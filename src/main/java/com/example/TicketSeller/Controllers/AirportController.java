package com.example.TicketSeller.Controllers;

import com.example.TicketSeller.Dto.AirportRequest;
import com.example.TicketSeller.Dto.AirportResponse;
import com.example.TicketSeller.Entities.Airport;
import com.example.TicketSeller.Services.AirportServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/airport")
@CrossOrigin
public class AirportController {

    @Autowired
    AirportServices airportServices;

    @PostMapping
    public void addNewAirport(@RequestBody List<AirportRequest> newAirports) {
        List<Airport> airportList = new ArrayList<>();

        for (AirportRequest airportRequest : newAirports) {
            Airport airport = airportServices.convertRequestToEntity(airportRequest);
            airportList.add(airport);
        }
        airportServices.addAirport(airportList);

    }

    @GetMapping("/{id}")
    public Airport findAirportById(@PathVariable int id) {
        return airportServices.findAirportById(id);
    }

    @GetMapping("/findByName/{airportName}")
    public AirportResponse findAirportByAirportName(@PathVariable String airportName) {
        return airportServices.findAirportByAirportName(airportName);
    }

    @GetMapping("/searchByName/{airportName}")
    public List<AirportResponse> searchAirportByAirportName(@PathVariable String airportName) {
        List<AirportResponse> airportResponses = new ArrayList<>();
        List<Airport> airports = airportServices.searchAirportsByAirportName(airportName);
        for (Airport airport : airports) {
            airportResponses.add(airportServices.convertEntityToResponse(airport));
        }

        return airportResponses;
    }

}
