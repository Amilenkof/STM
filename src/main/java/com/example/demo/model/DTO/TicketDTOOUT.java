package com.example.demo.model.DTO;

import com.example.demo.model.User;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Builder
public class TicketDTOOUT {
    private DirectionDTOOut directionDTOOut;
    private LocalDateTime dateTime;
    private String seat;
    private BigDecimal price;
    private UserDtoOut userDtoOut;
    private String status;

}


//@NoArgsConstructor
//@AllArgsConstructor
//@EqualsAndHashCode(onlyExplicitlyIncluded = true)
//@Getter
//@Builder
//public class Ticket {
//    @EqualsAndHashCode.Include
//    private long id;
//    private Direction direction;
//    private LocalDateTime dateTime;
//    private String seat;
//    private BigDecimal price;
//    private User user;
//    private String status;
//}