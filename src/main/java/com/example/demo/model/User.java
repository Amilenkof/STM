package com.example.demo.model;

import lombok.*;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Builder
public class User {
    @EqualsAndHashCode.Include
    private long id;
    private String login;
    private String password;
    private String fullName;



}
