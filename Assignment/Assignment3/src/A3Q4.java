import java.util.Scanner;

public class A3Q4 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int a = input.nextInt();
        int b = input.nextInt();
        int[][] matrix = new int[a][b];
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < b; j++) {
                matrix[i][j] = input.nextInt();
            }
        }
        int c = input.nextInt();
        int[][] pairs = new int[c][4];
        for (int i = 0; i < c; i++) {
            for (int j = 0; j < 4; j++) {
                pairs[i][j] = input.nextInt();
            }
        }
        input.close();
        int[] count = new int[c];
        for (int x = 0; x < c; x++) {
            for (int i = pairs[x][0]; i <= pairs[x][2]; i++) {
                for (int j = pairs[x][1]; j <= pairs[x][3]; j++) {
                    count[x] += matrix[i][j];
                }
            }
        }
        for (int i = 0; i < c; i++) {
            System.out.println(count[i]);
        }
    }
}