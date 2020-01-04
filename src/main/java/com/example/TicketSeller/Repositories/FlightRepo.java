package com.example.TicketSeller.Repositories;

import com.example.TicketSeller.Entities.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepo extends JpaRepository<Flight,Integer> {
    Flight findFlightById(int id);
    Flight findFlightByFlightCode(String flightCode);
}
