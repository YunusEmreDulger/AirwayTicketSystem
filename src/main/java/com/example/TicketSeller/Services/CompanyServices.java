package com.example.TicketSeller.Services;

import com.example.TicketSeller.Entities.Company;
import com.example.TicketSeller.Entities.Flight;
import com.example.TicketSeller.Repositories.CompanyRepo;
import com.example.TicketSeller.Repositories.FlightRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServices {

    @Autowired
    CompanyRepo companyRepo;

    @Autowired
    FlightRepo flightRepo;

    public void addCompany(Company company) {
        company.setCompanyName(company.getCompanyName().toLowerCase());
        companyRepo.save(company);
    }

    public Company findCompanyById(int id) {
        return companyRepo.findCompanyById(id);
    }

    public Company findCompanyByName(String companyName) {
        companyName = companyName.toLowerCase();
        return companyRepo.findCompanyByCompanyName(companyName);
    }

    public void addFlightToCompany(int companyId, int flightId) {
        Company company = companyRepo.findCompanyById(companyId);
        Flight flight = flightRepo.findFlightById(flightId);
        List<Flight> flights = company.getFlights();
        flights.add(flight);
        company.setFlights(flights);
        companyRepo.save(company);
    }

    public Flight findFlightOfCompanyByFlightId(int companyId, int flightId) {
        Company company = companyRepo.findCompanyById(companyId);
        Flight flight = flightRepo.findFlightById(flightId);

        if (company.getFlights().contains(flight)) {
            return flight;
        } else
            return null;
    }
}
