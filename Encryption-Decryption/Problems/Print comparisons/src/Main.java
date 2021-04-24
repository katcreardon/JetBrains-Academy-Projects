import java.util.*;

public class Main {
    public static void main(String[] args) {
        // write your code here
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int sqrtn = (int) Math.sqrt(n);
        int right = 1;
        int left = 0;

        int full = (n - 1) - ((n - 1) % sqrtn);

        for (int i = 0; i <= full; i++) {
            if (i % sqrtn == 0) {
                System.out.print(right++ + " ");
                left = right + sqrtn - 1;
            } else {
                System.out.print(left-- + " ");
            }
        }

        int rem = n - 1 - full;

        for (int j = rem; j > 0; j--) {
            if (j != 1) {
                System.out.print(right + j - 1 + " ");
            } else {
                System.out.print(right);
            }
        }
    }
}