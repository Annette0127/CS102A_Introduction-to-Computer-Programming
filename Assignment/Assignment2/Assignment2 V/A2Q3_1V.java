public class A2Q3_1V {
    public static void main(String[] args) {
        Double[] gpa = {4.00, 3.94, 3.85, 3.73, 3.55, 3.32, 3.09, 2.78, 2.42, 2.08, 1.63, 1.15, 0.00};
        String[] grade = {"A+", "A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "D-", "F"};
        int e;
        for (e = 0; e < 13; e++) {
            if (grade[e].equals(args[0])) {
                break;
            }
        }
        double s = gpa[e];
        //这个变量没有设立的必要，直接输出gpa[e]就行了。
        System.out.printf("%.2f", s);
    }
}