package com.example.TicketSeller.Repositories;

import com.example.TicketSeller.Entities.FlightRoute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRouteRepo extends JpaRepository<FlightRoute,Integer> {
    FlightRoute findFlightRouteById(int id);
    FlightRoute findFlightRouteByFlightRouteCode(String flightRouteCode);
}
