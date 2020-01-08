package com.example.TicketSeller.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "flight", uniqueConstraints =
@UniqueConstraint(columnNames = {"flightCode"}))
@NoArgsConstructor
@AllArgsConstructor
public class Flight extends BaseEntity implements Serializable {


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

    @ManyToOne
    @JoinColumn(name = "flightRoute_id", referencedColumnName = "flightRoute_id")
    private FlightRoute flightRoute;


    public double computeOccupancy(int numberOfSales, int quota) {
        return Double.valueOf(numberOfSales) / Double.valueOf(quota);
    }
}
