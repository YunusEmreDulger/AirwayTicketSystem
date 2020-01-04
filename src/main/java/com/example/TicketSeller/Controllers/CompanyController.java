package com.example.TicketSeller.Controllers;

import com.example.TicketSeller.Entities.Company;
import com.example.TicketSeller.Entities.Flight;
import com.example.TicketSeller.Services.CompanyServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/company")
@CrossOrigin
public class CompanyController {

    @Autowired
    CompanyServices companyServices;

    @PostMapping
    public void addNewCompany(@RequestBody Company company) {
        this.companyServices.addCompany(company);
    }

    @GetMapping("/{id}")
    public Company findCompanyById(@PathVariable int id) {
        return companyServices.findCompanyById(id);
    }

    @GetMapping("/findByName/{companyName}")
    public Company findCompanyByName(@PathVariable String companyName) {
        return companyServices.findCompanyByName(companyName);
    }

    @PutMapping("/{companyId}/{flightId}")
    public void addNewFlightToCompany(@PathVariable int companyId, @PathVariable int flightId) {
        companyServices.addFlightToCompany(companyId, flightId);
    }

    @GetMapping("/findFlightOfCompany/{companyId}/{flightId}")
    public Flight findFlightOfCompany(@PathVariable int companyId, @PathVariable int flightId) {
        if (companyServices.findFlightOfCompanyByFlightId(companyId, flightId) != null) {
            Flight flight = companyServices.findFlightOfCompanyByFlightId(companyId, flightId);
            return flight;
        } else
            return null;
    }


}
