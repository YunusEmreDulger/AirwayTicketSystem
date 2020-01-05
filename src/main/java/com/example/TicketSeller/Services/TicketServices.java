package com.example.TicketSeller.Services;

import com.example.TicketSeller.Entities.Flight;
import com.example.TicketSeller.Entities.Ticket;
import com.example.TicketSeller.Repositories.FlightRepo;
import com.example.TicketSeller.Repositories.TicketRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServices {

    @Autowired
    TicketRepo ticketRepo;

    @Autowired
    FlightRepo flightRepo;

    public void addTicket(List<Ticket> tickets) {
        for (Ticket ticket : tickets) {
            ticketRepo.save(tickets);
        }
    }

    public Ticket findTicketByTicketNo(String ticketNo) {
        return ticketRepo.findTicketByTicketNo(ticketNo);
    }


    public List<Ticket> getAllTicketsOfFlight(int flightId) {
        return ticketRepo.findTicketsByFlight_IdOrderByTicketNo(flightId);
    }

    public void buyTicket(String ticketNo) {
        Ticket ticket = ticketRepo.findTicketByTicketNo(ticketNo);
        ticket.setSold(true);

        ticketRepo.save(ticket);

        Flight flight = flightRepo.findFlightByFlightCode(ticket.getFlight().getFlightCode());

        double occupancyTemp = flight.computeOccupancy(flight.getNumberOfSales(), flight.getQuota());

        if (flight.getNumberOfSales() < flight.getQuota()) {
            flight.setNumberOfSales(flight.getNumberOfSales() + 1);
        }
        double occupancy = flight.computeOccupancy(flight.getNumberOfSales(), flight.getQuota());
        flight.setOccupancy(occupancy);

        List<Ticket> allTicketsOfFlight = getAllTicketsOfFlight(flight.getId());

        if (occupancy > occupancyTemp) {
            for (Ticket oneOfTicket : allTicketsOfFlight) {
                if (!oneOfTicket.isSold()) {
                    oneOfTicket.setPrice(checkOccupancyAndIncrementPrice(occupancy, occupancyTemp, ticket.getPrice()));
                }
            }
        }
        if (occupancyTemp > occupancy) {
            for (Ticket oneOfTicket : allTicketsOfFlight) {
                if (!oneOfTicket.isSold()) {
                    oneOfTicket.setPrice(checkOccupancyAndDecrementPrice(occupancy, occupancyTemp, ticket.getPrice()));

                }
            }
        }

        ticketRepo.save(ticket);
        flightRepo.save(flight);

    }

    public void cancelTicket(String ticketNo) {
        Ticket ticket = ticketRepo.findTicketByTicketNo(ticketNo);
        ticket.setSold(false);

        ticketRepo.save(ticket);


        Flight flight = flightRepo.findFlightByFlightCode(ticket.getFlight().getFlightCode());

        double occupancyTemp = flight.computeOccupancy(flight.getNumberOfSales(), flight.getQuota());
        if (flight.getNumberOfSales() >= 1) {
            flight.setNumberOfSales(flight.getNumberOfSales() - 1);
        }
        double occupancy = flight.computeOccupancy(flight.getNumberOfSales(), flight.getQuota());
        flight.setOccupancy(occupancy);

        List<Ticket> allTicketsOfFlight = getAllTicketsOfFlight(flight.getId());

        if (occupancy > occupancyTemp) {
            for (Ticket oneOfTicket : allTicketsOfFlight) {
                if (!oneOfTicket.isSold()) {
                    oneOfTicket.setPrice(checkOccupancyAndIncrementPrice(occupancy, occupancyTemp, ticket.getPrice()));
                }
            }
        }
        if (occupancyTemp > occupancy) {
            for (Ticket oneOfTicket : allTicketsOfFlight) {
                if (!oneOfTicket.isSold()) {
                    oneOfTicket.setPrice(checkOccupancyAndDecrementPrice(occupancy, occupancyTemp, ticket.getPrice()));
                }
            }
        }

        ticketRepo.save(ticket);
        flightRepo.save(flight);

    }

    public double checkOccupancyAndIncrementPrice(double occupancy, double occupancyTemp, double price) {
        if (0 <= occupancyTemp && occupancyTemp < 0.1 && occupancy >= 0.1) {
            return price * 1.1;
        } else if (0.1 <= occupancyTemp && occupancyTemp < 0.2 && occupancy >= 0.2) {
            return price * 1.1;
        } else if (0.2 <= occupancyTemp && occupancyTemp < 0.3 && occupancy >= 0.3) {
            return price * 1.1;
        } else if (0.3 <= occupancyTemp && occupancyTemp < 0.4 && occupancy >= 0.4) {
            return price * 1.1;
        } else if (0.4 <= occupancyTemp && occupancyTemp < 0.5 && occupancy >= 0.5) {
            return price * 1.1;
        } else if (0.5 <= occupancyTemp && occupancyTemp < 0.6 && occupancy >= 0.6) {
            return price * 1.1;
        } else if (0.6 <= occupancyTemp && occupancyTemp < 0.7 && occupancy >= 0.7) {
            return price * 1.1;
        } else if (0.7 <= occupancyTemp && occupancyTemp < 0.8 && occupancy >= 0.8) {
            return price * 1.1;
        } else if (0.8 <= occupancyTemp && occupancyTemp < 0.9 && occupancy >= 0.9) {
            return price * 1.1;
        } else if (0.9 <= occupancyTemp && occupancyTemp < 1 && occupancy >= 1) {
            return price * 1.1;
        } else
            return price;
    }

    public double checkOccupancyAndDecrementPrice(double occupancy, double occupancyTemp, double price) {
        if (0 <= occupancy && occupancy < 0.1 && occupancyTemp >= 0.1) {
            return price / 1.1;
        } else if (0.1 <= occupancy && occupancy < 0.2 && occupancyTemp >= 0.2) {
            return price / 1.1;
        } else if (0.2 <= occupancy && occupancy < 0.3 && occupancyTemp >= 0.3) {
            return price / 1.1;
        } else if (0.3 <= occupancy && occupancy < 0.4 && occupancyTemp >= 0.4) {
            return price / 1.1;
        } else if (0.4 <= occupancy && occupancy < 0.5 && occupancyTemp >= 0.5) {
            return price / 1.1;
        } else if (0.5 <= occupancy && occupancy < 0.6 && occupancyTemp >= 0.6) {
            return price / 1.1;
        } else if (0.6 <= occupancy && occupancy < 0.7 && occupancyTemp >= 0.7) {
            return price / 1.1;
        } else if (0.7 <= occupancy && occupancy < 0.8 && occupancyTemp >= 0.8) {
            return price / 1.1;
        } else if (0.8 <= occupancy && occupancy < 0.9 && occupancyTemp >= 0.9) {
            return price / 1.1;
        } else if (0.9 <= occupancy && occupancy < 1 && occupancyTemp >= 1) {
            return price / 1.1;
        } else
            return price;
    }


}
