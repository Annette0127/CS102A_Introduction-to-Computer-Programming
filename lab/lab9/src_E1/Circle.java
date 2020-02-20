public class Circle {
    public double distanceToOrigin(double x, double y) {
        return Math.sqrt(x * x + y * y);
    }

    public static void main(String[] args) {
        int n = (int) (5 + Math.random() * 5);
        double[] circleR = new double[n];
        double[][] circleP = new double[2][n];
        double minR = 10;
        double maxD = 0;
        for (int i = 0; i < n; i++) {
            circleR[i] = (1 + Math.random() * 2);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2; j++) {
                circleP[j][i] = (2 + Math.random() * 3);
            }
        }
        for (int i = 0; i < n; i++) {
            minR = Math.min(minR, circleR[i]);
        }
        for (int i = 0; i < n; i++) {
            System.out.printf("Circle #%d: radius = %.2f, x = %.2f, y = %.2f\n", i, circleR[i], circleP[0][i], circleP[1][i]);
        }
        for (int i = 0; i < n; i++) {
            if (circleR[i] == minR) {
                System.out.printf("Circle #%d is the smallest circle, area = %.2f\n", i, 3.14 * circleR[i] * circleR[i]);
            }
        }
        for (int i = 0; i < n; i++) {
            maxD = Math.max(maxD, Math.sqrt(circleP[0][i] * circleP[0][i] + circleP[1][i] * circleP[1][i]));
        }
        for (int i = 0; i < n; i++) {
            if (Math.sqrt(circleP[0][i] * circleP[0][i] + circleP[1][i] * circleP[1][i]) == maxD) {
                System.out.printf("Circle #%d is the farthest circle, distance to origin = %.2f\n", i, Math.sqrt(circleP[0][i] * circleP[0][i] + circleP[1][i] * circleP[1][i]));
            }
        }
    }
}
