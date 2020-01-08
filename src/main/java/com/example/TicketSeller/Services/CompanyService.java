package com.example.TicketSeller.Services;

import com.example.TicketSeller.Dto.CompanyRequest;
import com.example.TicketSeller.Dto.CompanyResponse;
import com.example.TicketSeller.Dto.FlightResponse;
import com.example.TicketSeller.Entities.Company;

import java.util.List;

public interface CompanyService {
    void addCompany(CompanyRequest companyRequest);

    Company findCompanyById(int id);

    CompanyResponse findCompanyByName(String companyName);

    void addFlightToCompany(String companyName, String flightCode);

    List<CompanyResponse> searchCompanyByName(String companyName);

    FlightResponse findFlightOfCompanyByFlightCode(String companyName, String flightCode);
}
