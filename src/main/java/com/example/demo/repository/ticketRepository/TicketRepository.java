package com.example.demo.repository.ticketRepository;

import com.example.demo.model.Ticket;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TicketRepository {
    Ticket findById(long id);

    Ticket greateNewTicket(Ticket ticket);

    List<Ticket> getAllTickets(int page, int size, String dateTime, String destinationPoint, String departurePoint, String transporterName);
}
