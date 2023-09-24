package com.example.demo.model.DTO;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Builder
public class TicketDTOIN {
    private long directionID;
    private LocalDateTime dateTime;
    private String seat;
    private BigDecimal price;
    private long userID;


}
