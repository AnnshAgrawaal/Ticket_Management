package com.ticketmanagement.booking.repository;

import com.ticketmanagement.booking.exception.TicketNotFoundException;
import com.ticketmanagement.booking.model.Ticket;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class TicketRepository {

    private final Map<Long, Ticket> tickets = new ConcurrentHashMap<>();
    private final AtomicLong idCounter  = new AtomicLong(1);

    public Ticket addTicket(Ticket ticket) {
        if(ticket.getId()==null){
            ticket.setId(idCounter .getAndIncrement());
        }

        tickets.put(ticket.getId(), ticket);
        return ticket;
    }

    public Ticket findById(long id) {
        return tickets.get(id);
    }

    public List<Ticket> findAll() {
        return new ArrayList<>(tickets.values());
    }

    public void delete(Long id) {
        tickets.remove(id);
    }

    public boolean existsById(Long id) {
        return tickets.containsKey(id);
    }
}
