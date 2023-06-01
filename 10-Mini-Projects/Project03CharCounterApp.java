package gr.aueb.cf.projects;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;

/**
 * This app reads every character of a file and  counts how many times it exists in it. It only reads up to 256 characters,
 * so in case there are more in the file it will only count the first 256 characters that it reads.
 * Spaces and special characters are also included.
 */
public class Project03CharCounterApp {

    public static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {

        try (FileReader fileReader = new FileReader("C:/tmp/random-text.txt")) {

            char[][] inputChars = new char[256][2];
            int counter = 0;
            int intReaded;
            char currentChar;
            boolean existing;
            int existingIndex;

            while (((intReaded = fileReader.read()) != -1) && counter != 256) {
                currentChar = (char) intReaded;
                existing = false;
                existingIndex = -1;

                for (int i = 0; i <= counter; i++) {
                    if (inputChars[i][0] == currentChar) {
                        existing = true;
                        existingIndex = i;
                        break;
                    }
                }

                if (!existing) {
                    inputChars[counter][0] = currentChar;
                    inputChars[counter][1]++;
                    counter++;
                } else {
                    inputChars[existingIndex][1]++;
                }
            }

            char[][] existingChars = new char[counter][2];
            for (int i = 0; i < counter; i++) {
                existingChars[i][0] = inputChars[i][0];
                existingChars[i][1] = inputChars[i][1];
            }

            printSortedAlphabetically(existingChars);
            printSortedByTimesInFile(existingChars);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printSortedAlphabetically(char[][] listOfCharsAndTimes) {
        int smallest;
        int smallestTimes;
        int smallestIndex;
        int tmp;
        int tmpTimes;

        for (int i = 0; i < listOfCharsAndTimes.length - 1; i++) {
            smallest = listOfCharsAndTimes[i][0];
            smallestTimes = listOfCharsAndTimes[i][1];
            smallestIndex = i;

            for (int j = i + 1; j < listOfCharsAndTimes.length; j++) {
                if (listOfCharsAndTimes[j][0] < smallest) {
                    smallest = listOfCharsAndTimes[j][0];
                    smallestTimes = listOfCharsAndTimes[j][1];
                    smallestIndex = j;
                }
            }

            tmp = listOfCharsAndTimes[i][0];
            tmpTimes = listOfCharsAndTimes[i][1];
            listOfCharsAndTimes[i][0] = (char) smallest;
            listOfCharsAndTimes[i][1] = (char) smallestTimes;
            listOfCharsAndTimes[smallestIndex][0] = (char) tmp;
            listOfCharsAndTimes[smallestIndex][1] = (char) tmpTimes;

        }

        System.out.println("Here is the list of characters and the times they exist in the file (following ASCII order): ");
        for (char[] listOfCharsAndTime : listOfCharsAndTimes) {
            if (listOfCharsAndTime[1] == (int) 1){
                System.out.println(listOfCharsAndTime[0] + " is found " + (int) listOfCharsAndTime[1] + " time");
            } else {
                System.out.println(listOfCharsAndTime[0] + " is found " + (int) listOfCharsAndTime[1] + " times");
            }
        }
    }

    public static void printSortedByTimesInFile(char[][] listOfCharsAndTimes) {
        int highest;
        int highestTimes;
        int highestIndex;
        int tmp;
        int tmpTimes;

        for (int i = 0; i < listOfCharsAndTimes.length - 1; i++) {
            highest = listOfCharsAndTimes[i][0];
            highestTimes = listOfCharsAndTimes[i][1];
            highestIndex = i;

            for (int j = i + 1; j < listOfCharsAndTimes.length; j++) {
                if (listOfCharsAndTimes[j][1] > highestTimes) {
                    highest = listOfCharsAndTimes[j][0];
                    highestTimes = listOfCharsAndTimes[j][1];
                    highestIndex = j;
                }
            }

            tmp = listOfCharsAndTimes[i][0];
            tmpTimes = listOfCharsAndTimes[i][1];
            listOfCharsAndTimes[i][0] = (char) highest;
            listOfCharsAndTimes[i][1] = (char) highestTimes;
            listOfCharsAndTimes[highestIndex][0] = (char) tmp;
            listOfCharsAndTimes[highestIndex][1] = (char) tmpTimes;

        }

        System.out.println("Here is the list of characters and the times they exist in the file ordered by their frequency: ");
        for (char[] listOfCharsAndTime : listOfCharsAndTimes) {
            if (listOfCharsAndTime[1] == (int) 1){
                System.out.println(listOfCharsAndTime[0] + " is found " + (int) listOfCharsAndTime[1] + " time");
            } else {
                System.out.println(listOfCharsAndTime[0] + " is found " + (int) listOfCharsAndTime[1] + " times");
            }
        }
    }
}
