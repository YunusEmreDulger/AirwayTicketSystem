package com.example.TicketSeller.EntityInterfaces;

import com.example.TicketSeller.Dto.AirportRequest;
import com.example.TicketSeller.Dto.AirportResponse;
import com.example.TicketSeller.Dto.CompanyRequest;
import com.example.TicketSeller.Dto.CompanyResponse;
import com.example.TicketSeller.Entities.Airport;
import com.example.TicketSeller.Entities.Company;

public interface CompanyInterface {
    Company convertRequestToEntity(CompanyRequest companyRequest);
    CompanyResponse convertEntityToResponse(Company company);
}
