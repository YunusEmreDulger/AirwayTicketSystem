package com.example.TicketSeller.Services;

import com.example.TicketSeller.Dto.FlightRequest;
import com.example.TicketSeller.Dto.FlightResponse;
import com.example.TicketSeller.Entities.Flight;
import com.example.TicketSeller.Entities.FlightRoute;
import com.example.TicketSeller.EntityInterfaces.FlightInterface;
import com.example.TicketSeller.Repositories.FlightRepo;
import com.example.TicketSeller.Repositories.FlightRouteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.Oneway;

@Service
public class FlightServices implements FlightInterface {

    @Autowired
    FlightRepo flightRepo;

    @Autowired
    FlightRouteRepo flightRouteRepo;

    public void addNewFlight(FlightRequest flightRequest) {
        FlightRoute flightRoute = flightRouteRepo.findFlightRouteByFlightRouteCode(flightRequest.getFlightRoute().getFlightRouteCode());
        flightRequest.setFlightRoute(flightRoute);
        Flight flight = convertRequestToEntity(flightRequest);
        flightRepo.save(flight);
    }

    public Flight getFlightByFlightCode(String flightCode) {
        return flightRepo.findFlightByFlightCode(flightCode);
    }

    @Override
    public Flight convertRequestToEntity(FlightRequest flightRequest) {
        Flight flight = new Flight();
        flight.setFlightCode(flightRequest.getFlightCode());
        flight.setQuota(flightRequest.getQuota());
        flight.setFlightRoute(flightRequest.getFlightRoute());

        return flight;

    }

    @Override
    public FlightResponse convertEntityToResponse(Flight flight) {
        FlightResponse flightResponse = new FlightResponse();
        flightResponse.setFlightCode(flight.getFlightCode());
        flightResponse.setFlightRoute(flight.getFlightRoute());
        flightResponse.setNumberOfSales(flight.getNumberOfSales());
        flightResponse.setQuota(flight.getQuota());
        flightResponse.setOccupancy(flight.getOccupancy());
        return null;
    }
}
