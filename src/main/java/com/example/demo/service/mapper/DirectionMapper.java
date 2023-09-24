package com.example.demo.service.mapper;

import com.example.demo.model.DTO.DirectionDTOOut;
import com.example.demo.model.Direction;
import com.example.demo.repository.UserRepository.UserRepositoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DirectionMapper {
    private final TransporterMapper transporterMapper;
    private final Logger logger = LoggerFactory.getLogger(DirectionMapper.class);


    public DirectionMapper(TransporterMapper transporterMapper) {
        this.transporterMapper = transporterMapper;
    }

    public DirectionDTOOut toDTOOut(Direction direction) {
        logger.info("Invoke DirectionMapper, method toDTOOut");

        return new DirectionDTOOut().builder()
                .departure_point(direction.getDeparture_point())
                .destination_point(direction.getDestination_point())
                .duration(direction.getDuration())
                .transporterDTOOut(transporterMapper.toDTOOut(direction.getTransporter()))
                .build();

    }
}
