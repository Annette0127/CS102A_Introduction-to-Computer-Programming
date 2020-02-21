import java.util.Scanner;

public class A3Q3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int a = input.nextInt();
        int b = input.nextInt();
        input.close();
        int direction = 0;
        int[] index = {0, b - 1};
        int[][] matrix = new int[a][b];
        for (int i = 1; i <= a * b; i++) {
            matrix[index[0]][index[1]] = i;
            go(index, direction);
            if (index[0] == a || index[1] == b || index[1] == -1 || matrix[index[0]][index[1]] != 0) {
                direction += 2;
                go(index, direction);
                direction--;
                go(index, direction);
            }
        }
        for (int[] e : matrix) {
            for (int t : e) {
                System.out.printf("%-3d",t);
            }
            System.out.println();
        }
    }

    public static void go(int[] index, int direction) {
        switch (direction % 4) {
            case 0:
                index[1]--;
                break;
            case 1:
                index[0]++;
                break;
            case 2:
                index[1]++;
                break;
            case 3:
                index[0]--;
        }
    }


}