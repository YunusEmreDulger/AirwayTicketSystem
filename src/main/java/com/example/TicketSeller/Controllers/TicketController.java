package com.example.TicketSeller.Controllers;

import com.example.TicketSeller.Dto.TicketRequest;
import com.example.TicketSeller.Dto.TicketResponse;
import com.example.TicketSeller.Entities.Flight;
import com.example.TicketSeller.Services.ServicesImpl.FlightServicesImpl;
import com.example.TicketSeller.Services.ServicesImpl.TicketServicesImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ticket")
@CrossOrigin
public class TicketController {

    private final TicketServicesImpl ticketServicesImpl;

    public TicketController(TicketServicesImpl ticketServicesImpl) {
        this.ticketServicesImpl = ticketServicesImpl;
    }

    @PostMapping
    public void addNewTicket(@RequestBody List<TicketRequest> ticketRequests) {

        ticketServicesImpl.addTicket(ticketRequests);
    }

    @GetMapping("/{ticketNo}")
    public ResponseEntity<TicketResponse> findTicketByTicketNo(@PathVariable String ticketNo) {
        return ResponseEntity.ok(ticketServicesImpl.findTicketByTicketNo(ticketNo));
    }

    @GetMapping("/getAllTicketsOfFlight/{flightCode}")
    public ResponseEntity<List<TicketResponse>> getAllTicketsOfFlight(@PathVariable String flightCode) {
        return ResponseEntity.ok(ticketServicesImpl.getAllTicketResponsesOfFlight(flightCode));
    }

    @PutMapping("/buy/{ticketNo}")
    public void buyTicket(@PathVariable String ticketNo) {
        ticketServicesImpl.buyTicket(ticketNo);
    }

    @PutMapping("/cancellation/{ticketNo}")
    public void cancelTicket(@PathVariable String ticketNo) {
        ticketServicesImpl.cancelTicket(ticketNo);
    }

}
