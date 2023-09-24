package com.example.demo.model.DTO;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Builder
public class DirectionDTOOut {
    private String departure_point;
    private String destination_point;
    private TransporterDTOOut transporterDTOOut;
    private long duration;
}
