package com.example.TicketSeller.Services;

import com.example.TicketSeller.Entities.Airport;
import com.example.TicketSeller.Repositories.AirportRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AirportServices {

    @Autowired
    AirportRepo airportRepo;


    public void addAirport(Airport airport){
        airportRepo.save(airport);
    }

    public Airport findAirportById(int id){
       return airportRepo.findAirportById(id);
    }

    public Airport findAirportByAirportName(String airportName){
        return airportRepo.findAirportByAirportName(airportName);

    }
}
