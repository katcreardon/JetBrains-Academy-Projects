package processor;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        Matrix matrixA = new Matrix();
//        Matrix matrixB = new Matrix();
//
//        Matrix matrixSum = Matrix.addMatrices(matrixA, matrixB);
//
//        matrixSum.print();
//
//        Matrix matrixC = new Matrix();
//
//        Scanner in = new Scanner(System.in);
//        int c = in.nextInt();
//
//        matrixC.scaleMatrix(c);
//        matrixC.print();

        userMenu();
    }

    public static void userMenu() {
        Scanner in = new Scanner(System.in);
        int choice = -1;
        int n = -1;
        int m = -1;
        Matrix matrixA;
        Matrix matrixB;
        Matrix matrixC;

        while (choice != 0) {
            System.out.println("1. Add matrices");
            System.out.println("2. Multiply matrix by a constant");
            System.out.println("3. Multiply matrices");
            System.out.println("4. Transpose matrix");
            System.out.println("5. Calculate a determinant");
            System.out.println("6. Inverse matrix");
            System.out.println("0. Exit");
            System.out.print("Your choice: ");
            choice = in.nextInt();

            switch (choice) {
                case 0:
                    System.exit(0);
                case 1:
                    System.out.print("Enter size of first matrix: ");
                    n = in.nextInt();
                    m = in.nextInt();

                    System.out.println("Enter first matrix:");
                    matrixA = new Matrix(n, m, getDoubleMatrix(n, m, in));

                    System.out.print("Enter size of second matrix: ");
                    n = in.nextInt();
                    m = in.nextInt();

                    System.out.println("Enter second matrix: ");
                    matrixB = new Matrix(n, m, getDoubleMatrix(n, m, in));

                    matrixC = Matrix.addMatrices(matrixA, matrixB);

                    if (matrixC == null) {
                        System.out.println("The operation cannot be performed.");
                    } else {
                        System.out.println("The result is:");
                        matrixC.print();
                    }
                    System.out.println();
                    break;
                case 2:
                    System.out.print("Enter size of matrix: ");
                    n = in.nextInt();
                    m = in.nextInt();

                    System.out.println("Enter matrix:");
                    matrixC = new Matrix(n, m, getDoubleMatrix(n, m, in));

                    System.out.print("Enter constant: ");
                    double c = in.nextDouble();

                    System.out.println("The result is:");
                    matrixC.scaleMatrix(c).print();
                    System.out.println();
                    break;
                case 3:
                    System.out.print("Enter size of first matrix: ");
                    n = in.nextInt();
                    m = in.nextInt();

                    System.out.println("Enter first matrix:");
                    matrixA = new Matrix(n, m, getDoubleMatrix(n, m, in));

                    System.out.print("Enter size of second matrix: ");
                    n = in.nextInt();
                    m = in.nextInt();

                    System.out.println("Enter second matrix: ");
                    matrixB = new Matrix(n, m, getDoubleMatrix(n, m, in));

                    matrixC = Matrix.multiplyMatrices(matrixA, matrixB);

                    if (matrixC == null) {
                        System.out.println("The operation cannot be performed.");
                    } else {
                        System.out.println("The result is:");
                        matrixC.print();
                    }
                    System.out.println();
                    break;
                case 4:
                    System.out.println();
                    transposeUserMenu(in);
                    break;
                case 5:
                    System.out.print("Enter matrix size: ");
                    n = in.nextInt();
                    m = in.nextInt();

                    System.out.println("Enter matrix:");
                    matrixA = new Matrix(n, m, getDoubleMatrix(n, m, in));

                    System.out.println("The result is:");
                    System.out.println(matrixA.getDeterminant(n) + "\n");
                    break;
                case 6:
                    System.out.print("Enter matrix size: ");
                    n = in.nextInt();
                    m = in.nextInt();

                    System.out.println("Enter matrix:");
                    matrixA = new Matrix(n, m, getDoubleMatrix(n, m, in));

                    if (matrixA.getDeterminant(n).equals("0")) {
                        System.out.println("This matrix doesn't have an inverse.");
                    } else {
                        matrixB = new Matrix(n, m, Inverse.invert(matrixA.getMatrix()));
                        System.out.println("The result is:");
                        matrixB.print();
                    }
                    System.out.println();
                    break;
                default:
                    System.out.println("Error: incorrect choice\n");
                    userMenu();
            }
        }
    }

    public static double[][] getDoubleMatrix(int n, int m, Scanner in) {
        double[][] matrix = new double[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = in.nextDouble();
            }
        }

        return matrix;
    }

    public static void transposeUserMenu(Scanner in) {
        System.out.println("1. Main diagonal");
        System.out.println("2. Side diagonal");
        System.out.println("3. Vertical line");
        System.out.println("4. Horizontal line");
        System.out.print("Your choice: ");

        int tchoice = in.nextInt();

        if (tchoice < 1 || tchoice > 4) {
            System.out.println("Error: incorrect choice");
            System.out.println();
            transposeUserMenu(in);
        }

        System.out.print("Enter matrix size: ");
        int n = in.nextInt();
        int m = in.nextInt();

        System.out.println("Enter matrix:");
        Matrix matrix = new Matrix(n, m, getDoubleMatrix(n, m, in));

        Matrix matrixT = matrix.transpose(tchoice);

        System.out.println("The result is:");
        matrixT.print();
        System.out.println();
    }
}
