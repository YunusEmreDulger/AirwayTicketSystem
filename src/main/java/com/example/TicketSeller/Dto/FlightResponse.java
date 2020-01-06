package com.example.TicketSeller.Dto;

import com.example.TicketSeller.Entities.FlightRoute;
import lombok.Data;

@Data
public class FlightResponse {
    private String flightCode;

    private int quota;

    private int numberOfSales;

    private double occupancy;

    private FlightRoute flightRoute;

}
