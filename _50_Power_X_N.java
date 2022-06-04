package leetcode.medium;

public class _50_Power_X_N {

    class Solution {
        public double myPow(double x, int n) {
            if (n == 0 || x == 0.0) {
                return 1.0;
            } else if (n < 0) {
                int m = -1 * n;
                return 1 / power(x, m);
            } else {
                return power(x, n);
            }

        }

        private double power(double x, int m) {
            if (m % 2 == 0) {
                x = Math.abs(x);
            }
            if (m == 0) {
                return 1;
            }
            if (m == 1) {
                return x;
            }
            if (m == 2) {
                return x * x;
            }

            int half = Math.abs((int) (Math.log(m) / Math.log(2)));
            int remain = m - (int) Math.pow(2, half);
            //System.out.printf("log: %d, remain: %d\n", half, remain);
            double square = power(x, 2);
            double result = square;
            while (half-- > 1) {
                result *= result;
                //System.out.printf(">> half: %d, result: %s\n", half, result);
            }
            return result * power(x,remain);
        }
    }
}
