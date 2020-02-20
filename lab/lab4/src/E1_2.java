import java.util.Scanner;

public class E1_2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please input the precision:");
        double per = sc.nextDouble();
        double pi = 0;
//        int n = 0;
        int x;
        int n = 0;
        for(;(4.0/(2.0*n+1)) >= per;n++)
            { int a = n%2;
            if(a == 0)x= 1;
            else x = -1;
            pi = pi+x*4.0/(2*n+1); }
        System.out.println("The estimation of Pi is:"+pi);
        System.out.println("The number of  iterations is:"+n);}
}
