package com.example.TicketSeller.Services;

import com.example.TicketSeller.Entities.FlightRoute;
import com.example.TicketSeller.Repositories.AirportRepo;
import com.example.TicketSeller.Repositories.FlightRepo;
import com.example.TicketSeller.Repositories.FlightRouteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlightRouteServices {
    @Autowired
    FlightRouteRepo flightRouteRepo;

    @Autowired
    AirportRepo airportRepo;

    public void addFlightRoute(FlightRoute flightRoute) {
        flightRoute.setStartPoint(airportRepo.findAirportById(flightRoute.getStartPoint().getId()));
        flightRoute.setEndPoint(airportRepo.findAirportById(flightRoute.getEndPoint().getId()));
        flightRouteRepo.save(flightRoute);
    }

    public FlightRoute findFlightRouteById(int id) {
        return flightRouteRepo.findFlightRouteById(id);
    }

    public FlightRoute findFlightRouteByFlightRouteCode(String flightRouteCode) {
        return flightRouteRepo.findFlightRouteByFlightRouteCode(flightRouteCode);
    }
}
