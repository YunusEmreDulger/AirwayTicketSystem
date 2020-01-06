package com.example.TicketSeller.Dto;

import com.example.TicketSeller.Entities.FlightRoute;
import lombok.Data;

@Data
public class FlightRequest {
    private String flightCode;
    private int quota;
    private FlightRoute flightRoute;

}
