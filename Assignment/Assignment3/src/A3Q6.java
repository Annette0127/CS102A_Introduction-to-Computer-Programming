import java.util.Scanner;

public class A3Q6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int[][] matrix = new int[a][b];
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < b; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }
        sc.close();
        int min = road(matrix,0,0);
        System.out.println(min);
    }

    public static int road(int[][] matrix, int x, int y) {
        if (x == matrix.length - 1 && y == matrix[0].length - 1) {
            return matrix[x][y];
        } else if (y == matrix[0].length - 1) {
            return matrix[x][y] + road(matrix, x + 1, y);
        } else if (x == matrix.length - 1) {
            return matrix[x][y] + road(matrix, x, y + 1);
        } else {
            return matrix[x][y]+Math.min(road(matrix, x, y + 1), road(matrix, x + 1, y));
        }
    }
}
