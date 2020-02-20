import java.util.Scanner;

public class E3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.printf("Please enter the numbers of subjects:");
        int sub = sc.nextInt();
        System.out.printf("Please enter the numbers of students:");
        int stu = sc.nextInt();
        int[][] score = new int[stu][sub];
        double[] avc = new double[sub];
        double[] avs = new double[stu];
        for (int i = 0; i < stu; i++) {
            for (int j = 0; j < sub; j++) {
                score[i][j] = sc.nextInt();
            }
        }

        for (int i = 0; i < stu; i++) {
            int sums = 0;
            for (int j = 0; j < sub; j++) {
                sums += score[i][j];
            }
            avs[i] = (double) sums / sub;
        }

        for (int i = 0; i < sub; i++) {
            int sumc = 0;
            for (int j = 0; j < stu; j++) {
                sumc += score[j][i];
            }
            avc[i] = (double) sumc / stu;
        }
        System.out.println("        Course1  Course2  Course3  Average");
        for (int r = 0; r < stu; r++) {
            System.out.printf("Student%d", r + 1);
            for (int c = 0; c < sub; c++) {
                System.out.printf("   %2d    ", score[r][c]);

            }
            System.out.printf("   %.2f",avs[r]);
            System.out.println();
        }
        System.out.printf("Average   %.2f    %.2f    %.2f",avc[0],avc[1],avc[2]);
    }
}
