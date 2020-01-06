package com.example.TicketSeller.EntityInterfaces;

import com.example.TicketSeller.Dto.AirportRequest;
import com.example.TicketSeller.Dto.AirportResponse;
import com.example.TicketSeller.Entities.Airport;

public interface AirportInterface {

    Airport convertRequestToEntity(AirportRequest airportRequest);
    AirportResponse convertEntityToResponse(Airport airport);
}
