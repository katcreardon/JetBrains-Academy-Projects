import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // put your code here
        double d = scanner.nextDouble();
        double t = scanner.nextDouble();
        double r = d / t;
        System.out.println(r);
    }
}