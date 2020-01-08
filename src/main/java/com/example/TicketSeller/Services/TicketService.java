package com.example.TicketSeller.Services;

import com.example.TicketSeller.Dto.TicketRequest;
import com.example.TicketSeller.Dto.TicketResponse;
import com.example.TicketSeller.Entities.Ticket;

import java.util.List;

public interface TicketService {
    void addTicket(List<TicketRequest> ticketRequests);

    TicketResponse findTicketByTicketNo(String ticketNo);

    List<TicketResponse> getAllTicketResponsesOfFlight(String flightCode);

    List<Ticket> getAllTicketsOfFlight(String flightCode);

    void buyTicket(String ticketNo);

    void cancelTicket(String ticketNo);

    double checkOccupancyAndIncrementPrice(double occupancy, double occupancyTemp, double price);

    double checkOccupancyAndDecrementPrice(double occupancy, double occupancyTemp, double price);
}
