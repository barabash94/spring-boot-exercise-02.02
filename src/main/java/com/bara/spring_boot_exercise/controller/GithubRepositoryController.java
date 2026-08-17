package com.bara.spring_boot_exercise.controller;

import com.bara.spring_boot_exercise.model.GithubRepositoryResponse;
import com.bara.spring_boot_exercise.service.GithubRepositoryService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Validated
public class GithubRepositoryController {

    private final GithubRepositoryService githubRepositoryService;

    public GithubRepositoryController(GithubRepositoryService githubRepositoryService) {
        this.githubRepositoryService = githubRepositoryService;
    }


    @GetMapping(path = "/api/{userName}",produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<GithubRepositoryResponse>> getBranches(@PathVariable @NotBlank(message = "Username cannot be blank") String userName) {
        return ResponseEntity.ok(githubRepositoryService.getRepositoryData(userName));
    }

}
