package com.example.TicketSeller.Controllers;

import com.example.TicketSeller.Dto.CompanyRequest;
import com.example.TicketSeller.Dto.CompanyResponse;
import com.example.TicketSeller.Dto.FlightResponse;
import com.example.TicketSeller.Entities.Company;
import com.example.TicketSeller.Services.CompanyServices;
import com.example.TicketSeller.Services.FlightServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
@CrossOrigin
public class CompanyController {

    @Autowired
    CompanyServices companyServices;

    @Autowired
    FlightServices flightServices;


    @PostMapping
    @CachePut(value = "companies" , key = "#companyName")
    public void addNewCompany(@RequestBody CompanyRequest company) {

        this.companyServices.addCompany(company);
    }

    @GetMapping("/{id}")
    public Company findCompanyById(@PathVariable int id) {
        return companyServices.findCompanyById(id);
    }

    @GetMapping("/findByName/{companyName}")
    @Cacheable(value = "companies" , key = "#companyName")
    public CompanyResponse findCompanyByName(@PathVariable String companyName) {
        return companyServices.findCompanyByName(companyName);
    }

    @GetMapping("/searchByName/{companyName}")
    @Cacheable(value = "companies" , key = "#companyName")
    public List<CompanyResponse> searchByCompanyName(@PathVariable String companyName) {
        return companyServices.searchCompanyByName(companyName);
    }

/*    @PutMapping("/{companyId}/{flightId}")
    public void addNewFlightToCompany(@PathVariable int companyId, @PathVariable int flightId) {
        companyServices.addFlightToCompany(companyId, flightId);
    }*/

    @PutMapping("/{companyName}/{flightCode}")
    public void addNewFlightToCompany(@PathVariable String companyName, @PathVariable String flightCode) {
        companyName = companyName.toLowerCase();
        flightCode = flightCode.toUpperCase();
        companyServices.addFlightToCompany(companyName, flightCode);
    }

    @GetMapping("/findFlightOfCompany/{companyName}/{flightCode}")
    public FlightResponse findFlightOfCompany(@PathVariable String companyName, @PathVariable String flightCode) {
        companyName = companyName.toLowerCase();
        flightCode = flightCode.toUpperCase();
        if (companyServices.findFlightOfCompanyByFlightCode(companyName, flightCode) != null) {
            FlightResponse flightResponse = flightServices.convertEntityToResponse(companyServices.findFlightOfCompanyByFlightCode(companyName, flightCode));

            return flightResponse;
        } else
            return null;
    }


}
