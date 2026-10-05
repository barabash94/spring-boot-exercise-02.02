package com.bara.spring_boot_exercise.service;

import com.bara.spring_boot_exercise.entity.Repo;
import com.bara.spring_boot_exercise.repository.RepoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RepoService {
    private final RepoRepository repoRepository;

    public Page<Repo> getAll(Pageable pageable) {
        return repoRepository.findAll(pageable);
    }

    public Repo create(Repo repo) {
        return repoRepository.save(repo);
    }

    public Repo getById(Long id) {
        return repoRepository.findById(id)
                .orElseThrow();
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
}
