package com.example.demo.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Builder
public class Transporter {
    @EqualsAndHashCode.Include
    private long id;
    private String name;
    private String phone;
}
