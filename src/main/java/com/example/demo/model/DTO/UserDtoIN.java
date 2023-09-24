package com.example.demo.model.DTO;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Builder
public class UserDtoIN {
    private String login;
    private String password;
    private String fullName;
}
