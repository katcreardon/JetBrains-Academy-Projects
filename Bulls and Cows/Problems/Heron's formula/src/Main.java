import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner in = new Scanner(System.in);
        int a = in.nextInt();
        int b = in.nextInt();
        int c = in.nextInt();

        double p = (a + b + c) / 2.0;

        System.out.println(Math.sqrt(p * (p - a) * (p - b) * (p - c)));
    }
}