package com.bara.spring_boot_exercise.controller;

import com.bara.spring_boot_exercise.model.GithubRepositoryResponse;
import com.bara.spring_boot_exercise.service.GithubRepositoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GithubRepositoryController {

    private final GithubRepositoryService githubRepositoryService;

    public GithubRepositoryController(GithubRepositoryService githubRepositoryService) {
        this.githubRepositoryService = githubRepositoryService;
    }


    @GetMapping("/api/{userName}")
    ResponseEntity<List<GithubRepositoryResponse>>getBranches(@PathVariable String userName){
        return  ResponseEntity.ok(githubRepositoryService.getRepositoryData(userName));
    }

}
