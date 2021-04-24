import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner in = new Scanner(System.in);
        double a = in.nextDouble();
        double b = in.nextDouble();
        double c = in.nextDouble();
        double d = Math.sqrt(Math.pow(b, 2) - (4 * a * c));

        double x1 = (-b + d) / (2 * a);
        double x2 = (-b - d) / (2 * a);

        if (x1 < x2) {
            System.out.println(x1 + " " + x2);
        } else {
            System.out.println(x2 + " " + x1);
        }
    }
}