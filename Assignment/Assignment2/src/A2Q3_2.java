public class A2Q3_2 {
    public static void main(String[] args) {
        int n = args.length;
        if (0 == n || 0 != n % 2) {
            System.out.print("Please input the right format of score and credit hour in pair, eg. 95 2 88 3");
            return;
        }
        double[] gpa = {4.00, 3.94, 3.85, 3.73, 3.55, 3.32, 3.09, 2.78, 2.42, 2.08, 1.63, 1.15, 0.00};
        double[] score = new double[n / 2];
        double[] credit = new double[n / 2];
        double sum = 0;
        double g;
        double cr = 0;

        for (int i = 0; i < n ; i += 2) {
            score[(i + 1) / 2] = Double.parseDouble(args[i]);
            credit[(i + 1) / 2] = Integer.parseInt(args[i + 1]);
            if (score[(i + 1) / 2] >= 90) {
                if (score[(i + 1) / 2] > 96) {
                    g = gpa[0];
                } else if (score[(i + 1) / 2] > 92) {
                    g = gpa[1];
                } else {
                    g = gpa[2];
                }
            } else if (score[(i + 1) / 2] >= 80) {
                if (score[(i + 1) / 2] > 86) {
                    g = gpa[3];
                } else if (score[(i + 1) / 2] > 82) {
                    g = gpa[4];
                } else {
                    g = gpa[5];
                }
            } else if (score[(i + 1) / 2] >= 70) {
                if (score[(i + 1) / 2] > 76) {
                    g = gpa[6];
                } else if (score[(i + 1) / 2] > 72) {
                    g = gpa[7];
                } else {
                    g = gpa[8];
                }
            } else if (score[(i + 1) / 2] >= 60) {
                if (score[(i + 1) / 2] > 66) {
                    g = gpa[9];
                } else if (score[(i + 1) / 2] > 62) {
                    g = gpa[10];
                } else {
                    g = gpa[11];
                }
            } else {
                g = gpa[12];
            }
            sum += g * credit[(i + 1) / 2];
            cr += credit[(i + 1) / 2];
        }
        System.out.printf("GPA = %.2f",sum/cr);
    }
}