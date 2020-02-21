import java.util.Scanner;

public class A3Q5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int n = 2;
        int[][] matrix = new int[a][b];
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < b; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }
        sc.close();
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < b; j++) {
                if (matrix[i][j] == 1) {
                    note(matrix, i, j, n);
                    n++;
                }
            }
        }
        int[] count = new int[n - 2];
        int max = 0;
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < b; j++) {
                if (matrix[i][j] > 1) {
                    count[matrix[i][j] - 2]++;
                }
            }
        }
        for (int i = 0; i < n - 2; i++) {
            max = Math.max(max, count[i]);
        }
        System.out.println(max);
    }

    public static void note(int[][] matrix, int x, int y, int n) {
        if (x > 0 && matrix[x - 1][y] == 1) {
            matrix[x - 1][y] = n;
            note(matrix, x - 1, y, n);
        }
        if (x < matrix.length - 1 && matrix[x + 1][y] == 1) {
            matrix[x + 1][y] = n;
            note(matrix, x + 1, y, n);
        }
        if (y > 0 && matrix[x][y - 1] == 1) {
            matrix[x][y - 1] = n;
            note(matrix, x, y - 1, n);
        }
        if (y < matrix[0].length - 1 && matrix[x][y + 1] == 1) {
            matrix[x][y + 1] = n;
            note(matrix, x, y + 1, n);
        }
    }
}