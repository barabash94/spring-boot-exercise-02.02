package com.bara.spring_boot_exercise.service;

import com.bara.spring_boot_exercise.entity.Repo;
import com.bara.spring_boot_exercise.error.RepoNotFoundException;
import com.bara.spring_boot_exercise.mapper.GithubRepoMapper;
import com.bara.spring_boot_exercise.model.GithubRepositoryResponse;
import com.bara.spring_boot_exercise.repository.RepoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RepoService {

    private final RepoRepository repoRepository;
    private final GithubRepositoryService githubRepositoryService;
    private final GithubRepoMapper githubRepoMapper;

    public Page<Repo> getAll(Pageable pageable) {
        return repoRepository.findAll(pageable);
    }

    public Repo create(Repo repo) {
        return repoRepository.save(repo);
    }

    public Repo getById(Long id) {
        return repoRepository.findById(id)
                .orElseThrow( ()->
        new RepoNotFoundException("Repository with id " + id + " not found"));

    }

    public Repo update(Long id, Repo repo) {
        Repo existingRepo = repoRepository.findById(id)
                .orElseThrow();

        existingRepo.setOwner(repo.getOwner());
        existingRepo.setName(repo.getName());

        return repoRepository.save(existingRepo);
    }

    public void delete(Long id) {
        repoRepository.deleteById(id);
    }

    public List<Repo> saveRepositoriesFromGithub(String userName) {

        List<GithubRepositoryResponse> githubRepositories =
                githubRepositoryService.getRepositoryData(userName);

        List<Repo> repos = githubRepositories.stream()
                .map(githubRepoMapper::toEntity)
                .toList();

        return repoRepository.saveAll(repos);
    }
}
