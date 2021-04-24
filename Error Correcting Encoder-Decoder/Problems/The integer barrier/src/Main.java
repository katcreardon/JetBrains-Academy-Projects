import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner in = new Scanner(System.in);
        int sum = 0;
        while (in.hasNextInt()) {
            int next = in.nextInt();
            if (sum >= 1000) {
                System.out.println(sum - 1000);
                System.exit(0);
            } else {
                if (next == 0) {
                    System.out.println(sum);
                    System.exit(0);
                } else {
                    sum += next;
                }
            }
        }
    }
}