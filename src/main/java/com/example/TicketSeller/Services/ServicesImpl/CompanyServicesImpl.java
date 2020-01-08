package com.example.TicketSeller.Services.ServicesImpl;

import com.example.TicketSeller.Dto.CompanyRequest;
import com.example.TicketSeller.Dto.CompanyResponse;
import com.example.TicketSeller.Dto.FlightResponse;
import com.example.TicketSeller.Entities.Company;
import com.example.TicketSeller.Entities.Flight;
import com.example.TicketSeller.Repositories.CompanyRepo;
import com.example.TicketSeller.Repositories.FlightRepo;
import com.example.TicketSeller.Services.CompanyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CompanyServicesImpl implements CompanyService {

    private final CompanyRepo companyRepo;
    private final FlightRepo flightRepo;
    private final ModelMapper modelMapper;

    public CompanyServicesImpl(CompanyRepo companyRepo, FlightRepo flightRepo, ModelMapper modelMapper) {
        this.companyRepo = companyRepo;
        this.flightRepo = flightRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addCompany(CompanyRequest companyRequest) {
        companyRequest.setCompanyName(companyRequest.getCompanyName().toLowerCase());

        Company company = modelMapper.map(companyRequest, Company.class);
        companyRepo.save(company);
    }

    @Override
    public Company findCompanyById(int id) {
        return companyRepo.findCompanyById(id);
    }

    @Override
    public CompanyResponse findCompanyByName(String companyName) {
        companyName = companyName.toLowerCase();
        Company company = companyRepo.findCompanyByCompanyName(companyName);
        CompanyResponse companyResponse = modelMapper.map(company, CompanyResponse.class);
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
    @Override
    public void addFlightToCompany(String companyName, String flightCode) {
        Company company = companyRepo.findCompanyByCompanyName(companyName);
        Flight flight = flightRepo.findFlightByFlightCode(flightCode);
        List<Flight> flights = company.getFlights();
        flights.add(flight);
        company.setFlights(flights);
        companyRepo.save(company);
    }

    @Override
    public List<CompanyResponse> searchCompanyByName(String companyName) {

        List<Company> companyList = companyRepo.findCompanyByCompanyNameContaining(companyName);
        List<CompanyResponse> companyResponseList = new ArrayList<>();

        for (Company company : companyList) {
            companyResponseList.add(modelMapper.map(company, CompanyResponse.class));
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
    @Override
    public FlightResponse findFlightOfCompanyByFlightCode(String companyName, String flightCode) {
        Company company = companyRepo.findCompanyByCompanyName(companyName);
        Flight flight = flightRepo.findFlightByFlightCode(flightCode);

        if (company.getFlights().contains(flight)) {
            FlightResponse flightResponse = modelMapper.map(flight, FlightResponse.class);
            return flightResponse;
        } else
            return null;
    }

}
