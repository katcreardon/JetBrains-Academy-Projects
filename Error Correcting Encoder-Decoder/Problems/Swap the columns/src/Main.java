import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();

        int[][] arr = new int[n][m];

        for (int p = 0; p < n; p++) {
            for (int q = 0; q < m; q++) {
                arr[p][q] = in.nextInt();
            }
        }

        int i = in.nextInt();
        int j = in.nextInt();

        for (int p = 0; p < n; p++) {
            int temp = arr[p][i];
            arr[p][i] = arr[p][j];
            arr[p][j] = temp;
        }

        for (int p = 0; p < n; p++) {
            for (int q = 0; q < m; q++) {
                System.out.print(arr[p][q] + " ");
            }
            System.out.println();
        }
    }
}