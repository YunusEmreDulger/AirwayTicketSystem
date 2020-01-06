package com.example.TicketSeller.Repositories;

import com.example.TicketSeller.Entities.Company;
import com.example.TicketSeller.Entities.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyRepo extends JpaRepository<Company,Integer> {
    Company findCompanyById(int id);
    Company findCompanyByCompanyName(String companyName);
    List<Company> findCompanyByCompanyNameContaining(String companyName);

}
