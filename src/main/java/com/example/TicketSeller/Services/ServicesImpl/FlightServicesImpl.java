package com.example.TicketSeller.Services.ServicesImpl;

import com.example.TicketSeller.Dto.FlightRequest;
import com.example.TicketSeller.Dto.FlightResponse;
import com.example.TicketSeller.Entities.Flight;
import com.example.TicketSeller.Entities.FlightRoute;
import com.example.TicketSeller.Repositories.FlightRepo;
import com.example.TicketSeller.Repositories.FlightRouteRepo;
import com.example.TicketSeller.Services.FlightService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlightServicesImpl implements FlightService {

    private final FlightRepo flightRepo;
    private final FlightRouteRepo flightRouteRepo;
    private final ModelMapper modelMapper;

    public FlightServicesImpl(FlightRepo flightRepo, FlightRouteRepo flightRouteRepo, ModelMapper modelMapper) {
        this.flightRepo = flightRepo;
        this.flightRouteRepo = flightRouteRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addNewFlight(FlightRequest flightRequest) {
        FlightRoute flightRoute = flightRouteRepo.findFlightRouteByFlightRouteCode(flightRequest.getFlightRoute().getFlightRouteCode());
        flightRequest.setFlightRoute(flightRoute);
        Flight flight = modelMapper.map(flightRequest, Flight.class);
        flightRepo.save(flight);
    }

    @Override
    public Flight getFlightByFlightCode(String flightCode) {
        return flightRepo.findFlightByFlightCode(flightCode);
    }

}
