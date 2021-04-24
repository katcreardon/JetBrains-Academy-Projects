import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner in = new Scanner(System.in);
        String intString = in.nextLine();
        int numRotations = in.nextInt();

        String[] result = intString.split("\\s");
        int[] arr = new int[result.length];
        numRotations = numRotations % arr.length;
        int j = numRotations;

        for (int i = 0; i < arr.length; i++) {
            arr[j] = Integer.parseInt(result[i]);
            if (j + 1 > arr.length - 1) {
                j = 0;
            } else {
                j += 1;
            }
        }

        for (int i : arr) {
            System.out.print(i + " ");
        }
    }
}