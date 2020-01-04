package com.example.TicketSeller.Controllers;

import com.example.TicketSeller.Entities.Flight;
import com.example.TicketSeller.Services.FlightServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/flight")
@CrossOrigin
public class FlightController {

    @Autowired
    FlightServices flightServices;

    @PostMapping
    public void addNewFlight(@RequestBody Flight flight){
        flightServices.addNewFlight(flight);
    }
}
