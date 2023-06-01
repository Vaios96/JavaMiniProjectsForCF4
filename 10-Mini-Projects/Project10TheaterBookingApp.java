package gr.aueb.cf.projects;

import java.util.Scanner;

/**
 * Welcome to CF4's theater. The theater consists of 30 seats rows and 12 seat columns. With this app
 * you are able to book your seat in theater. The seats are represented by a boolean 2 dimensional table.
 * False means that the seat is available to be booked. True means that someone already booked the seat.
 * You will be prompted by a menu to choose you action (book, cancel, check available seats, quit) and then asked
 * for the necessary information.
 */
public class Project10TheaterBookingApp {

    public static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {

        boolean[][] seats = new boolean[30][12];
        String choice;
        boolean quit = false;
        int action = 0;
        String seatForAction;
        char column;
        int row;

        do {
            printMenu();
            choice = getMenuChoice().trim();

            // If choice is Q/q program will finish normally without any other actions, if not and is not between 1-3, it will be caught as a Number Format Exception when "action = Integer.parseInt(choice);" tries to be executed
            if (choice.matches("[qQ]") || choice.equals("Q/q")) quit = true;
            else {
                // If the user does not want to quit, the program checks if the user's action is valid. If not it informs the user and continues the loop, if it is a valid choice it proceeds with the
                // proper actions checking that everything is in range with the use of methods. If a not valid seat is given it informs the user about the valid input range and continues with the loop
                try {
                    action = Integer.parseInt(choice);
                    if (!isValid(action)) {
                        throw new IllegalArgumentException("Error - Choice must be between 1 - 3 or Q/q");
                    } else {
                        switch (action) {
                            case 1:
                                seatForAction = getSeat();
                                if (isSeatValid(seatForAction)) {
                                    isSeatValid(seatForAction);
                                    column = seatForAction.charAt(0);
                                    row = Integer.parseInt(seatForAction.substring(1));
                                } else {
                                    throw new IllegalArgumentException("You should provide a seat in the range of A-L and 1-30 like the following: C8 or A27");
                                }

                                bookSeat(column, row, seats);
                                break;
                            case 2:
                                seatForAction = getSeat();
                                if (isSeatValid(seatForAction)) {
                                    column = seatForAction.charAt(0);
                                    row = Integer.parseInt(seatForAction.substring(1));
                                    cancelBooking(column, row, seats);
                                    break;
                                } else {
                                    throw new IllegalArgumentException("You should provide a seat in the range of A-L and 1-30 like the following: C8 or A27");
                                }
                            case 3:
                                printAvailable(seats);
                                break;
                            default:
                                throw new IllegalArgumentException("Sorry! I do not know where I messed up!");
                        }
                    }
                } catch (NumberFormatException e) {
                    System.out.println("The choice should be between 1-3 or Q/q");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }

        } while (!quit);
    }

    /**
     * The following method prints the menu of choices the user can make
     */
    public static void printMenu() {
        System.out.println("Welcome to CF4's Theater. What would you like to do? :");
        System.out.println("1. Book a seat");
        System.out.println("2. Cancel my booked seat");
        System.out.println("3. Check available seats");
        System.out.println("Q/q Quit");
    }

    /**
     * Prints the seats of the theater that are available to book in green colour and the ones that are already booked in red
     *
     * @param arr   Represents the seats of the theater, true means the seat is booked and false means it is booked
     */
    public static void printAvailable(boolean[][] arr) {
        for (int i = 11; i >= 0; i--){
            for (int j = 0; j < 30; j++){
                if (!arr[j][i]) {
                    System.out.print("\u001B[32m" + ((char) (i + 65)) + "" + (j + 1) + " " + "\u001B[0m");
                } else {
                    System.out.print("\u001B[31m" + ((char) (i + 65)) + "" + (j + 1) + " " + "\u001B[0m");
                }
            }
            System.out.println();
        }
    }

    /**
     * Books the seat provided by the user, if the seat is available. If the seat is already booked throws an Illegal Argument Exception
     *
     * @param column    the row of the theater that the user would like to book a seat (I got confused column should be row and row column)
     * @param row       the column of the theater that the user would like to book a seat (I got confused column should be row and row column)
     * @param arr       the seats of the theater represented by a boolean table. True means the seat is booked and false that the seat is available
     */
    public static void bookSeat(char column, int row, boolean[][] arr) {
        int columnNumber;

        if (column >= 'a' && column <= 'j') {
            columnNumber = column - 'a';
        } else if (column >= 'A' && column <= 'J') {
            columnNumber = column - 'A';
        } else if (column == 'k' || column =='K'){
            columnNumber = 10;
        } else {
            columnNumber = 11;
        }

        if (arr[row - 1][columnNumber]) {
            throw new IllegalArgumentException("Seat is already booked");
        } else {
            arr[row - 1][columnNumber] = true;
        }
    }

    /**
     * Cancels the booking of the seat provided by the user, if the seat is booked. If the seat is already free throws an Illegal Argument Exception
     *
     * @param column    the row of the theater that the user would like to book a seat (I got confused column should be row and row column)
     * @param row       the column of the theater that the user would like to book a seat (I got confused column should be row and row column)
     * @param arr       the seats of the theater represented by a boolean table. True means the seat is booked and false that the seat is available
     */
    public static void cancelBooking(char column, int row, boolean[][] arr) {
        int columnNumber;

        if (column >= 'a' && column <= 'j') {
            columnNumber = column - 'a';
        } else if (column >= 'A' && column <= 'J') {
            columnNumber = column - 'A';
        } else if (column == 'k' || column =='K'){
            columnNumber = 10;
        } else {
            columnNumber = 11;
        }

        if (!arr[row - 1][columnNumber]) {
            throw new IllegalArgumentException("This seat is already free");
        } else {
            arr[row - 1][columnNumber] = false;
        }
    }

    /**
     * Gets user's Menu choice
     *
     * @return  The choice of the user from the Menu
     */
    public static String getMenuChoice() {
        System.out.println("Εισάγετε επιλογή");
        return in.nextLine();
    }

    /**
     * Checks if the user's input is valid. A valid option is between the numbers 1-3 as there are only 3 options
     *
     * @param choice    user's choice
     * @return          if the choice is valid
     */
    public static boolean isValid(int choice) {
        return (choice >= 1 && choice <= 3);
    }

    /**
     * Gets the seat that the user would provide to move forward with his action (book or cancel booking)
     *
     * @return      a string representing the user's seat of choice
     */
    public static String getSeat() {
        System.out.println("What is the seat you would like to proceed with your action?");
        return in.nextLine();

    }

    public static boolean isSeatValid(String s) {
        char columnGiven;
        int rowGiven;
        boolean isRowValid;
        boolean isColumnValid;

        try {
            columnGiven = s.charAt(0);
            try {
                rowGiven = Integer.parseInt(s.substring(1));

            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("You should provide a seat in the range of A-L and 1-30 like the following: C8 or A27");
            }

        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("You should provide a seat in the range of A-L and 1-30 like the following: C8 or F15");
        }

        isRowValid = (rowGiven >= 1 && rowGiven <= 30);
        isColumnValid = ((int) columnGiven >= 'a' && (int) columnGiven <= 'l') || ((int) columnGiven >= 'A' && (int) columnGiven <= 'L');

        return isColumnValid && isRowValid;
    }
}
