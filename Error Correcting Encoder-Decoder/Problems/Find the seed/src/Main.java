import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // write your code here
        String intStr = scanner.nextLine();
        String[] result = intStr.split("\\s");

        int a = Integer.valueOf(result[0]);
        int b = Integer.valueOf(result[1]);
        int n = Integer.valueOf(result[2]);
        int k = Integer.valueOf(result[3]);
        int seed = a;
        int min = Integer.MAX_VALUE;

        for (int i = a; i <= b; i++) {
            Random rand = new Random(i);
            int max = Integer.MIN_VALUE;
            for (int j = 0; j < n; j++) {
                int next = rand.nextInt(k);
                if (next > max) {
                    max = next;
                }
            }
            if (max < min) {
                min = max;
                seed = i;
            }
        }
        System.out.println(seed);
        System.out.println(min);
    }
}