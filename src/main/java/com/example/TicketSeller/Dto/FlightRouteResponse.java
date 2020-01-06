package com.example.TicketSeller.Dto;

import com.example.TicketSeller.Entities.Airport;
import lombok.Data;

@Data
public class FlightRouteResponse {
    private String flightRouteCode;

    private Airport startPoint;

    private Airport endPoint;
}
