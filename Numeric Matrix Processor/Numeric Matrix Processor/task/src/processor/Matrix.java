package processor;

import java.util.Scanner;

public class Matrix {

    private int rows;
    private int cols;
    private double[][] matrix;

    public Matrix() {
        Scanner in = new Scanner(System.in);
        rows = in.nextInt();
        cols = in.nextInt();

        matrix = new double[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = in.nextInt();
            }
        }
    }

    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        matrix = new double[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = 0.0;
            }
        }
    }

    public Matrix(int rows, int cols, double[][] matrix) {
        this.rows = rows;
        this.cols = cols;
        this.matrix = matrix;
    }

    public int getRows() {
        return this.rows;
    }

    public int getCols() {
        return this.cols;
    }

    public double[][] getMatrix() {
        return this.matrix;
    }

    public void setMatrix() {
        Scanner in = new Scanner(System.in);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = in.nextInt();
            }
        }
    }

    public static Matrix addMatrices(Matrix a, Matrix b) {
        int n = a.rows;
        int m = a.cols;

        // Need to change how error is handled
        if (n != b.rows || m != b.cols) {
            return null;
        }

        double[][] matrix = new double[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = a.matrix[i][j] + b.matrix[i][j];
            }
        }

        return new Matrix(n, m, matrix);
    }

    public static Matrix multiplyMatrices(Matrix a, Matrix b) {
        int n = a.rows;
        int m = a.cols;
        int k = b.cols;

        // Need to change how error is handled
        if (m != b.rows) {
            return null;
        }

        double[][] matrix = new double[n][k];

        for (int i = 0; i < n; i++) { // rows of new matrix
            for (int j = 0; j < k; j++) { // cols of new matrix
                double sum = 0;
                for (int p = 0; p < m; p++) {
                    sum += a.matrix[i][p] * b.matrix[p][j];
                }
                matrix[i][j] = sum;
            }
        }

        return new Matrix(n, k, matrix);
    }

    public Matrix scaleMatrix(double c) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] *= c;
            }
        }

        return this;
    }

    public Matrix transpose(int tchoice) {
        double[][] matrix = new double[this.rows][this.cols];

        switch (tchoice) {
            case 1:
                return transposeMainDiag(this, matrix);
            case 2:
                return transposeSideDiag(this, matrix);
            case 3:
                return transposeVertical(this, matrix);
            case 4:
                return transposeHorizontal(this, matrix);
            default:
                throw new IllegalStateException("Unexpected value: " + tchoice);
        }
    }

    public Matrix transposeMainDiag(Matrix a, double[][] matrix) {
        for (int i = 0; i < a.rows; i++) {
            matrix[i][i] = a.matrix[i][i];
        }

        for (int i = 0; i < a.rows - 1; i++) {
            for (int j = i + 1; j < a.cols; j++) {
                matrix[i][j] = a.matrix[j][i];
                matrix[j][i] = a.matrix[i][j];
            }
        }

        return new Matrix(a.rows, a.cols, matrix);
    }

    public Matrix transposeSideDiag(Matrix a, double[][] matrix) {
        for (int i = 0, j = a.cols - 1; i < a.rows && j >= 0; i++, j--) {
            matrix[i][j] = a.matrix[i][j];
        }

        for (int i = 0, j = a.cols - 1; i < a.rows && j >= 0; i++, j--) {
            for (int h = i + 1, k = j - 1; h < a.rows && k >= 0; h++, k--) {
                matrix[i][k] = a.matrix[h][j];
                matrix[h][j] = a.matrix[i][k];
            }
        }

        return new Matrix(a.rows, a.cols, matrix);
    }

    public Matrix transposeVertical(Matrix a, double[][] matrix) {
        for (int i = 0; i < a.rows; i++) {
            for (int j = 0, k = a.cols - 1; j < a.rows; j++, k--) {
                matrix[i][k] = a.matrix[i][j];
            }
        }

        return new Matrix(a.rows, a.cols, matrix);
    }

    public Matrix transposeHorizontal(Matrix a, double[][] matrix) {
        for (int i = 0, h = a.rows - 1; i < a.rows; i++, h--) {
            for (int j = 0; j < a.cols; j++) {
                matrix[h][j] = a.matrix[i][j];
            }
        }

        return new Matrix(a.rows, a.cols, matrix);
    }

    // Taken from GeeksforGeeks
    public String getDeterminant(int n) {
        return parse(this.determinant(n));
    }

    private double determinant(int n) {
        // Base case
        if (n == 1) {
            return matrix[0][0];
        }

        double det = 0;
        int sign = 1;
        Matrix temp = new Matrix(4, 4);

        for (int i = 0; i < rows; i++) {
            getCofactor(this, temp, 0, i, n);
            det += sign * matrix[0][i] * temp.determinant(n - 1);
            sign = -sign;
        }

        return det;
    }

    private void getCofactor(Matrix matrix, Matrix temp, int p, int q, int n) {
        int i = 0;
        int j = 0;

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (row != p && col != q) {
                    temp.matrix[i][j++] = matrix.matrix[row][col];
                    if (j == n - 1) {
                        j = 0;
                        i++;
                    }
                }
            }
        }
    }

    public void print() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(parse(matrix[i][j]) + " ");
            }
            System.out.println();
        }
    }

    private static String parse(double num) {
        if ((int) num == num) {
            return Integer.toString((int) num);
        } else {
            return Double.toString(num);
        }
    }
}
