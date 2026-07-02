package com.ticketmanagement.booking.controller;

import com.ticketmanagement.booking.dto.CreateTicketRequest;
import com.ticketmanagement.booking.dto.UpdateTicketRequest;
import com.ticketmanagement.booking.model.Priority;
import com.ticketmanagement.booking.model.Status;
import com.ticketmanagement.booking.model.Ticket;
import com.ticketmanagement.booking.service.TicketService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {
    private final TicketService service;

    public TicketController(TicketService service) {
        this.service = service;
    }

    // Create Ticket
    @PostMapping
    public ResponseEntity<Ticket> createTicket(
            @Valid @RequestBody CreateTicketRequest request) {

        Ticket ticket = service.createTicket(request);

        return new ResponseEntity<>(ticket, HttpStatus.CREATED);
    }

    // Get All Tickets / Filter
    @GetMapping
    public ResponseEntity<List<Ticket>> getTickets(

            @RequestParam(required = false) Status status,

            @RequestParam(required = false) Priority priority) {

        if (status == null && priority == null) {
            return ResponseEntity.ok(service.getAllTickets());
        }

        return ResponseEntity.ok(service.filterTickets(status, priority));
    }

    // Get Ticket By Id
    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getTicket(@PathVariable Long id) {

        return ResponseEntity.ok(service.getTicketById(id));
    }

    // Update Ticket
    @PutMapping("/{id}")
    public ResponseEntity<Ticket> updateTicket(

            @PathVariable Long id,

            @Valid @RequestBody UpdateTicketRequest request) {

        return ResponseEntity.ok(service.updateTicket(id, request));
    }

    // Delete Ticket
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long id) {

        service.deleteTicket(id);

        return ResponseEntity.noContent().build();
    }
}
