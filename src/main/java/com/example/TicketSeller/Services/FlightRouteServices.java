package com.example.TicketSeller.Services;

import com.example.TicketSeller.Dto.FlightRouteRequest;
import com.example.TicketSeller.Dto.FlightRouteResponse;
import com.example.TicketSeller.Entities.Airport;
import com.example.TicketSeller.Entities.FlightRoute;
import com.example.TicketSeller.EntityInterfaces.FlightRouteInterface;
import com.example.TicketSeller.Repositories.AirportRepo;
import com.example.TicketSeller.Repositories.FlightRepo;
import com.example.TicketSeller.Repositories.FlightRouteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlightRouteServices implements FlightRouteInterface {
    @Autowired
    FlightRouteRepo flightRouteRepo;

    @Autowired
    AirportRepo airportRepo;

    public void addFlightRoute(FlightRouteRequest flightRouteRequest) {
        Airport startPointAirport = airportRepo.findAirportByAirportName(flightRouteRequest.getStartPoint().getAirportName());
        Airport endpointAirport = airportRepo.findAirportByAirportName(flightRouteRequest.getEndPoint().getAirportName());

        flightRouteRequest.setStartPoint(startPointAirport);
        flightRouteRequest.setEndPoint(endpointAirport);
        flightRouteRequest.setStartPoint(airportRepo.findAirportById(flightRouteRequest.getStartPoint().getId()));
        flightRouteRequest.setEndPoint(airportRepo.findAirportById(flightRouteRequest.getEndPoint().getId()));

        FlightRoute flightRoute = convertRequestToEntity(flightRouteRequest);
        flightRouteRepo.save(flightRoute);
    }

    public FlightRoute findFlightRouteById(int id) {
        return flightRouteRepo.findFlightRouteById(id);
    }

    public FlightRouteResponse findFlightRouteByFlightRouteCode(String flightRouteCode) {
        return convertEntityToResponse(flightRouteRepo.findFlightRouteByFlightRouteCode(flightRouteCode));
    }

    @Override
    public FlightRoute convertRequestToEntity(FlightRouteRequest flightRouteRequest) {
        FlightRoute flightRoute = new FlightRoute();
        flightRoute.setFlightRouteCode(flightRouteRequest.getFlightRouteCode());
        flightRoute.setStartPoint(flightRouteRequest.getStartPoint());
        flightRoute.setEndPoint(flightRouteRequest.getEndPoint());
        return flightRoute;
    }

    @Override
    public FlightRouteResponse convertEntityToResponse(FlightRoute flightRoute) {
        FlightRouteResponse flightRouteResponse = new FlightRouteResponse();
        flightRouteResponse.setFlightRouteCode(flightRoute.getFlightRouteCode());
        flightRouteResponse.setStartPoint(flightRoute.getStartPoint());
        flightRouteResponse.setEndPoint(flightRoute.getEndPoint());
        return flightRouteResponse;
    }
}
