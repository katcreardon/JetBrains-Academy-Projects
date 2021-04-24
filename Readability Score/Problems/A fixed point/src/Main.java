import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = in.nextInt();
        }

        System.out.println(hasFixedPoint(n, numbers));

    }

    public static boolean hasFixedPoint(int n, int[] numbers) {
        int left = 0;
        int right = n - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (numbers[mid] == mid) {
                return true;
            } else if (numbers[mid] > mid) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return false;
    }
}