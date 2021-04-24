import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        printSequence(n);
    }

    public static void printSequence(int n) {
        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (count == n) {
                break;
            }
            for (int j = 1; j <= i; j++) {
                System.out.print(i + " ");
                count += 1;
                if (count == n) {
                    break;
                }
            }
        }
    }
}