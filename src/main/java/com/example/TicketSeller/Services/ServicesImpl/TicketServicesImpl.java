package com.example.TicketSeller.Services.ServicesImpl;

import com.example.TicketSeller.Dto.TicketRequest;
import com.example.TicketSeller.Dto.TicketResponse;
import com.example.TicketSeller.Entities.Flight;
import com.example.TicketSeller.Entities.Ticket;
import com.example.TicketSeller.Repositories.FlightRepo;
import com.example.TicketSeller.Repositories.TicketRepo;
import com.example.TicketSeller.Services.TicketService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TicketServicesImpl implements TicketService {

    private final TicketRepo ticketRepo;
    private final FlightRepo flightRepo;
    private final ModelMapper modelMapper;

    public TicketServicesImpl(TicketRepo ticketRepo, FlightRepo flightRepo, ModelMapper modelMapper) {
        this.ticketRepo = ticketRepo;
        this.flightRepo = flightRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addTicket(List<TicketRequest> ticketRequests) {
        for (TicketRequest ticketRequest : ticketRequests) {
            ticketRequest.setFlight(flightRepo.findFlightByFlightCode(ticketRequest.getFlight().getFlightCode()));
            Ticket ticket = modelMapper.map(ticketRequest, Ticket.class);
            ticketRepo.save(ticket);
        }
    }

    @Override
    public TicketResponse findTicketByTicketNo(String ticketNo) {
        Ticket ticket = ticketRepo.findTicketByTicketNo(ticketNo);
        return modelMapper.map(ticket, TicketResponse.class);
    }

    @Override
    public List<TicketResponse> getAllTicketResponsesOfFlight(String flightCode) {
        Flight flight = flightRepo.findFlightByFlightCode(flightCode);
        int flightId = flight.getId();

        List<Ticket> ticketList = ticketRepo.findTicketsByFlight_IdOrderByTicketNo(flightId);
        List<TicketResponse> ticketResponses = new ArrayList<>();

        for (Ticket ticket : ticketList) {
            ticketResponses.add(modelMapper.map(ticket, TicketResponse.class));
        }

        return ticketResponses;
    }

    @Override
    public List<Ticket> getAllTicketsOfFlight(String flightCode) {
        Flight flight = flightRepo.findFlightByFlightCode(flightCode);
        int flightId = flight.getId();

        return ticketRepo.findTicketsByFlight_IdOrderByTicketNo(flightId);
    }

    @Override
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

        List<Ticket> allTicketsOfFlight = getAllTicketsOfFlight(flight.getFlightCode());

        if (occupancy > occupancyTemp) {
            for (Ticket oneOfTicket : allTicketsOfFlight) {
                if (!oneOfTicket.isSold()) {
                    oneOfTicket.setPrice(checkOccupancyAndIncrementPrice(occupancy, occupancyTemp, oneOfTicket.getPrice()));
                }
            }
        }
        if (occupancyTemp > occupancy) {
            for (Ticket oneOfTicket : allTicketsOfFlight) {
                if (!oneOfTicket.isSold()) {
                    oneOfTicket.setPrice(checkOccupancyAndDecrementPrice(occupancy, occupancyTemp, oneOfTicket.getPrice()));

                }
            }
        }

        ticketRepo.save(ticket);
        flightRepo.save(flight);

    }

    @Override
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

        flightRepo.save(flight);

        List<Ticket> allTicketsOfFlight = getAllTicketsOfFlight(flight.getFlightCode());

        if (occupancy > occupancyTemp) {
            for (Ticket oneOfTicket : allTicketsOfFlight) {
                if (!oneOfTicket.isSold()) {
                    oneOfTicket.setPrice(checkOccupancyAndIncrementPrice(occupancy, occupancyTemp, oneOfTicket.getPrice()));
                }
            }
        }
        if (occupancyTemp > occupancy) {
            for (Ticket oneOfTicket : allTicketsOfFlight) {
                if (!oneOfTicket.isSold()) {
                    oneOfTicket.setPrice(checkOccupancyAndDecrementPrice(occupancy, occupancyTemp, oneOfTicket.getPrice()));
                }
            }
        }

        ticketRepo.save(ticket);
        flightRepo.save(flight);

    }

    @Override
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

    @Override
    public double checkOccupancyAndDecrementPrice(double occupancy, double occupancyTemp, double price) {

        if (occupancyTemp > 0.9 && occupancy <= 0.9) {
            return price / 1.1;
        } else if (occupancyTemp > 0.8 && occupancy <= 0.8) {
            return price / 1.1;
        } else if (occupancyTemp > 0.7 && occupancy <= 0.7) {
            return price / 1.1;
        } else if (occupancyTemp > 0.6 && occupancy <= 0.6) {
            return price / 1.1;
        } else if (occupancyTemp > 0.5 && occupancy <= 0.5) {
            return price / 1.1;
        } else if (occupancyTemp > 0.4 && occupancy <= 0.4) {
            return price / 1.1;
        } else if (occupancyTemp > 0.3 && occupancy <= 0.3) {
            return price / 1.1;
        } else if (occupancyTemp > 0.2 && occupancy <= 0.2) {
            return price / 1.1;
        } else if (occupancyTemp > 0.1 && occupancy <= 0.1) {
            return price / 1.1;
        } else if (occupancyTemp > 0.0 && occupancy <= 0.0) {
            return price / 1.1;
        } else
            return price;
    }
}
