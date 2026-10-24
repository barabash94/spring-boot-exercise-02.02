package com.bara.spring_boot_exercise.repository;

import com.bara.spring_boot_exercise.entity.Repo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepoRepository extends JpaRepository<Repo,Long> {
}
