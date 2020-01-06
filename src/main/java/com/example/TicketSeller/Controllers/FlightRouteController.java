package com.example.TicketSeller.Controllers;

import com.example.TicketSeller.Dto.FlightRouteRequest;
import com.example.TicketSeller.Dto.FlightRouteResponse;
import com.example.TicketSeller.Entities.FlightRoute;
import com.example.TicketSeller.Services.FlightRouteServices;
import oracle.jdbc.proxy.annotation.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/flightRoute")
@CrossOrigin
public class FlightRouteController {

    @Autowired
    FlightRouteServices flightRouteServices;

    @PostMapping
    public void addNewFlightRoute(@RequestBody FlightRouteRequest flightRouteRequest) {
        flightRouteServices.addFlightRoute(flightRouteRequest);
    }

    @GetMapping("/{id}")
    public FlightRoute findFlightRouteById(@PathVariable int id) {
        return flightRouteServices.findFlightRouteById(id);
    }

    @GetMapping("/findByFlightRouteCode/{flightRouteCode}")
    public FlightRouteResponse findFlightRouteByFlightRouteCode(@PathVariable String flightRouteCode) {
        flightRouteCode = flightRouteCode.toUpperCase();
        return flightRouteServices.findFlightRouteByFlightRouteCode(flightRouteCode);
    }
}


