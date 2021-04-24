import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner in = new Scanner(System.in);
        int u1 = in.nextInt();
        int u2 = in.nextInt();
        int v1 = in.nextInt();
        int v2 = in.nextInt();

        double ul = Math.sqrt(Math.pow(u1, 2) + Math.pow(u2, 2));
        double vl = Math.sqrt(Math.pow(v1, 2) + Math.pow(v2, 2));

        double udotv = u1 * v1 + u2 * v2;

        double costheta = udotv / (ul * vl);

        double angle = Math.acos(costheta);

        System.out.println(Math.toDegrees(angle));
    }
}