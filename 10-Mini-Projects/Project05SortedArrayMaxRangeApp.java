package gr.aueb.cf.projects;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This app finds the range, where the highest number is present, in a sorted array. The user decides how many random numbers
 * will be generated (between 10-100). The random values that will be calculated are between 1 and 20.
 */
public class Project05SortedArrayMaxRangeApp {

    public static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        int numbersToBeGenerated;
        int[] randomNumbers;
        int[] highestNumberRange;

        numbersToBeGenerated = getAmountOfNumbers();
        randomNumbers = new int[numbersToBeGenerated];

        populateAndSortArray(randomNumbers, numbersToBeGenerated);
        highestNumberRange = findHighestRange(randomNumbers);

        if (highestNumberRange[0] == highestNumberRange[1]) {
            System.out.printf("There was only one instance of the highest number, %d! It was in position: %d!", randomNumbers[randomNumbers.length - 1], randomNumbers.length);
        } else {
            System.out.printf("The highest number was: %d! It was found in range between %d and %d!\n", randomNumbers[randomNumbers.length - 1], highestNumberRange[0], highestNumberRange[1]);
        }

        System.out.println("For confirmation here are the random generated numbers: ");
        for (int i = 0; i < randomNumbers.length; i++) {
            System.out.print(randomNumbers[i] + " ");
            if (i % 20 == 0 && i != 0){
                System.out.println();
            }
        }
    }

    public static int getAmountOfNumbers() {
        int amount;

        while (true) {
            try {
                System.out.println("Please insert the amount of numbers you would like to be generated(10-100): ");
                amount = in.nextInt();
                if (amount < 10 || amount > 100) {
                    throw new IllegalArgumentException();
                }
                break;
            } catch (InputMismatchException e) {
                in.nextLine();
                System.out.println("You must insert a number between 10 and 100");
            } catch (IllegalArgumentException e) {
                System.out.println("You must insert a number between 10 and 100");
            }
        }

        return amount;
    }

    public static void populateAndSortArray(int[] arr, int amount) {

        for (int i = 0; i < amount; i++) {

            arr[i] = (int) (Math.random() * 20) + 1;
        }

        Arrays.sort(arr);
    }

    public static int[] findHighestRange(int[] arr) {
        int[] highestPositions = new int[2];
        int highestEnd = arr.length - 1;
        int highestStart = -1;
        boolean continueCalculations = false;

        do {
            // The calculations start from the second to last position as the array is already sorted and
            // it is certain that the length is at least 10
            for (int i = arr.length - 2; i >= 0; i--) {
                if (arr[i] == arr[highestEnd]) {
                    highestStart = i;
                } else {
                    continueCalculations = true;
                }
            }
        } while (!continueCalculations);

        if (highestStart == -1) {
            highestStart = highestEnd;
        }

        // Adding one to provide user friendly feedback
        highestPositions[0] = highestStart + 1;
        highestPositions[1] = highestEnd + 1;

        return highestPositions;
    }
}
