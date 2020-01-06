package com.example.TicketSeller.EntityInterfaces;

import com.example.TicketSeller.Dto.FlightRouteRequest;
import com.example.TicketSeller.Dto.FlightRouteResponse;
import com.example.TicketSeller.Dto.TicketRequest;
import com.example.TicketSeller.Dto.TicketResponse;
import com.example.TicketSeller.Entities.FlightRoute;
import com.example.TicketSeller.Entities.Ticket;

public interface TicketInterface {

    Ticket convertRequestToEntity(TicketRequest ticketRequest);
    TicketResponse convertEntityToResponse(Ticket ticket);
}
