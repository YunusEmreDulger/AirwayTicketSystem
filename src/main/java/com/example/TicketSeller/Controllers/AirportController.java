package com.example.TicketSeller.Controllers;

import com.example.TicketSeller.Dto.AirportRequest;
import com.example.TicketSeller.Dto.AirportResponse;
import com.example.TicketSeller.Entities.Airport;
import com.example.TicketSeller.Services.AirportService;
import com.example.TicketSeller.Services.ServicesImpl.AirportServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/airport")
@CrossOrigin
public class AirportController {

    private final AirportServicesImpl airportServiceImpl;

    public AirportController(AirportServicesImpl airportServiceImpl) {
        this.airportServiceImpl = airportServiceImpl;
    }

    @PostMapping
    public void addNewAirport(@RequestBody List<AirportRequest> newAirports) {

        airportServiceImpl.addAirport(newAirports);

    }

    @GetMapping("/{id}")
    public Airport findAirportById(@PathVariable int id) {
        return airportServiceImpl.findAirportById(id);
    }

    @GetMapping("/findByName/{airportName}")
    public ResponseEntity<AirportResponse> findAirportByAirportName(@PathVariable String airportName) {

        return ResponseEntity.ok(airportServiceImpl.findAirportByAirportName(airportName));
    }

    @GetMapping("/searchByName/{airportName}")
    public ResponseEntity<List<AirportResponse>> searchAirportByAirportName(@PathVariable String airportName) {
        List<AirportResponse> airportResponses = airportServiceImpl.searchAirportsByAirportName(airportName);
        return ResponseEntity.ok(airportResponses);
    }

}
