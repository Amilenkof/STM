package com.example.demo.model.DTO;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Builder
public class UserDtoOut {
    private String login;
    private String password;
    private String fullName;
}
