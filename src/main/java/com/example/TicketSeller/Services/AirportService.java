package com.example.TicketSeller.Services;

import com.example.TicketSeller.Dto.AirportRequest;
import com.example.TicketSeller.Dto.AirportResponse;
import com.example.TicketSeller.Entities.Airport;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AirportService {

    void addAirport(List<AirportRequest> airportRequests);

    Airport findAirportById(int id);

    AirportResponse findAirportByAirportName(String airportName);

    List<AirportResponse> searchAirportsByAirportName(String airportName);
}
