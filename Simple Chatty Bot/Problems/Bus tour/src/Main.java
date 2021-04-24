import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int h = input.nextInt();
        int n = input.nextInt();
        int i;

        for (i = 1; i <= n; i++) {
            int bh = input.nextInt();
            if (bh <= h) {
                System.out.printf("Will crash on bridge %d\n", i);
                break;
            }
        }

        if (i > n) {
            System.out.println("Will not crash");
        }
    }
}