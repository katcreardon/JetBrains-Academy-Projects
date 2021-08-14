package cinema;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class CinemaController {

    private final Cinema cinema = new Cinema(9, 9);
    private final List<Ticket> ticketList = new ArrayList<>();

    // Handles the GET request and returns the information about the movie theater (rows, columns, and available seats)
    @GetMapping("/seats")
    public Cinema getCinema() {
        return this.cinema;
    }

    // Handles the POST request and marks a booked ticket as purchased
    @PostMapping("/purchase")
    public Map<String, ?> postSeats(@RequestBody Seat seat) {
        int row = seat.getRow();
        int column = seat.getColumn();
        List<Seat> availableSeats = cinema.getAvailableSeats();

        if (row > 9 || row < 1 || column > 9 || column < 1) {
            // Respond with a 400 status code if users pass a wrong row/column number
            throw new CinemaException("The number of a row or a column is out of bounds!");
        }

        Seat selectedSeat = new Seat(row, column);

        // Check if seat is taken
        if (!availableSeats.contains(selectedSeat)) {
            // Respond with a 400 status code if seat is taken
            throw new CinemaException("The ticket has been already purchased!");
        } else {
            // Remove seat from list once it is purchased
            availableSeats.remove(selectedSeat);
            // Create ticket
            String token = UUID.randomUUID().toString();
            ticketList.add(new Ticket(selectedSeat, token));
            return Map.of(
                    "token", token,
                    "ticket", selectedSeat
            );
        }
    }

    // Handles the POST request and allows customers to refund their tickets
    @PostMapping("/return")
    public Map<String, ?> returnTicket(@RequestBody Ticket t) {
        String token = t.getToken();
        Ticket returnedTicket = null;

        // Find the ticket the token belongs to
        for (Ticket ticket : ticketList) {
            if (ticket.getToken().equals(token)) {
                returnedTicket = ticket;
            }
        }

        if (returnedTicket == null) {
            // Respond with a 400 status code if token is expired
            throw new CinemaException("Wrong token!");
        } else {
            // Remove returned ticket from the ticket list
            ticketList.remove(returnedTicket);
            Seat seat = returnedTicket.getSeat();
            // Add the seat back to the available seats
            cinema.getAvailableSeats().add(seat);
            return Map.of(
                    "returned_ticket", seat
            );
        }
    }

    // Handles the POST request with URL parameters and returns the movie theater statistics
    @PostMapping("/stats")
    public Map<String, ?> getStatistics(@RequestParam(required = false) String password) {
        int currentIncome = 0;
        int numSeats = 81;
        int numTickets = 0;

        if (password == null || !password.equals("super_secret")) {
            throw new NotAuthorizedException("The password is wrong!");
        } else {
            for (Ticket ticket : ticketList) {
                currentIncome += ticket.getSeat().getPrice();
                numSeats -= 1;
                numTickets += 1;
            }
            return Map.of(
                    "current_income", currentIncome,
                    "number_of_available_seats", numSeats,
                    "number_of_purchased_tickets", numTickets
            );
        }
    }
}
