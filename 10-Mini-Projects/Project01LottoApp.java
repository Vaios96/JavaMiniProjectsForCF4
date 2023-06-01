package gr.aueb.cf.projects;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;

/**
 * This is a Lotto-like app. The app takes a file as input (with the assumptions that there are more than 6 integers in it and simultaneously less than 50. Also the integers are in range of 1-49).
 * The numbers are inserted in a table, the table is sorted and then all the combinations of 6 numbers are found.
 * The combinations are filtered as there should not be any combinations that have 4 or more odd or even numbers, there should be up to 2 following numbers,
 * there should be up to 3 same ending numbers and also up to 3 numbers from the same set of ten.
 */
public class Project01LottoApp {

    public static void main(String[] args) {

        try (Scanner in = new Scanner(new File("C:/tmp/lotto6in.txt"));
             PrintStream ps = new PrintStream("C:/tmp/lotto6out.txt", StandardCharsets.UTF_8)) {

            final int LOTTO_SIZE = 6;
            int[] inputNumbers = new int[49];
            int pivot = 0;
            int[] result = new int[6];
            int num;
            int window;
            final int EVEN_OR_ODD_THRESHOLD = 4;
            final int CONTINUOUS_THRESHOLD = 2;
            final int SAME_ENDING_THRESHOLD = 3;
            final int SAME_TEN_SET = 3;


            while ((num = in.nextInt()) != -1 && pivot <= 48) {
                inputNumbers[pivot] = num;
                pivot++;
            }

            int[] numbers = Arrays.copyOfRange(inputNumbers, 0, pivot);
            Arrays.sort(numbers);

            window = pivot - LOTTO_SIZE;

            for (int i = 0; i <= window; i++){
                for (int j = i + 1; j <= window + 1; j++) {
                    for (int k = j + 1; k <= window + 2; k++) {
                        for (int a = k + 1; a <= window + 3; a++) {
                            for (int b = a + 1; b <= window + 4; b++) {
                                for (int c = b + 1; c <= window + 5; c++) {
                                    result[0] = numbers[i];
                                    result[1] = numbers[j];
                                    result[2] = numbers[k];
                                    result[3] = numbers[a];
                                    result[4] = numbers[b];
                                    result[5] = numbers[c];

                                    if (areLessThanThresholdEven(result, EVEN_OR_ODD_THRESHOLD) && areLessThanThresholdOdd(result, EVEN_OR_ODD_THRESHOLD) && areUpToThresholdContinuous(result, CONTINUOUS_THRESHOLD) &&
                                            areUpToThresholdEnding(result, SAME_ENDING_THRESHOLD) && areUpToThresholdSameTenSet(result, SAME_TEN_SET)) {

                                        ps.printf("%d %d %d %d %d %d\n", result[0], result[1], result[2], result[3], result[4], result[5]);
                                    }
                                }
                            }
                        }
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean areLessThanThresholdEven(int[] arr, int threshold) {
        int count = 0;

        for (int num : arr) {
            if (num % 2 == 0) {
                count++;
            }
        }

        return count <= threshold;
    }

    public static boolean areLessThanThresholdOdd(int[] arr, int threshold) {
        int count = 0;

        for (int num : arr) {
            if (num % 2 != 0) {
                count++;
            }
        }

        return count <= threshold;
    }

    public static boolean areUpToThresholdContinuous(int[] arr, int threshold) {
        int count = 0;

        for (int i = 0; i < arr.length - 2; i++) {
            if (arr[i] + 1 == arr[i+1]) {
                count++;
            }
        }

        return count <= threshold;
    }

    public static boolean areUpToThresholdEnding(int[] arr, int threshold) {
        int[][] count = new int[arr.length][2];
        int counter = 0;

        for (int i = 0; i < arr.length; i++) {
            count[i][0] = arr[i] % 10;
            count[i][1] = 0;
        }

        for (int j = 0; j < count.length; j++){
            for (int k = 0; k < count.length; k++) {
                if (count[k][0] == count[j][0]) {
                    count[j][1]++;
                    if (count[j][1] > threshold) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public static boolean areUpToThresholdSameTenSet(int[] arr, int threshold) {
        int[][] count = new int[arr.length][2];
        int counter = 0;

        for (int i = 0; i < arr.length; i++) {
            count[i][0] = arr[i] / 10;
            count[i][1] = 0;
        }

        for (int j = 0; j < count.length; j++){
            for (int k = 0; k < count.length; k++) {
                if (count[k][0] == count[j][0]) {
                    count[j][1]++;
                    if (count[j][1] > threshold) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

}
