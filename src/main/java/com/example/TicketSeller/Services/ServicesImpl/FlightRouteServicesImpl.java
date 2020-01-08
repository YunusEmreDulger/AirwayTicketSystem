package com.example.TicketSeller.Services.ServicesImpl;

import com.example.TicketSeller.Dto.FlightRouteRequest;
import com.example.TicketSeller.Dto.FlightRouteResponse;
import com.example.TicketSeller.Entities.Airport;
import com.example.TicketSeller.Entities.FlightRoute;
import com.example.TicketSeller.Repositories.AirportRepo;
import com.example.TicketSeller.Repositories.FlightRouteRepo;
import com.example.TicketSeller.Services.FlightRouteService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class FlightRouteServicesImpl implements FlightRouteService {

    private final FlightRouteRepo flightRouteRepo;
    private final AirportRepo airportRepo;
    private final ModelMapper modelMapper;

    public FlightRouteServicesImpl(FlightRouteRepo flightRouteRepo, AirportRepo airportRepo, ModelMapper modelMapper) {
        this.flightRouteRepo = flightRouteRepo;
        this.airportRepo = airportRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addFlightRoute(FlightRouteRequest flightRouteRequest) {
        Airport startPointAirport = airportRepo.findAirportByAirportName(flightRouteRequest.getStartPoint().getAirportName());
        Airport endpointAirport = airportRepo.findAirportByAirportName(flightRouteRequest.getEndPoint().getAirportName());

        flightRouteRequest.setStartPoint(startPointAirport);
        flightRouteRequest.setEndPoint(endpointAirport);
        flightRouteRequest.setStartPoint(airportRepo.findAirportById(flightRouteRequest.getStartPoint().getId()));
        flightRouteRequest.setEndPoint(airportRepo.findAirportById(flightRouteRequest.getEndPoint().getId()));

        FlightRoute flightRoute = modelMapper.map(flightRouteRequest, FlightRoute.class);
        flightRouteRepo.save(flightRoute);
    }

    @Override
    public FlightRoute findFlightRouteById(int id) {
        return flightRouteRepo.findFlightRouteById(id);
    }

    @Override
    public FlightRouteResponse findFlightRouteByFlightRouteCode(String flightRouteCode) {
        FlightRoute flightRoute = flightRouteRepo.findFlightRouteByFlightRouteCode(flightRouteCode);

        return modelMapper.map(flightRoute, FlightRouteResponse.class);
    }
}
