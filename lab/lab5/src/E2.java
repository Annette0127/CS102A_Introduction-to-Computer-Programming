import java.util.Scanner;

public class E2 {
    public static void main(String[] args){
        double[] ar = new double[10];
        Scanner sc = new Scanner(System.in);
        System.out.println("Please input 10 scores of these students:");
        for(int i=0;i<10;i++){
           ar[i] = sc.nextInt();
        }
        double max = 0;
        for(double e : ar) {
            if(e > max) {
                max = e;
            }
        }
        double min = 100;
        for(double e : ar) {
            if(e < min) {
                min = e;
            }
        }
        System.out.println(min+"  "+max);
        double sum = 0;
        for(int e = 0; e < 10;e++) {
            sum += ar[e];
        }
        System.out.print("Average score is:" + sum/10);

}
}
