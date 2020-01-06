package com.example.TicketSeller.EntityInterfaces;

import com.example.TicketSeller.Dto.FlightRequest;
import com.example.TicketSeller.Dto.FlightResponse;
import com.example.TicketSeller.Dto.FlightRouteRequest;
import com.example.TicketSeller.Dto.FlightRouteResponse;
import com.example.TicketSeller.Entities.Flight;
import com.example.TicketSeller.Entities.FlightRoute;

public interface FlightRouteInterface {

    FlightRoute convertRequestToEntity(FlightRouteRequest flightRouteRequest);
    FlightRouteResponse convertEntityToResponse(FlightRoute flightRoute);
}
