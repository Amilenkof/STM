package com.example.demo.service;

import com.example.demo.exception.CurrentTicketHasBeenSold;
import com.example.demo.exception.TicketAlreadyHaveException;
import com.example.demo.model.DTO.TicketDTOIN;
import com.example.demo.model.DTO.TicketDTOOUT;
import com.example.demo.model.Ticket;
import com.example.demo.repository.ticketRepository.TickerRepositoryImpl;
import com.example.demo.service.mapper.TicketMapper;
import com.example.demo.service.mapper.UserMapper;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TicketService {
    private final TickerRepositoryImpl tickerRepository;
    private final TicketMapper ticketMapper;
    private final Logger logger = LoggerFactory.getLogger(UserMapper.class);


    public TicketService(TickerRepositoryImpl tickerRepository, TicketMapper ticketMapper) {
        this.tickerRepository = tickerRepository;
        this.ticketMapper = ticketMapper;
    }


    public TicketDTOOUT greateNewTicket(TicketDTOIN ticketDTOIN) {
        logger.info("Invoke TicketService, method greateNewTicket");

        try {
            Ticket ticket = ticketMapper.toEntity(ticketDTOIN);
            tickerRepository.greateNewTicket(ticket);
            return ticketMapper.toDTOOut(ticket);
        } catch (DataIntegrityViolationException e) {
            throw new TicketAlreadyHaveException("Ticket already have in DB");
        }

    }

    public List<TicketDTOOUT> getAllTickets(int page, int size, String dateTime,
                                            String destinationPoint, String departurePoint, String transporterName) {
        logger.info("Invoke TicketService, method getAllTickets");
        return tickerRepository.getAllTickets(page, size, dateTime,
                        destinationPoint, departurePoint,
                        transporterName).stream()
                .map(ticketMapper::toDTOOut)
                .toList();
    }

    @Transactional()
    public TicketDTOOUT buyTicket(long id) {
        logger.info("Invoke  TicketService method buyTicket(id={})", id);
        Ticket ticket = tickerRepository.findById(id);
        int rowsAffected = tickerRepository.updateStatus(id);
        System.out.println(rowsAffected);
        if (rowsAffected > 0) {
            ticket.setStatus("SOLD");
            return ticketMapper.toDTOOut(ticket);
        } else
            throw new CurrentTicketHasBeenSold("The ticket has already been sold earlier");
    }

    public TicketDTOOUT findById(long id) {
        Ticket ticket = tickerRepository.findById(id);
        return ticketMapper.toDTOOut(ticket);
    }
}
