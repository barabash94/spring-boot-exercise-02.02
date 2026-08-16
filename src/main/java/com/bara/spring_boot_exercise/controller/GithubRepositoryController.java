package com.bara.spring_boot_exercise.controller;

import com.bara.spring_boot_exercise.model.Branch;
import com.bara.spring_boot_exercise.model.FullResponseData;
import com.bara.spring_boot_exercise.model.ResponseData;
import com.bara.spring_boot_exercise.service.GithubRepositoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GithubRepositoryController {

    private final GithubRepositoryService githubRepositoryService;

    public GithubRepositoryController(GithubRepositoryService githubRepositoryService) {
        this.githubRepositoryService = githubRepositoryService;
    }

    @GetMapping("/repo/{userName}")
    List<ResponseData>getAllRepos(@PathVariable String userName){
       return  githubRepositoryService.getAllUserRepos(userName);

    }

    @GetMapping("/branches/{userName}")
    List<FullResponseData>getBranches(@PathVariable String userName){
        return  githubRepositoryService.getBranchesByUserName(userName);
    }

}
