package com.example.TicketSeller.Controllers;

import com.example.TicketSeller.Dto.CompanyRequest;
import com.example.TicketSeller.Dto.CompanyResponse;
import com.example.TicketSeller.Dto.FlightResponse;
import com.example.TicketSeller.Entities.Company;
import com.example.TicketSeller.Entities.Flight;
import com.example.TicketSeller.Services.ServicesImpl.CompanyServicesImpl;
import com.example.TicketSeller.Services.ServicesImpl.FlightServicesImpl;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
@CrossOrigin
public class CompanyController {

    private final CompanyServicesImpl companyServicesImpl;


    public CompanyController(CompanyServicesImpl companyServicesImpl) {
        this.companyServicesImpl = companyServicesImpl;
    }


    @PostMapping
    @CachePut(value = "companies", key = "#companyName")
    public void addNewCompany(@RequestBody CompanyRequest company) {

        this.companyServicesImpl.addCompany(company);
    }

    @GetMapping("/{id}")
    public Company findCompanyById(@PathVariable int id) {
        return companyServicesImpl.findCompanyById(id);
    }

    @GetMapping("/findByName/{companyName}")
    @Cacheable(value = "companies", key = "#companyName")
    public ResponseEntity<CompanyResponse> findCompanyByName(@PathVariable String companyName) {
        return ResponseEntity.ok(companyServicesImpl.findCompanyByName(companyName));
    }

    @GetMapping("/searchByName/{companyName}")
    @Cacheable(value = "companies", key = "#companyName")
    public ResponseEntity<List<CompanyResponse>> searchByCompanyName(@PathVariable String companyName) {
        return ResponseEntity.ok(companyServicesImpl.searchCompanyByName(companyName));
    }

/*    @PutMapping("/{companyId}/{flightId}")
    public void addNewFlightToCompany(@PathVariable int companyId, @PathVariable int flightId) {
        companyServices.addFlightToCompany(companyId, flightId);
    }*/

    @PutMapping("/{companyName}/{flightCode}")
    public void addNewFlightToCompany(@PathVariable String companyName, @PathVariable String flightCode) {
        companyName = companyName.toLowerCase();
        flightCode = flightCode.toUpperCase();
        companyServicesImpl.addFlightToCompany(companyName, flightCode);
    }

    @GetMapping("/findFlightOfCompany/{companyName}/{flightCode}")
    public ResponseEntity<FlightResponse> findFlightOfCompany(@PathVariable String companyName, @PathVariable String flightCode) {
        companyName = companyName.toLowerCase();
        flightCode = flightCode.toUpperCase();
        if (companyServicesImpl.findFlightOfCompanyByFlightCode(companyName, flightCode) != null) {
            FlightResponse flightResponse = companyServicesImpl.findFlightOfCompanyByFlightCode(companyName, flightCode);

            return ResponseEntity.ok(flightResponse);
        } else
            return null;
    }


}
