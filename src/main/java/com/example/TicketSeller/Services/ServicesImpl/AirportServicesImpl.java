package com.example.TicketSeller.Services.ServicesImpl;

import com.example.TicketSeller.Dto.AirportRequest;
import com.example.TicketSeller.Dto.AirportResponse;
import com.example.TicketSeller.Entities.Airport;
import com.example.TicketSeller.Repositories.AirportRepo;
import com.example.TicketSeller.Services.AirportService;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class AirportServicesImpl implements AirportService {

    private final AirportRepo airportRepo;
    private final ModelMapper modelMapper;

    public AirportServicesImpl(AirportRepo airportRepo, ModelMapper modelMapper) {
        this.airportRepo = airportRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addAirport(List<AirportRequest> airportRequests) {
        for (AirportRequest airportRequest : airportRequests) {
            airportRequest.setAirportName(airportRequest.getAirportName().toLowerCase());
            Airport airport = modelMapper.map(airportRequest, Airport.class);
            airportRepo.save(airport);
        }
    }

    @Override
    public Airport findAirportById(int id) {
        return airportRepo.findAirportById(id);
    }

    @Override
    public AirportResponse findAirportByAirportName(String airportName) {
        airportName = airportName.toLowerCase();
//convertEntityToResponse(airportRepo.findAirportByAirportName(airportName));
        Airport airport = airportRepo.findAirportByAirportName(airportName);
        return modelMapper.map(airport, AirportResponse.class);
    }

    @Override
    public List<AirportResponse> searchAirportsByAirportName(String airportName) {
        airportName = airportName.toLowerCase();
        List<Airport> airports = airportRepo.findAirportsByAirportNameContaining(airportName);
        List<AirportResponse> airportResponses = new ArrayList<>();

        for (Airport airport : airports) {
            airportResponses.add(modelMapper.map(airport, AirportResponse.class));
        }
        return airportResponses;
    }
}
