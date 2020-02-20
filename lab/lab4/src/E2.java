import java.util.Scanner;

public class E2 {
    public static void main(String[] args){
        int sumf = 0;
        char a ;
        int cj ;
        int f = 0;
        double fi = 0;
        double grade = 0;
        double total = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("Please input the numbers:");
        while (f>=0){
            f = sc.nextInt();
            if(f==-1)break;
            else {
                cj = sc.nextInt();
                if (cj >= 90 && cj <= 100) {
                    a = 'A';
                } else if (cj >= 80 && cj <= 90) {
                    a = 'B';
                } else if (cj >= 70 && cj <= 80) {
                    a = 'C';
                } else if (cj >= 70 && cj <= 60) {
                    a = 'D';
                } else {
                    a = 'E';
                }
                sumf = f + sumf;
                switch (a) {
                    case 'A':
                        grade = 4.0;
                        break;
                    case 'B':
                        grade = 3.0;
                        break;
                    case 'C':
                        grade = 2.0;
                        break;
                    case 'D':
                        grade = 1.0;
                        break;
                    case 'E':
                        grade = 0.0;
                        break;
                }
                total = grade * f + total;

                fi = total / sumf;
            }
        }
        System.out.println("Final GPA is;"+fi);

    }
}

