package com.ticketmanagement.booking.service;

import com.ticketmanagement.booking.dto.CreateTicketRequest;
import com.ticketmanagement.booking.dto.UpdateTicketRequest;
import com.ticketmanagement.booking.exception.TicketNotFoundException;
import com.ticketmanagement.booking.model.Priority;
import com.ticketmanagement.booking.model.Status;
import com.ticketmanagement.booking.model.Ticket;
import com.ticketmanagement.booking.repository.TicketRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TicketServiceTest {

    @Mock
    private TicketRepository ticketRepository;

    @InjectMocks
    private TicketService ticketService;

    @Test
    void testCreateTicket() {

        CreateTicketRequest request = new CreateTicketRequest();
        request.setTitle("Login Issue");
        request.setDescription("Unable to login");
        request.setPriority(Priority.HIGH);

        Ticket savedTicket = new Ticket();
        savedTicket.setId(1L);
        savedTicket.setTitle("Login Issue");
        savedTicket.setDescription("Unable to login");
        savedTicket.setPriority(Priority.HIGH);
        savedTicket.setStatus(Status.OPEN);

        when(ticketRepository.addTicket(any(Ticket.class))).thenReturn(savedTicket);

        Ticket result = ticketService.createTicket(request);

        assertNotNull(result);
        assertEquals("Login Issue", result.getTitle());
        assertEquals(Status.OPEN, result.getStatus());

        verify(ticketRepository).addTicket(any(Ticket.class));
    }

    @Test
    void testGetTicketById() {

        Ticket ticket = new Ticket();
        ticket.setId(1L);
        ticket.setTitle("Payment Issue");

        when(ticketRepository.findById(1L)).thenReturn(ticket);

        Ticket result = ticketService.getTicketById(1L);

        assertEquals(1L, result.getId());
        assertEquals("Payment Issue", result.getTitle());
    }

    @Test
    void testGetTicketByIdNotFound() {

        when(ticketRepository.findById(1L)).thenReturn(null);

        assertThrows(TicketNotFoundException.class,
                () -> ticketService.getTicketById(1L));
    }

    @Test
    void testGetAllTickets() {

        List<Ticket> tickets = new ArrayList<>();

        Ticket t1 = new Ticket();
        t1.setId(1L);

        Ticket t2 = new Ticket();
        t2.setId(2L);

        tickets.add(t1);
        tickets.add(t2);

        when(ticketRepository.findAll()).thenReturn(tickets);

        List<Ticket> result = ticketService.getAllTickets();

        assertEquals(2, result.size());
    }

    @Test
    void testDeleteTicket() {

        when(ticketRepository.existsById(1L)).thenReturn(true);

        ticketService.deleteTicket(1L);

        verify(ticketRepository).delete(1L);
    }

    @Test
    void testDeleteTicketNotFound() {

        when(ticketRepository.existsById(1L)).thenReturn(false);

        assertThrows(TicketNotFoundException.class,
                () -> ticketService.deleteTicket(1L));
    }

    @Test
    void testUpdateTicket() {

        Ticket ticket = new Ticket();
        ticket.setId(1L);
        ticket.setTitle("Old");

        UpdateTicketRequest request = new UpdateTicketRequest();
        request.setTitle("New");
        request.setDescription("Updated");
        request.setPriority(Priority.LOW);
        request.setStatus(Status.RESOLVED);

        when(ticketRepository.findById(1L)).thenReturn(ticket);
        when(ticketRepository.addTicket(any(Ticket.class))).thenReturn(ticket);

        Ticket result = ticketService.updateTicket(1L, request);

        assertEquals("New", result.getTitle());
        assertEquals(Status.RESOLVED, result.getStatus());

        verify(ticketRepository).addTicket(ticket);
    }

    @Test
    void testFilterTickets() {

        List<Ticket> tickets = new ArrayList<>();

        Ticket t1 = new Ticket();
        t1.setStatus(Status.OPEN);
        t1.setPriority(Priority.HIGH);

        Ticket t2 = new Ticket();
        t2.setStatus(Status.RESOLVED);
        t2.setPriority(Priority.LOW);

        tickets.add(t1);
        tickets.add(t2);

        when(ticketRepository.findAll()).thenReturn(tickets);

        List<Ticket> result =
                ticketService.filterTickets(Status.OPEN, Priority.HIGH);

        assertEquals(1, result.size());
    }

}
