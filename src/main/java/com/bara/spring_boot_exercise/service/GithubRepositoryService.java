package com.bara.spring_boot_exercise.service;

import com.bara.spring_boot_exercise.GitHubClient;
import com.bara.spring_boot_exercise.model.BranchResponseDto;
import com.bara.spring_boot_exercise.model.GithubRepositoryResponse;
import com.bara.spring_boot_exercise.model.RepositoryResponseDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Log4j2
public class GithubRepositoryService {

    private final GitHubClient gitHubClient;

    public GithubRepositoryService(GitHubClient gitHubClient) {
        this.gitHubClient = gitHubClient;
    }

    public List<GithubRepositoryResponse> getRepositoryData(String userName) {
        List<GithubRepositoryResponse> repositoryData = new ArrayList<>();
        List<RepositoryResponseDto> repositories = getAllUserRepos(userName);
        List<BranchResponseDto> branches = getBranchesByUserName(userName);

        for (RepositoryResponseDto repo : repositories) {
            repositoryData.add(new GithubRepositoryResponse(repo.name(), repo.owner().login(), Collections.singletonList(branches.get(branches.size() - 1))));
        }
//
//
        return repositoryData;
    }

    private List<BranchResponseDto> getBranchesByUserName(String userName) {
        List<BranchResponseDto> branches = new ArrayList<>();
        List<RepositoryResponseDto> repositories = getAllUserRepos(userName);
        for (RepositoryResponseDto repo : repositories) {
            branches.addAll(gitHubClient.getRepoBranches(repo.owner().login(), repo.name()));
        }
        return branches;
    }

    private List<RepositoryResponseDto> getAllUserRepos(String userName) {
        log.info("Fetching all repos for user:" + userName);
        List<RepositoryResponseDto> repos = gitHubClient.getUserRepos(userName);
        repos.stream().filter(repo -> repo.fork() != true)
                .toList();
        return repos;
    }
}
