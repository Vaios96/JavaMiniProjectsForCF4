package gr.aueb.cf.projects;

/**
 * A small app showcasing a shallow and a deep copy of a 2d array.
 */
public class Project07TwoDimensionsArrayShallowAndDeepCopy {

    public static void main(String[] args) {

        int[][] originalArray = new int[10][10];
        int[][] shallowCopy;
        int[][] deepCopy;

        initializeArray(originalArray);

        shallowCopy = creatingShallowCopy(originalArray);
        deepCopy = creatingDeepCopy(originalArray);

        System.out.println("The original array:");
        printArray(originalArray);
        System.out.println();

        for (int i = 0; i < shallowCopy.length; i++) {
            for (int j = 0; j < shallowCopy[i].length; j++) {
                shallowCopy[i][j] += 10;
            }
        }

        System.out.println("The shallow copy after the changes: ");
        printArray(shallowCopy);
        System.out.println();
        System.out.println("The effect of the shallow copy on the original array: ");
        printArray(originalArray);
        System.out.println();

        for (int k = 0; k < deepCopy.length; k++) {
            for (int a = 0; a < deepCopy[k].length; a++) {
                deepCopy[k][a] += 1000;
            }
        }

        System.out.println("The deep copy after the changes:");
        printArray(deepCopy);
        System.out.println();
        System.out.println("The unaffected original copy: ");
        printArray(originalArray);
    }

    public static void initializeArray(int[][] array) {
        int counter = 1;

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                array[i][j] = counter;
                counter++;
            }
        }
    }

    public static int[][] creatingShallowCopy(int[][] array) {
        int[][] shallow = new int[10][10];
        shallow = array.clone();

        return shallow;
    }

    public static int[][] creatingDeepCopy(int[][] array) {
        int[][] deep = new int[10][10];

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                deep[i][j] = array[i][j];
            }
        }

        return deep;
    }

    public static void printArray(int[][] array) {
        for (int[] i : array) {
            for (int j : i) {
                System.out.print(j + " ");
            }
        }
        System.out.println();
    }
}
