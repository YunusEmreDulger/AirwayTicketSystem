package com.example.TicketSeller.Services;

import com.example.TicketSeller.Dto.FlightRouteRequest;
import com.example.TicketSeller.Dto.FlightRouteResponse;
import com.example.TicketSeller.Entities.FlightRoute;

public interface FlightRouteService {
    void addFlightRoute(FlightRouteRequest flightRouteRequest);

    FlightRoute findFlightRouteById(int id);

    FlightRouteResponse findFlightRouteByFlightRouteCode(String flightRouteCode);

}
