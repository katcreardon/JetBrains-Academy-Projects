import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int num = input.nextInt();

        while (num != 0) {
            if (num % 2 == 0) {
                System.out.println("even");
            } else {
                System.out.println("odd");
            }
            num = input.nextInt();
        }
    }
}
