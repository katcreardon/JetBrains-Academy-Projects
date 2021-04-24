import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner in = new Scanner(System.in);
        int s = in.nextInt();
        int[] arr = new int[s];

        for (int i = 0; i < s; i++) {
            arr[i] = in.nextInt();
        }

        int n = in.nextInt();
        in.close();
        int sum = 0;

        for (int i : arr) {
            if (i > n) {
                sum += i;
            }
        }

        System.out.println(sum);
    }
}