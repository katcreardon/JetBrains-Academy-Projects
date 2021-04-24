import java.util.*;

public class Main {
    public static void main(String[] args) {
        // write your code here
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int[] incomes = new int[n];
        int[] taxes = new int[n];

        for (int i = 0; i < n; i++) {
            incomes[i] = in.nextInt();
        }

        for (int j = 0; j < n; j++) {
            taxes[j] = in.nextInt();
        }

        double[] pays = new double[n];

        for (int k = 0; k < n; k++) {
            pays[k] = incomes[k] * (taxes[k] / 100.0);
        }

        double high = 0;
        int highIndex = 0;

        for (int l = 0; l < n; l++) {
            if (pays[l] > high) {
                high = pays[l];
                highIndex = l + 1;
            }
        }

        System.out.println(highIndex);
    }
}
