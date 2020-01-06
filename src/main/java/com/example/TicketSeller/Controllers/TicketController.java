package com.example.TicketSeller.Controllers;

import com.example.TicketSeller.Dto.TicketRequest;
import com.example.TicketSeller.Dto.TicketResponse;
import com.example.TicketSeller.Entities.Flight;
import com.example.TicketSeller.Entities.Ticket;
import com.example.TicketSeller.Services.FlightServices;
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

    @Autowired
    FlightServices flightServices;

    @PostMapping
    public void addNewTicket(@RequestBody List<TicketRequest> ticketRequests) {
        for (TicketRequest ticketRequest : ticketRequests) {
            Flight flight = flightServices.getFlightByFlightCode(ticketRequest.getFlight().getFlightCode());
            ticketRequest.setFlight(flight);
        }
        ticketServices.addTicket(ticketRequests);
    }

    @GetMapping("/{ticketNo}")
    public TicketResponse findTicketByTicketNo(@PathVariable String ticketNo) {
        return ticketServices.findTicketByTicketNo(ticketNo);
    }

    @GetMapping("/getAllTicketsOfFlight/{flightCode}")
    public List<TicketResponse> getAllTicketsOfFlight(@PathVariable String flightCode) {
        return ticketServices.getAllTicketResponsesOfFlight(flightCode);
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
