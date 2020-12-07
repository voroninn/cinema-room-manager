package cinema;

import java.util.Scanner;

public class Cinema {

    private static CinemaHall cinemaHall;
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seatsInRow = scanner.nextInt();
        cinemaHall = new CinemaHall(rows, seatsInRow);
        printMenu();
    }

    private static void printMenu() {
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
        processCommand(scanner.nextInt());
    }

    static void processCommand(int commandNumber) {
        switch (commandNumber) {
            case 1:
                cinemaHall.printSeatingArrangement();
                System.out.println();
                break;
            case 2:
                System.out.println("\nEnter a row number:");
                int selectedRow = scanner.nextInt();
                System.out.println("Enter a seat number in that row:");
                int selectedSeat = scanner.nextInt();
                cinemaHall.buyTicket(selectedRow, selectedSeat);
                break;
            case 3:
                cinemaHall.printStatistics();
                System.out.println();
                break;
            case 0:
                return;
            default:
                System.out.println("\nPlease enter a valid command number\n");
        }
        printMenu();
    }
}
