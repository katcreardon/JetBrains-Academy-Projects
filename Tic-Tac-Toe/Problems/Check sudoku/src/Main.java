import java.util.*;

public class Main {
    public static void main(String[] args) {
        // write your code here
        int[][] grid = getGrid();
        int n2 = grid[0].length;
        int n = (int) Math.pow(n2, 0.5);

        if (checkRows(n2, grid) && checkCols(n2, grid) && checkBoxes(n, n2, grid)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    static int[][] getGrid() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int n2 = (int) Math.pow(n, 2);
        int[][] grid = new int[n2][n2];

        // Creates the grid and checks that the numbers are valid
        for (int i = 0; i < n2; i++) {
            for (int j = 0; j < n2; j++) {
                grid[i][j] = in.nextInt();

            }
        }
        return grid;
    }

    static boolean checkRows(int n2, int[][] grid) {
        // Searches each row for duplicate numbers
        for (int i = 0; i < n2; i++) {
            for (int j = 0; j < n2; j++) {
                if (grid[i][j] > n2 || grid[i][j] < 1) {
                    return false;
                }
                for (int k = j + 1; k < n2; k++) {
                    if (grid[i][j] == grid[i][k]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    static boolean checkCols(int n2, int[][] grid) {
        // Searches each column for duplicate numbers
        for (int j = 0; j < n2; j++) {
            for (int i = 0; i < n2; i++) {
                for (int k = i + 1; k < n2; k++) {
                    if (grid[i][j] == grid[k][j]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    static boolean checkBoxes(int n, int n2, int[][] grid) {
        // Checks smaller squares for unique numbers
        for (int i = 0; i < n2; i += n) {
            for (int j = 0; j < n2; j += n) {
                Set<Integer> set = new HashSet<>();
                for (int k = 0; k < n; k++) {
                    for (int l = 0; l < n; l++) {
                        if (set.contains(grid[i + k][j + l])) {
                            return false;
                        }
                        set.add(grid[i + k][j + l]);
                    }
                }
            }
        }
        return true;
    }
}