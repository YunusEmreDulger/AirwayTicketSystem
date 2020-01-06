package com.example.TicketSeller.Services;

import com.example.TicketSeller.Dto.AirportRequest;
import com.example.TicketSeller.Dto.AirportResponse;
import com.example.TicketSeller.Entities.Airport;
import com.example.TicketSeller.EntityInterfaces.AirportInterface;
import com.example.TicketSeller.Repositories.AirportRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class AirportServices implements AirportInterface {

    @Autowired
    AirportRepo airportRepo;

    public void addAirport(List<Airport> airports) {
        for (Airport airport : airports) {
            airport.setAirportName(airport.getAirportName().toLowerCase());
            airportRepo.save(airport);
        }
    }

    public Airport findAirportById(int id) {
        return airportRepo.findAirportById(id);
    }

    public AirportResponse findAirportByAirportName(String airportName) {
        airportName = airportName.toLowerCase();

        return convertEntityToResponse(airportRepo.findAirportByAirportName(airportName));
    }

    public List<Airport> searchAirportsByAirportName(String airportName) {
        airportName = airportName.toLowerCase();
        return airportRepo.findAirportsByAirportNameContaining(airportName);
    }

    @Override
    public Airport convertRequestToEntity(AirportRequest airportRequest) {
        Airport airport = new Airport();
        airport.setAirportName(airportRequest.getAirportName());

        return airport;
    }

    @Override
    public AirportResponse convertEntityToResponse(Airport airport) {

        AirportResponse airportResponse = new AirportResponse();
        airportResponse.setAirportName(airport.getAirportName());

        return airportResponse;
    }
}
