public class A2Q2 {
    public static void main(String[] args) {
        int n = args.length;
        double num[] = new double[n];
        double median = 0, sum = 0;
        for (int j = 0; j < n; j++) {
            num[j] = Double.parseDouble(args[j]);
            sum += num[j];
        }
        int i;
        for (int m = n - 2; m >= 0; m--) {
            i = 0;
            while (i <= m) {
                if (num[i] > num[i + 1]) {
                    double a = num[i];
                    num[i] = num[i + 1];
                    num[i + 1] = a;
                }
                i++;
            }
        }
        System.out.printf("average = %.2f\n", sum / n);
        int count[] = new int[n];
        for (int k = 0; k < n; k++) {
            count[k] = 1;
        }
        for (int k = 0, j; k < n; ) {
            for (j = k + 1; j < n; j++) {
                if (num[k] != num[j]) {
                    break;
                }
                count[k]++;
            }
            k = j;
        }
        int maxNum = 1;
        for (int k = 0; k < n; k++) {
            if (count[k] > maxNum) {
                maxNum = count[k];
            }
        }
        System.out.printf("mode = ");
        for (int k = 0; k < n; k++) {
            if (count[k] == maxNum) {
                System.out.printf("%.2f ", num[k]);
            }
        }
        if(n%2==0){
            median = (num[n/2]+num[n/2-1])/2;
        }
        else{
            median = num[(n-1)/2];
        }
        System.out.printf("\nmedian = %.2f", median);
    }
}

