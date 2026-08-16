package com.bara.spring_boot_exercise.service;

import com.bara.spring_boot_exercise.GitHubClient;
import com.bara.spring_boot_exercise.model.Branch;
import com.bara.spring_boot_exercise.model.FullResponseData;
import com.bara.spring_boot_exercise.model.ResponseData;
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

    public List<FullResponseData> getBranchesByUserName(String userName) {
        List<FullResponseData> fullData = new ArrayList<>();
        List<Branch> branches = new ArrayList<>();
        List<ResponseData> repositories = getAllUserRepos(userName);
        for (ResponseData repo : repositories) {
            branches.addAll(gitHubClient.getFullResponse(repo.owner().login(), repo.name()));
            fullData.add(new FullResponseData(repo.name(), repo.owner().login(), Collections.singletonList(branches.get(branches.size() - 1))));
        }


        return fullData;
    }

    public List<ResponseData> getAllUserRepos(String userName) {
        log.info("Fetching all repos for user:" + userName);
        List<ResponseData> repos = gitHubClient.getUserRepos(userName);
        repos.stream().filter(repo -> repo.fork() != true)
                .toList();
        return repos;
    }
}
