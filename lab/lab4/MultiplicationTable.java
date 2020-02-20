import java.util.Scanner;

public class MultiplicationTable {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int n = 0;
        while (true) {
            System.out.println("Please input a number to print the Multiplication Table [0 to terminate]:");
            n = input.nextInt();
            if (n == 0) {

            } else if (n < 0 || n > 9) {
                System.out.println("Please input a number between [1,9]");

            } else {
                for (int i = 1; i <= 9; i++) {
                    if (i > n) {

                    }
                    for (int j = 1; j <= 9; j++) {
                        if (j > i) {

                        }
                        System.out.printf("%d * %d = %2d  ", j, i, j * i);
                    }
                    System.out.println();
                }
            }
        }
        input.close();
    }
}