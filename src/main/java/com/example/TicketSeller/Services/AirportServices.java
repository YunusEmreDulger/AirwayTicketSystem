package com.example.TicketSeller.Services;

import com.example.TicketSeller.Entities.Airport;
import com.example.TicketSeller.Repositories.AirportRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class AirportServices {

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

    public Airport findAirportByAirportName(String airportName) {
        airportName = airportName.toLowerCase();
        return airportRepo.findAirportByAirportName(airportName);

    }


    public List<Airport> searchAirportsByAirportName(String airportName) {
        airportName = airportName.toLowerCase();
        return airportRepo.findAirportsByAirportNameContaining(airportName);
    }
}
