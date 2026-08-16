package com.bara.spring_boot_exercise.model;

import java.util.List;

public record GithubRepositoryResponse(String name, String owner, List<BranchResponseDto> branches) {
}
