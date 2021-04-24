import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner in = new Scanner(System.in);
        String s = in.nextLine().toUpperCase();

        if (s.charAt(0) == 'J') {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }
}
