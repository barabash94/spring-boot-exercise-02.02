package com.bara.spring_boot_exercise.controller;

import com.bara.spring_boot_exercise.entity.Repo;
import com.bara.spring_boot_exercise.service.RepoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/repos")
@RequiredArgsConstructor
public class RepoController {

    private final RepoService repoService;

    @GetMapping
    public Page<Repo> getAll(Pageable pageable) {
        return repoService.getAll(pageable);
    }

    @PostMapping
    public Repo create(@RequestBody Repo repo) {
        return repoService.create(repo);
    }

    @GetMapping("/{id}")
    public Repo getById(@PathVariable Long id) {
        return repoService.getById(id);
    }

    @PutMapping("/{id}")
    public Repo update(@PathVariable Long id, @RequestBody Repo repo) {
        return repoService.update(id, repo);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repoService.delete(id);
    }

}
