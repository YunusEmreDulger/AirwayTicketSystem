package com.example.TicketSeller.Repositories;

import com.example.TicketSeller.Entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepo extends JpaRepository<Ticket,Integer> {

    Ticket findTicketByTicketNo(String ticketNo);
    List<Ticket> findTicketsByFlight_IdOrderByTicketNo(int flightId);
}
