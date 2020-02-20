public class E3 {
    public static void main(String[] args) {
        int i = 1;
        while (i <= 9) {
            int j = 1;
            while (j <= i) {
                int k = i*j;
                System.out.printf("%2d",j);
                j++;
                System.out.print("*");
                System.out.printf("%2d",i);
                System.out.print("=");
                System.out.printf("%2d   ",k);
                           }
            System.out.println();
            i++;
        }
    }
}
