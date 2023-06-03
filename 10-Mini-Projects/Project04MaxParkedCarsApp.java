package gr.aueb.cf.projects;

/**
 * A small app that counts the maximum amount of parked cards at any moment of the day. Given an array of arrival and departure times,
 * the app creates an array of twice the size. The first column of the created array is filled with each element of the provided one.
 * The second one is filled with 1 if a car was arriving at that time, else with 0. Then the created array is sorted based on the time
 * of each action. Finally, the counter finds the maximum amount of cars that were simultaneously parked.
 */
public class Project04MaxParkedCarsApp {

    public static void main(String[] args) {

        int[][] carArrivalDepartureTime = {{1012, 1112}, {1047, 1254}, {1102, 1131}, {1115, 1357}};
        int[][] arrayForComparisons = new int[8][2];
        int counter = 0;

        initializeComparisonsArray(carArrivalDepartureTime, arrayForComparisons);
        sortArray(arrayForComparisons);
        counter = countMaxParked(arrayForComparisons);

        System.out.println("The maximum amount of parked cars at the same time was: " + counter + "!");
    }

    /**
     * This method takes the original array and creates a new one for comparisons. Each element of originalArray will take its place in comparisonArray[] and comparisonArray[][] will be filled with
     * 1s and 0s representing arrival and departure respectively.
     *
     * @param originalArray     The original array of arrivals and departures
     * @param comparisonArray   The array that is created so that analysis is easier
     */
    public static void initializeComparisonsArray(int[][] originalArray, int[][] comparisonArray) {

        for (int i = 0; i < comparisonArray.length; i ++) {

            // If i%2 == 0 then the car had arrived, else it had departed. Considering that we have doubled the size of the original array, every two loops we will
            // fill the comparison Array with data from 1 row of the Original array. Therefore, we use originalArray[i / 2][0] for arrivals and originalArray[i / 2][1] for departures.
            if (i % 2 == 0) {
                comparisonArray[i][0] = originalArray[i / 2][0];
                comparisonArray[i][1] = 1;
            } else {
                comparisonArray [i][0] = originalArray[i / 2][1];
                comparisonArray[i][1] = 0;
            }
        }
    }

    public static void sortArray(int[][] array) {
        int tmp1;
        int tmp2;
        int lowestIndex;

        for (int i = 0; i < array.length - 1; i++) {
            tmp1 = array[i][0];
            tmp2 = array[i][1];
            lowestIndex = i;

            for (int j = i + 1; j < array.length; j++) {
                if (array[j][0] < tmp1) {
                    lowestIndex = j;
                    tmp1 = array[j][0];
                    tmp2 = array[j][1];
                }
            }

            if (lowestIndex != i) {
                array[lowestIndex][0] = array[i][0];
                array[lowestIndex][1] = array[i][1];
                array[i][0] = tmp1;
                array[i][1] = tmp2;
            }
        }
    }

    public static int countMaxParked(int[][] array) {
        int counter = 0;
        int maxAmount = 0;

        for (int[] ints : array) {
            if (ints[1] == 1) {
                counter++;

                if (maxAmount < counter) {
                    maxAmount = counter;
                }
            } else {
                counter--;
            }
        }

        return maxAmount;
    }
}
