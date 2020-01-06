package com.example.TicketSeller.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "airport", uniqueConstraints =
@UniqueConstraint(columnNames = {"airportName"}))
@NoArgsConstructor
@AllArgsConstructor
public class Airport extends BaseEntity implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "airport_generator")
    @SequenceGenerator(name = "airport_generator", sequenceName = "airport_seq", allocationSize = 1)
    @Column(name = "airport_id")
    private int id;

    @Column(name = "airportName")
    private String airportName;


}
