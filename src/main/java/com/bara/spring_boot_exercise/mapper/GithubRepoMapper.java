package com.bara.spring_boot_exercise.mapper;

import com.bara.spring_boot_exercise.entity.Repo;
import com.bara.spring_boot_exercise.model.GithubRepositoryResponse;
import org.springframework.stereotype.Component;

@Component
public class GithubRepoMapper {

    public Repo toEntity(GithubRepositoryResponse response) {
        Repo repo = new Repo();

        repo.setName(response.name());
        repo.setOwner(response.owner());

        return repo;
    }
}

