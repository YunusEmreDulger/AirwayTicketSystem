package com.example.TicketSeller.Entities;

import com.example.TicketSeller.EntityInterfaces.FlightInterface;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "flight")
@NoArgsConstructor
@AllArgsConstructor
public class Flight implements Serializable, FlightInterface {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "flight_generator")
    @SequenceGenerator(name = "flight_generator", sequenceName = "flight_seq", allocationSize = 1)
    @Column(name = "flight_id")
    private int id;

    @Column(name = "flightCode")
    private String flightCode;


    @Column(name = "quota")
    private int quota;

    @Column(name = "numberOfSales")
    private int numberOfSales;

    @Column(name = "occupancy")
    private double occupancy;

   /* @OneToMany
    @JoinColumn(name = "flight_id" , referencedColumnName = "flight_id")
    private List<Ticket> ticket = new ArrayList<>();*/


    @Override
    public double computeOccupancy(int numberOfSales, int quota) {
        return Double.valueOf(numberOfSales) / Double.valueOf(quota);
    }
}
