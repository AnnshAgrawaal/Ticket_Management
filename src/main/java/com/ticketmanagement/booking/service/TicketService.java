package com.ticketmanagement.booking.service;

import com.ticketmanagement.booking.dto.CreateTicketRequest;
import com.ticketmanagement.booking.dto.UpdateTicketRequest;
import com.ticketmanagement.booking.exception.TicketNotFoundException;
import com.ticketmanagement.booking.model.Priority;
import com.ticketmanagement.booking.model.Status;
import com.ticketmanagement.booking.model.Ticket;
import com.ticketmanagement.booking.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository repository) {
        this.ticketRepository = repository;
    }

    // Create Ticket
    public Ticket createTicket(CreateTicketRequest request) {

        Ticket ticket = new Ticket();

        ticket.setTitle(request.getTitle());
        ticket.setDescription(request.getDescription());
        ticket.setPriority(request.getPriority());
        ticket.setStatus(Status.OPEN);

        return ticketRepository.addTicket(ticket);
    }

    // Get All Tickets
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    // Get Ticket By Id
    public Ticket getTicketById(Long id) {

        Ticket ticket = ticketRepository.findById(id);

        if (ticket == null) {
            throw new TicketNotFoundException("Ticket not found.");
        }

        return ticket;
    }

    // Update Ticket
    public Ticket updateTicket(Long id, UpdateTicketRequest request) {

        Ticket ticket = ticketRepository.findById(id);

        if (ticket == null) {
            throw new TicketNotFoundException("Ticket not found.");
        }

        ticket.setTitle(request.getTitle());
        ticket.setDescription(request.getDescription());
        ticket.setPriority(request.getPriority());
        ticket.setStatus(request.getStatus());

        return ticketRepository.addTicket(ticket);
    }

    // Delete Ticket
    public void deleteTicket(Long id) {

        if (!ticketRepository.existsById(id)) {
            throw new TicketNotFoundException("Ticket not found.");
        }

        ticketRepository.delete(id);
    }

    // Filter Tickets
    public List<Ticket> filterTickets(Status status, Priority priority) {

        List<Ticket> filteredTickets = new ArrayList<>();

        List<Ticket> tickets = ticketRepository.findAll();

        for (Ticket ticket : tickets) {

            boolean statusMatch =
                    (status == null || ticket.getStatus() == status);

            boolean priorityMatch =
                    (priority == null || ticket.getPriority() == priority);

            if (statusMatch && priorityMatch) {
                filteredTickets.add(ticket);
            }

        }

        return filteredTickets;
    }



}
