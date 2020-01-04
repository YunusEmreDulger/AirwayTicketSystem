package com.example.TicketSeller.Entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "company", uniqueConstraints =
@UniqueConstraint(columnNames = {"companyName"}))
@NoArgsConstructor
@AllArgsConstructor
public class Company implements Serializable {

    // uçuş tanımlanabilecek,aranabilecek

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "company_generator")
    @SequenceGenerator(name = "company_generator", sequenceName = "company_seq" , allocationSize = 1)
    @Column(name = "company_id")
    private int id;

    @Column(name = "companyName")
    private String companyName;

    @OneToMany
    @JoinColumn(name = "company_id" , referencedColumnName = "company_id")
    private List<Flight> flights = new ArrayList<>();

/*    @OneToMany
    @JoinColumn(name = "company_id" , referencedColumnName = "company_id")
    private List<Ticket> tickets = new ArrayList<>();*/


}
