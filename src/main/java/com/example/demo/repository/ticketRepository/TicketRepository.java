package com.example.demo.repository.ticketRepository;

import com.example.demo.model.Ticket;

public interface TicketRepository {
    Ticket findById(long id);
}
