package com.example.TicketSeller.Controllers;

import com.example.TicketSeller.Entities.Airport;
import com.example.TicketSeller.Services.AirportServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/airport")
@CrossOrigin
public class AirportController {

    @Autowired
    AirportServices airportServices;

    @PostMapping
    public void addNewAirport(@RequestBody List<Airport> newAirports) {
        airportServices.addAirport(newAirports);
    }

    @GetMapping("/{id}")
    public Airport findAirportById(@PathVariable int id) {
        return airportServices.findAirportById(id);
    }

    @GetMapping("/findByName/{airportName}")
    public Airport findAirportByAirportName(@PathVariable String airportName) {
        return airportServices.findAirportByAirportName(airportName);
    }

    @GetMapping("/searchByName/{airportName}")
    public List<Airport> searchAirportByAirportName(@PathVariable String airportName){
        return  airportServices.searchAirportsByAirportName(airportName);
    }

}
