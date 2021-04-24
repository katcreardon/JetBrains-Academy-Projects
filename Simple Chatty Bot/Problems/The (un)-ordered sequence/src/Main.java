import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int curr = input.nextInt();
        int next = input.nextInt();
        Boolean asc = false;
        Boolean desc = false;
        Boolean isOrdered = true;

        while (next != 0) {
            if (curr <= next) { // ascending
                if (desc) {
                    isOrdered = false;
                    break;
                }
                asc = true;
            } else { // descending
                if (asc) {
                    isOrdered = false;
                    break;
                }
                desc = true;
            }
            curr = next;
            next = input.nextInt();
        }
        System.out.println(isOrdered);
    }
}