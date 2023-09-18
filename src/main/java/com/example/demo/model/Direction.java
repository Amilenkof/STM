package com.example.demo.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Builder
public class Direction {//маршрут
    @EqualsAndHashCode.Include
    private long id;
    private String departure_point;
    private String destination_point;
    private Transporter transporter;
    private long duration;

}
