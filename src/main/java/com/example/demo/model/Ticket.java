package com.example.demo.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Builder
public class Ticket {
    @EqualsAndHashCode.Include
    private long id;
    private Direction direction;
    private LocalDateTime dateTime;
    private String seat;
    private BigDecimal price;
    private User user;
    private String status;
}
