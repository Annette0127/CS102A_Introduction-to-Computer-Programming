import java.util.Arrays;

public class Demo {

    public static void main(String[] args) {
        int[] array1 = {1, 2, 3, 4, 5};
        int[] array2 = new int[5];
        array2[0] = 6;
        array2[1] = 7;
        array2[2] = 8;
        array2[3] = 9;
        array2[4] = 10;

        // print the elements of array1 using a conventional for loop
        for(int i = 0; i < array1.length; i++) {
            System.out.print(array1[i] + "\t");
        }
        System.out.println();

        // print the elements of array2 using a for-each loop
        for(int e : array2) {
            System.out.print(e + "\t");
        }
        System.out.println();

        // print the elements of the arrays arrays using Arrays.toString() method
        System.out.println("array1: " + Arrays.toString(array1));
        System.out.println("array2: " + Arrays.toString(array2));

        // assignment
        int[] array3 = null;
        array3 = array2;
        System.out.println(array3);
        System.out.println(array2);

        // can the first for-each loop change the value of array elements?
        for(int e : array2) {
            e = 0;
        }
        System.out.println("array2: " + Arrays.toString(array2));

        // change the array elements using a conventional for loop
        for(int i = 0; i < array2.length; i++) {
            array2[i] = 0;
        }
        System.out.println("array2: " + Arrays.toString(array2));

        // find the largest value in an array
        System.out.printf("max of array1: %d\n", findMax(array1));
    }

    public static int findMax(int[] array) {
        int max = Integer.MIN_VALUE;
        for(int e : array) {
            if(e > max) {
                max = e;
            }
        }
        return max;
    }
}