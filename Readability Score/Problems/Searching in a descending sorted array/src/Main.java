import java.util.Scanner;

public class Main {

    /* Modify this method */
    public static int binarySearch(int elem, int[] array) {

        int left = 0;
        int right = array.length - 1;
        int index = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (elem == array[mid]) {
                index = mid;
                right = mid - 1;
            } else if (elem < array[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return index;
    }

    /* Do not change code below */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < array.length; i++) {
            array[i] = scanner.nextInt();
        }

        int m = scanner.nextInt();
        for (int i = 0; i < m; i++) {
            int elem = scanner.nextInt();
            System.out.print(binarySearch(elem, array));
            System.out.print(i < m - 1 ? " " : "\n");
        }
    }
}