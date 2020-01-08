package com.example.TicketSeller.Controllers;

import com.example.TicketSeller.Dto.FlightRequest;
import com.example.TicketSeller.Services.ServicesImpl.FlightServicesImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/flight")
@CrossOrigin
public class FlightController {

    private final FlightServicesImpl flightServicesImpl;

    public FlightController(FlightServicesImpl flightServicesImpl) {
        this.flightServicesImpl = flightServicesImpl;
    }

    @PostMapping
    public void addNewFlight(@RequestBody FlightRequest flightRequest) {
        flightServicesImpl.addNewFlight(flightRequest);
    }
}
