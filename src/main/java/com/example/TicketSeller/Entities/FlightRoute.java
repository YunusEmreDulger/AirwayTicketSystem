package com.example.TicketSeller.Entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "flightRoute", uniqueConstraints =
@UniqueConstraint(columnNames = {"flightRoute_Code"}))
@NoArgsConstructor
@AllArgsConstructor
public class FlightRoute extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "flightRoute_generator")
    @SequenceGenerator(name = "flightRoute_generator", sequenceName = "flightRoute_seq", allocationSize = 1)
    @Column(name = "flightRoute_id")
    private int id;

    @Column(name = "flightRoute_Code")
    private String flightRouteCode;

    @Column(name = "startPoint")
    private Airport startPoint;

    @Column(name = "endPoint")
    private Airport endPoint;


}
