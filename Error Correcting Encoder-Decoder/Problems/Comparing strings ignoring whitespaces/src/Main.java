import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner in = new Scanner(System.in);
        String first = in.nextLine();
        String second = in.nextLine();

        System.out.println(first.replaceAll("\\s+", "")
                .equals(second.replaceAll("\\s+", "")));
    }
}