package com.example.TicketSeller.Dto;

import com.example.TicketSeller.Entities.Flight;
import lombok.Data;

@Data
public class TicketRequest {
    private String ticketNo;

    private double price;

    private String seatNumber;

    private boolean isSold;

    private Flight flight;
}
