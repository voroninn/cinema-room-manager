package cinema;

import java.util.Arrays;
import java.util.Locale;

public class CinemaHall {
    private final int rows;
    private final int seatsInRow;
    private final int totalSeats;
    private final char[][] seats;
    private int currentIncome = 0;

    public CinemaHall(int rows, int seatsInRow) {
        this.rows = rows;
        this.seatsInRow = seatsInRow;
        this.seats = new char[rows][seatsInRow];
        totalSeats = rows * seatsInRow;
        for (char[] row : seats) {
            Arrays.fill(row, 'S');
        }
    }

    void printSeatingArrangement() {
        System.out.println("\nCinema:");
        for (int i = 0; i <= rows; i++) {
            for (int j = 0; j <= seatsInRow; j++) {
                if (i == 0 && j == 0) {
                    System.out.print("  ");
                } else if (i == 0) {
                    System.out.print(j + " ");
                } else if (j == 0) {
                    System.out.print(i + " ");
                } else {
                    System.out.print(seats[i - 1][j - 1] + " ");
                }
            }
            System.out.println();
        }
    }

    void buyTicket(int selectedRow, int selectedSeat) {
        if (selectedRow <= 0 || selectedRow > rows || selectedSeat <= 0 || selectedSeat > seatsInRow) {
            System.out.println("\nWrong input!\n");
            return;
        }
        if (seats[selectedRow - 1][selectedSeat - 1] != 'B') {
            seats[selectedRow - 1][selectedSeat - 1] = 'B';
        } else {
            System.out.println("\nThat ticket has already been purchased\n");
            Cinema.processCommand(2);
        }
        int ticketPrice = 10;
        if (totalSeats > 60 && selectedRow > rows / 2) {
            ticketPrice = 8;
        }
        System.out.println("\nTicket price: $" + ticketPrice + "\n");
        currentIncome += ticketPrice;
    }

    void printStatistics() {
        int purchasedTickets = countPurchasedTickets();
        System.out.printf("%nNumber of purchased tickets: %d%n", purchasedTickets);
        System.out.printf(Locale.US, "Percentage: %.2f%%%n", purchasedTickets * 100.0 / totalSeats);
        System.out.printf("Current income: $%d%n", currentIncome);
        System.out.printf("Total income: $%d%n", calculateTotalIncome());
    }

    private int countPurchasedTickets() {
        int purchasedTickets = 0;
        for (char[] subArray : seats) {
            for (char arrayItem : subArray) {
                if (arrayItem == 'B') {
                    purchasedTickets++;
                }
            }
        }
        return purchasedTickets;
    }

    private int calculateTotalIncome() {
        if (totalSeats < 60) {
            return totalSeats * 10;
        } else {
            return rows / 2 * seatsInRow * 10 + ((totalSeats - rows / 2 * seatsInRow) * 8);
        }
    }
}
