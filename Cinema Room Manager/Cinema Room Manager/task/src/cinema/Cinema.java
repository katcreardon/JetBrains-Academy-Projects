package cinema;

import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        // Write your code here
        char[][] theatre = initSeats();
//        calculateProfit();
//        calculateTicketPrice(theatre);
        showMenu(theatre);
    }

    static char[][] initSeats() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rows = in.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = in.nextInt();
        char[][] arr = new char[rows][seats];

//        System.out.print("\nCinema:\n  ");
//        for (int i = 1; i <= seats; i++) {
//            System.out.printf("%d ", i);
//        }
//        System.out.println();
        for (int i = 0; i < rows; i++) {
//            System.out.printf("%d ", i + 1);
            for (int j = 0; j < seats; j++) {
                arr[i][j] = 'S';
//                System.out.printf("%c ", 'S');
            }
//            System.out.println();
        }
//        System.out.println();
        return arr;
    }

    static char[][] printSeats(char[][] arr) {
        // Print theatre
        System.out.print("\nCinema:\n  ");
        for (int i = 1; i <= arr[0].length; i++) {
            System.out.printf("%d ", i);
        }
        System.out.println();
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("%d ", i + 1);
            for (int j = 0; j < arr[i].length; j++) {
                System.out.printf("%c ", arr[i][j]);
            }
            System.out.println();
        }
        return arr;
    }

    static int calculateTotalIncome(char[][] arr) {
        int rows = arr.length;
        int seats = arr[0].length;
        int totalSeats = rows * seats;
        int profit;

        if (totalSeats < 60) {
            profit = totalSeats * 10;
        } else if (totalSeats % 2 == 0) {
            profit = (totalSeats / 2 * 10) + (totalSeats / 2 * 8);
        } else {
            int frontRows = rows / 2;
            int backRows = frontRows + 1;
            profit = (frontRows * seats * 10) + (backRows * seats * 8);
        }
        return profit;
    }

    static char[][] calculateTicketPrice(char[][] arr) {
        Scanner in = new Scanner(System.in);
        System.out.println("\nEnter a row number:");
        int rowNum = in.nextInt();
        System.out.println("Enter a seat number in that row:");
        int seatNum = in.nextInt();
        boolean isValid = false;

        while (!isValid) {
            if (rowNum > arr.length || rowNum < 1 || seatNum > arr[0].length || seatNum < 1) {
                System.out.println("\nWrong input!");
                System.out.println("\nEnter a row number:");
                rowNum = in.nextInt();
                System.out.println("Enter a seat number in that row:");
                seatNum = in.nextInt();
            } else if (arr[rowNum - 1][seatNum - 1] == 'B') {
                System.out.println("\nThat ticket has already been purchased!");
                System.out.println("\nEnter a row number:");
                rowNum = in.nextInt();
                System.out.println("Enter a seat number in that row:");
                seatNum = in.nextInt();
            } else {
                isValid = true;
            }
        }

        int totalSeats = arr.length * arr[0].length;
        int frontRows, backRows;
        // Determine which rows are front and back for pricing
        if (arr.length % 2 == 0) {
            frontRows = backRows = arr.length / 2;
        } else {
            frontRows = arr.length / 2;
            backRows = frontRows + 1;
        }
        // Calculate ticket price
        if (totalSeats < 60) {
            System.out.printf("%nTicket price: $%d%n", 10);
        } else if (rowNum <= frontRows) {
            System.out.printf("%nTicket price: $%d%n", 10);
        } else {
            System.out.printf("%nTicket price: $%d%n", 8);
        }
        // Change seat to bought
        arr[rowNum - 1][seatNum - 1] = 'B';
//        // Print theatre
//        System.out.print("\nCinema:\n  ");
//        for (int i = 1; i <= arr[0].length; i++) {
//            System.out.printf("%d ", i);
//        }
//        System.out.println();
//        for (int i = 0; i < arr.length; i++) {
//            System.out.printf("%d ", i + 1);
//            for (int j = 0; j < arr[i].length; j++) {
//                System.out.printf("%c ", arr[i][j]);
//            }
//            System.out.println();
//        }
        return arr;
    }

    static int calculateCurrentIncome(char[][] arr) {
        int currIncome = 0;
        int totalSeats = arr.length * arr[0].length;
        int frontRows = arr.length / 2;
        // Check theatre for bought seats
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j] == 'B') {
                    if (totalSeats < 60) {
                        currIncome += 10;
                    } else if (i + 1 <= frontRows) {
                        currIncome += 10;
                    } else {
                        currIncome += 8;
                    }
                }
            }
        }
        return currIncome;
    }

    static void showStatistics(char[][] arr) {
        // Count tickets sold
        int tickets = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] == 'B') {
                    tickets++;
                }
            }
        }
        // Get percent of theatre seats sold
        double percent = (double) tickets / (arr.length * arr[0].length) * 100;
        // Calculate current income
        int currIncome = 0;
        if (tickets != 0) {
            currIncome = calculateCurrentIncome(arr);
        }
        // Calculate total potential income
        int totalIncome = calculateTotalIncome(arr);

        System.out.printf("\nNumber of purchased tickets: %d%n", tickets);
        System.out.printf("Percentage: %.2f%%%n", percent);
        System.out.printf("Current income: $%d%n", currIncome);
        System.out.printf("Total income: $%d%n", totalIncome);
    }

    static void showMenu(char[][] arr) {
        char[][] theatre = arr;
        Scanner in = new Scanner(System.in);
        int i = -1;
        while (i != 0) {
            System.out.println("\n1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            i = in.nextInt();
            switch (i) {
                case 1:
                    printSeats(theatre);
                    break;
                case 2:
                    theatre = calculateTicketPrice(theatre);
                    break;
                case 3:
                    showStatistics(theatre);
                    break;
            }

        }
    }
}