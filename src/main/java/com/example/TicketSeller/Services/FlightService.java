package com.example.TicketSeller.Services;

import com.example.TicketSeller.Dto.FlightRequest;
import com.example.TicketSeller.Dto.FlightResponse;
import com.example.TicketSeller.Entities.Flight;

public interface FlightService {
    void addNewFlight(FlightRequest flightRequest);

    Flight getFlightByFlightCode(String flightCode);
}
