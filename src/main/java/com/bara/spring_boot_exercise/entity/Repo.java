package com.bara.spring_boot_exercise.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "repo")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Repo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String owner;

    @Column(nullable = false)
    private String name;

}
