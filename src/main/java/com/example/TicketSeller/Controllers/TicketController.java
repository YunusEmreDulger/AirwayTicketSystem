package com.example.TicketSeller.Controllers;

import com.example.TicketSeller.Entities.Ticket;
import com.example.TicketSeller.Services.TicketServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ticket")
@CrossOrigin
public class TicketController {

    @Autowired
    TicketServices ticketServices;

    @PostMapping
    public void addNewTicket(@RequestBody List<Ticket> tickets) {
        ticketServices.addTicket(tickets);
    }

    @GetMapping("/{ticketNo}")
    public Ticket findTicketByTicketNo(@PathVariable String ticketNo) {
        return ticketServices.findTicketByTicketNo(ticketNo);
    }

    @GetMapping("/getAllTicketsOfFlight/{flightId}")
    public List<Ticket> getAllTicketsOfFlight(@PathVariable int flightId)
    {
        return ticketServices.getAllTicketsOfFlight(flightId);
    }


    @PutMapping("/buy/{ticketNo}")
    public void buyTicket(@PathVariable String ticketNo) {
        ticketServices.buyTicket(ticketNo);
    }

    @PutMapping("/cancellation/{ticketNo}")
    public void cancelTicket(@PathVariable String ticketNo) {
        ticketServices.cancelTicket(ticketNo);
    }

}
