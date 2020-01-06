package com.example.TicketSeller.EntityInterfaces;

import com.example.TicketSeller.Dto.CompanyRequest;
import com.example.TicketSeller.Dto.CompanyResponse;
import com.example.TicketSeller.Dto.FlightRequest;
import com.example.TicketSeller.Dto.FlightResponse;
import com.example.TicketSeller.Entities.Company;
import com.example.TicketSeller.Entities.Flight;

public interface FlightInterface {


    Flight convertRequestToEntity(FlightRequest flightRequest);
    FlightResponse convertEntityToResponse(Flight flight);

}
