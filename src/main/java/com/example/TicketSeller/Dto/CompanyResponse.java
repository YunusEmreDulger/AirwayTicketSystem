package com.example.TicketSeller.Dto;

import com.example.TicketSeller.Entities.Flight;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class CompanyResponse implements Serializable {
    private String companyName;
    private List<Flight> flights = new ArrayList<>();

}
