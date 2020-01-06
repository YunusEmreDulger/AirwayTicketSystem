package com.example.TicketSeller.Dto;

import com.example.TicketSeller.Entities.Flight;
import lombok.Data;

import java.util.Date;

@Data
public class TicketResponse {
    private String ticketNo;

    private double price;

    private String seatNumber;

    private boolean isSold;

    private Date creationDate;

    private Flight flight;
}
