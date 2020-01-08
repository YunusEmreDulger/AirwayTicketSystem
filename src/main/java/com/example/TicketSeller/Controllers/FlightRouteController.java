package com.example.TicketSeller.Controllers;

import com.example.TicketSeller.Dto.FlightRouteRequest;
import com.example.TicketSeller.Dto.FlightRouteResponse;
import com.example.TicketSeller.Entities.FlightRoute;
import com.example.TicketSeller.Services.ServicesImpl.FlightRouteServicesImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/flightRoute")
@CrossOrigin
public class FlightRouteController {


    private final FlightRouteServicesImpl flightRouteServicesImpl;

    public FlightRouteController(FlightRouteServicesImpl flightRouteServicesImpl) {
        this.flightRouteServicesImpl = flightRouteServicesImpl;
    }

    @PostMapping
    public void addNewFlightRoute(@RequestBody FlightRouteRequest flightRouteRequest) {
        flightRouteServicesImpl.addFlightRoute(flightRouteRequest);
    }

    @GetMapping("/{id}")
    public FlightRoute findFlightRouteById(@PathVariable int id) {
        return flightRouteServicesImpl.findFlightRouteById(id);
    }

    @GetMapping("/findByFlightRouteCode/{flightRouteCode}")
    public ResponseEntity<FlightRouteResponse> findFlightRouteByFlightRouteCode(@PathVariable String flightRouteCode) {
        flightRouteCode = flightRouteCode.toUpperCase();
        return ResponseEntity.ok(flightRouteServicesImpl.findFlightRouteByFlightRouteCode(flightRouteCode));
    }
}


