package com.bara.spring_boot_exercise;

import com.bara.spring_boot_exercise.model.Branch;
import com.bara.spring_boot_exercise.model.ResponseData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(
        name = "repos",
        url = "${gitHubApi.url}")
public interface GitHubClient {


    @GetMapping(value = "/users/{name}/repos", produces = MediaType.APPLICATION_JSON_VALUE)
    List<ResponseData> getUserRepos(@PathVariable("name") String userName);

    @GetMapping(value = "/repos/{userName}/{repoName}/branches", produces = MediaType.APPLICATION_JSON_VALUE)
    List<Branch> getFullResponse(@PathVariable("userName") String userName, @PathVariable("repoName") String repoName);

}
