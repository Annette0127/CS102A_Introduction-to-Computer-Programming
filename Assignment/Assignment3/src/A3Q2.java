import java.util.Scanner;

public class A3Q2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] input = new int[n];
        int maxl, xsl, maxr;
        int count = 0;
        for (int x = 0; x < n; x++) {
            input[x] = sc.nextInt();
        }
        sc.close();
        for (int i = 1; i < input.length; i++) {
            maxl = maxr = input[i];
            for (int j = 0; j < i; j++) {
                if (maxl < input[j]) {
                    maxl = input[j];
                }
            }
            for (int j = i+1; j < input.length; j++) {
                if (maxr < input[j]) {
                    maxr = input[j];
                }

            }
            xsl = Math.min(maxl, maxr) - input[i];
            count = xsl + count;
        }
        System.out.println(count);
    }
}
