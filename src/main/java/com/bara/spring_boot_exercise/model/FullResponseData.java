package com.bara.spring_boot_exercise.model;

import java.util.List;

public record FullResponseData(String name, String owner,List<Branch> branches) {
}
