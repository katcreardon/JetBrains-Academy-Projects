import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[][] arr = new int[n][n];
        int top = 0;
        int bottom = n - 1;
        int left = 0;
        int right = n - 1;
        int count = 1;
        int dir = 1;

        while (top <= bottom && left <= right) {
            if (dir == 1) {
                for (int i = left; i <= right; i++) {
                    arr[top][i] = count;
                    count++;
                }
                top++;
                dir = 2;
            }
            if (dir == 2) {
                for (int i = top; i <= bottom; i++) {
                    arr[i][right] = count;
                    count++;
                }
                right--;
                dir = 3;
            }
            if (dir == 3) {
                for (int i = right; i >= left; i--) {
                    arr[bottom][i] = count;
                    count++;
                }
                bottom--;
                dir = 4;
            }
            if (dir == 4) {
                for (int i = bottom; i >= top; i--) {
                    arr[i][left] = count;
                    count++;
                }
                left++;
                dir = 1;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}