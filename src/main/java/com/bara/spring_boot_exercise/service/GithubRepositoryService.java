package com.bara.spring_boot_exercise.service;

import com.bara.spring_boot_exercise.GitHubClient;
import com.bara.spring_boot_exercise.error.UserNotFoundException;
import com.bara.spring_boot_exercise.model.BranchResponseDto;
import com.bara.spring_boot_exercise.model.GithubRepositoryResponse;
import com.bara.spring_boot_exercise.model.RepositoryResponseDto;
import feign.FeignException;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class GithubRepositoryService {

    private final GitHubClient gitHubClient;

    public GithubRepositoryService(GitHubClient gitHubClient) {
        this.gitHubClient = gitHubClient;
    }


    public List<GithubRepositoryResponse> getRepositoryData(String userName) {
        log.info("Fetching info for user: {}", userName);
        List<RepositoryResponseDto> repositories = getAllUserRepos(userName);
        return repositories.stream()
                .map(this::createRepositoryResponse)
                .toList();
    }


    private GithubRepositoryResponse createRepositoryResponse(RepositoryResponseDto repository) {
        log.info("Fetching all branches from repository name: {}", repository.name());
        List<BranchResponseDto> branches =
                gitHubClient.getRepoBranches(
                        repository.owner().login(),
                        repository.name()
                );

        return new GithubRepositoryResponse(
                repository.name(),
                repository.owner().login(),
                branches
        );
    }

    private List<RepositoryResponseDto> getAllUserRepos(String userName) {
        log.info("Fetching all repos for user: {}", userName);
        try {
            List<RepositoryResponseDto> repos = gitHubClient.getUserRepos(userName);
            return repos.stream().
                    filter(repo -> !repo.fork())
                    .toList();
        } catch (FeignException.NotFound e) {
            log.error("User not found: {}", userName);
            throw new UserNotFoundException("User not found");
        }

    }
}
