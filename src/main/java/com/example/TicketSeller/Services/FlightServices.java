package com.example.TicketSeller.Services;

import com.example.TicketSeller.Entities.Flight;
import com.example.TicketSeller.Repositories.FlightRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlightServices {

    @Autowired
    FlightRepo flightRepo;

    public void  addNewFlight(Flight flight){
        flightRepo.save(flight);
    }
}
