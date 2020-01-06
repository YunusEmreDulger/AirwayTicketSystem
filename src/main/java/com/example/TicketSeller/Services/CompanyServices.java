package com.example.TicketSeller.Services;

import com.example.TicketSeller.Dto.CompanyRequest;
import com.example.TicketSeller.Dto.CompanyResponse;
import com.example.TicketSeller.Dto.FlightResponse;
import com.example.TicketSeller.Entities.Company;
import com.example.TicketSeller.Entities.Flight;
import com.example.TicketSeller.EntityInterfaces.CompanyInterface;
import com.example.TicketSeller.Repositories.CompanyRepo;
import com.example.TicketSeller.Repositories.FlightRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyServices implements CompanyInterface {

    @Autowired
    CompanyRepo companyRepo;

    @Autowired
    FlightRepo flightRepo;

    public void addCompany(CompanyRequest companyRequest) {
        companyRequest.setCompanyName(companyRequest.getCompanyName().toLowerCase());

        Company company = convertRequestToEntity(companyRequest);
        companyRepo.save(company);
    }

    public Company findCompanyById(int id) {
        return companyRepo.findCompanyById(id);
    }

    public CompanyResponse findCompanyByName(String companyName) {
        companyName = companyName.toLowerCase();
        CompanyResponse companyResponse = convertEntityToResponse(companyRepo.findCompanyByCompanyName(companyName));
        return companyResponse;
    }

/*    public void addFlightToCompany(int companyId, int flightId) {
        Company company = companyRepo.findCompanyById(companyId);
        Flight flight = flightRepo.findFlightById(flightId);
        List<Flight> flights = company.getFlights();
        flights.add(flight);
        company.setFlights(flights);
        companyRepo.save(company);
    }*/

    public void addFlightToCompany(String companyName, String flightCode) {
        Company company = companyRepo.findCompanyByCompanyName(companyName);
        Flight flight = flightRepo.findFlightByFlightCode(flightCode);
        List<Flight> flights = company.getFlights();
        flights.add(flight);
        company.setFlights(flights);
        companyRepo.save(company);
    }

    public List<CompanyResponse> searchCompanyByName(String companyName) {

        List<Company> companyList = companyRepo.findCompanyByCompanyNameContaining(companyName);
        List<CompanyResponse> companyResponseList = new ArrayList<>();

        for (Company company : companyList) {
            companyResponseList.add(convertEntityToResponse(company));
        }

        return companyResponseList;
    }

    /*public Flight findFlightOfCompanyByFlightId(int companyId, int flightId) {
        Company company = companyRepo.findCompanyById(companyId);
        Flight flight = flightRepo.findFlightById(flightId);

        if (company.getFlights().contains(flight)) {
            return flight;
        } else
            return null;
    }*/

    public Flight findFlightOfCompanyByFlightCode(String companyName, String flightCode) {
        Company company = companyRepo.findCompanyByCompanyName(companyName);
        Flight flight = flightRepo.findFlightByFlightCode(flightCode);

        if (company.getFlights().contains(flight)) {
            return flight;
        } else
            return null;
    }

    @Override
    public Company convertRequestToEntity(CompanyRequest companyRequest) {
        Company company = new Company();
        company.setCompanyName(companyRequest.getCompanyName());
        return company;
    }

    @Override
    public CompanyResponse convertEntityToResponse(Company company) {
        CompanyResponse companyResponse = new CompanyResponse();
        companyResponse.setCompanyName(company.getCompanyName());
        companyResponse.setFlights(company.getFlights());
        return companyResponse;
    }
}
