package com.example.TicketSeller.Repositories;

import com.example.TicketSeller.Entities.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepo extends JpaRepository<Airport,Integer> {
    Airport findAirportById(int id);
    Airport findAirportByAirportName(String airportName);
}
