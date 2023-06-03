package gr.aueb.cf.projects;

/**
 * A small app following Kadane's algorithm to find the maximum subarray in an array.
 */
public class Project06MaxSubarrayApp {

    public static void main(String[] args) {
        int[] array = {-3, 7, 3, 2, -5, 15, -25, 8, 3, 9};
        int maxSubarray;

        maxSubarray = findMaxSubarray(array);

        System.out.println("The maximum subarray is: " + maxSubarray);
    }

    /**
     * This method follows Kadane's algorithm. There are 2 variables to store local and global maximums. The algorithm works as follows:
     * we iterate each element of the array, starting from the first element. If we sum the current element to the local maximum and the sum is greater than 0 we add it to the local subarray (so
     * if we got an array starting with a negative number or 0 they will not be added to the local subarray. The first positive number will mark the beginning of our first subarray
     * and if at any point the local maximum reaches 0 or get lower than that, we set the local maximum to 0 and continue our iterations).
     * On each iteration if the local maximum is greater than the global maximum, the global maximum is updated.
     *
     * @param array     the array from which we want to find the maximum subarray
     * @return          the maximum subarray
     */
    public static int findMaxSubarray(int[] array) {
        int localMax = 0;
        int globalMax = 0;

        if (array == null) return 0;

        for (int i : array) {
            if (localMax + i <= 0) {
                localMax = 0;
            } else {
                localMax += i;
            }

            if (localMax > globalMax) {
                globalMax = localMax;
            }
        }

        return globalMax;
    }
}
