package com.example.demo.controller;

import com.example.demo.model.DTO.TicketDTOIN;
import com.example.demo.model.DTO.TicketDTOOUT;
import com.example.demo.model.Ticket;
import com.example.demo.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tickets")

public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping("/greateNewTicket")
    public ResponseEntity<TicketDTOOUT> greateNewTicket(@RequestBody TicketDTOIN ticketDTOIN) {
        return ResponseEntity.ok(ticketService.greateNewTicket(ticketDTOIN));
    }

    @GetMapping("/getAllTickets")//todo падает если передать ноль в Size и не понятно почему

    public ResponseEntity<List<TicketDTOOUT>> getAllTickets(@RequestParam("page") int page,
                                                            @RequestParam("size") int size,
                                                            @RequestParam(required = false, name = "dateTime") String dateTime,
                                                            @RequestParam(required = false, name = "destinationPoint") String destinationPoint,
                                                            @RequestParam(required = false, name = "departurePoint") String departurePoint,
                                                            @RequestParam(required = false, name = "transporterName") String transporterName) {

        return ResponseEntity.ok(ticketService.getAllTickets(page, size, dateTime, destinationPoint, departurePoint, transporterName));
    }

    @GetMapping ("/buy/{id}")
    public  ResponseEntity<TicketDTOOUT> buyTicket (@PathVariable long id){
        return ResponseEntity.ok(ticketService.buyTicket(id));
    }

    @GetMapping ("/find/{id}")
    public  ResponseEntity<TicketDTOOUT> findById (@PathVariable long id){
        return ResponseEntity.ok(ticketService.findById(id));
    }

}
