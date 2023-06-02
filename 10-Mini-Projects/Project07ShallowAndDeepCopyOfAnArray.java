package gr.aueb.cf.projects;

/**
 * A small showcase of the difference between shallow and deep copy of an array.
 */
public class Project07ShallowAndDeepCopyOfAnArray {

    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 4, 5};
        int[] shallowCopy;
        int[] deepCopy = new int[5];

        shallowCopy = arr;

        System.out.println("The original array: ");
        printArray(arr);
        System.out.println("The shallow copy: ");
        printArray(shallowCopy);

        System.arraycopy(arr, 0, deepCopy, 0, arr.length);

        shallowCopy[0] = 10;

        System.out.println("The shallow copy after a change in it: ");
        printArray(shallowCopy);
        System.out.println("The original array after a change in shallow copy: ");
        printArray(arr);

        deepCopy[2] = 100;

        System.out.println("The deep copy after a change: ");
        printArray(deepCopy);
        System.out.println("The original array after a change in deep copy: ");
        printArray(arr);
    }

    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
