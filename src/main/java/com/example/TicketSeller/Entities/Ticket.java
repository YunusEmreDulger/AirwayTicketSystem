package com.example.TicketSeller.Entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name = "ticket", uniqueConstraints =
@UniqueConstraint(columnNames = {"ticketNo"}))
@NoArgsConstructor
@AllArgsConstructor
public class Ticket implements Serializable {

    //bilet satın alınabilmeli,iptal edilebilmeli, aranabilmeli

    //her bir bilet satışında uçuk kontenjanının %10,%20,%30 kotnenjanları dolduğunda bilet fiyatı %10 artış göstermeli.


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ticket_generator")
    @SequenceGenerator(name = "ticket_generator", sequenceName = "ticket_seq", allocationSize = 1)
    @Column(name = "ticket_id")
    private int id;

    @Column(name = "ticketNo")
    private String ticketNo;

    @Column(name = "price")
    private double price;


    @Column(name = "seatNumber")
    private String seatNumber;

    @Column(name = "isSold")
    private boolean isSold;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ticket_date")
    private Date creationDate;

    @ManyToOne
    @JoinColumn(name = "flight_id", referencedColumnName = "flight_id")
    private Flight flight;


}
