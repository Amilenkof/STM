package com.example.demo.service.mapper;

import com.example.demo.model.DTO.TicketDTOIN;
import com.example.demo.model.DTO.TicketDTOOUT;
import com.example.demo.model.DTO.UserDtoOut;
import com.example.demo.model.Ticket;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository.UserRepositoryImpl;
import com.example.demo.repository.directionRepository.DirectionRepositoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TicketMapper {
    private final DirectionRepositoryImpl directionRepository;
    private final DirectionMapper directionMapper;
    private final UserMapper userMapper;
    private final Logger logger = LoggerFactory.getLogger(TicketMapper.class);


    public TicketMapper(DirectionRepositoryImpl directionRepository, DirectionMapper directionMapper, UserMapper userMapper) {
        this.directionRepository = directionRepository;
        this.directionMapper = directionMapper;
        this.userMapper = userMapper;
    }

    public Ticket toEntity(TicketDTOIN dtoIn) {
        logger.info("Invoke TicketMapper, method toEntity");

        return Ticket.builder()
                .direction(directionRepository.findById(dtoIn.getDirectionID()))
                .dateTime(dtoIn.getDateTime())
                .seat(dtoIn.getSeat())
                .price(dtoIn.getPrice())
                .status("FREE")
                .build();

    }

    public TicketDTOOUT toDTOOut(Ticket ticket) {
        logger.info("Invoke TicketMapper, method toDTOOut");
        User user = ticket.getUser();


        return new TicketDTOOUT().builder()
                .directionDTOOut(directionMapper.toDTOOut(ticket.getDirection()))
                .dateTime(ticket.getDateTime())
                .seat(ticket.getSeat())
                .price(ticket.getPrice())
                .userDtoOut(user!=null ? userMapper.toDTOOut(ticket.getUser()): null)
                .status(ticket.getStatus())
                .build();

    }
}
