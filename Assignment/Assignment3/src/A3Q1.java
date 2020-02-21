import java.util.Scanner;

public class A3Q1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String[] print = new String[n];
        for (int i = 0; i < n; i++) {
            String result = "";
            int row = sc.nextInt();
            int col = sc.nextInt();
            int[][] matrix = new int[col][row];
            for (int y = 0; y < row; y++) {
                for (int x = 0; x < col; x++) {
                    matrix[x][y] = sc.nextInt();
                }
            }

            if (row == 1) {
                for (int j = 0; j < col; j++) {
                    result += matrix[j][0] + " ";
                }
                print[i] = result;
            } else {
                for (int k = 0; k < col; k++) {
                    int gcdn = gcd(matrix[k]);
                    result += gcdn + " ";
                }
                print[i] = result;
            }
        }
        for (String e : print) {
            System.out.println(e);
        }
        sc.close();
    }

    public static int gcd1(int a, int b) {
        int x = Math.max(a, b);
        int y = Math.min(a, b);
        int c = x % y;
        x = y;
        y = c;
        if (y == 0) {
            return x;
        } else {
            return gcd1(x, y);
        }
    }

    public static int gcd(int[] gar) {
        int gcdNum = gcd1(gar[0], gar[1]);
        for (int i = 2; i < gar.length; i++) {
            gcdNum = gcd1(gcdNum, gar[i]);
        }
        return gcdNum;
    }
}

