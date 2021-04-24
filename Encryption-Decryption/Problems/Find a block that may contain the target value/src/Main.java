import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = in.nextInt();
        }
        int target = in.nextInt();
        System.out.println(jumpSearch(array, target));
    }

    public static String jumpSearch(int[] array, int target) {
        int currentRight = -1; // right border of the current block
        int prevRight = 0; // right border of the previous block

        /* If array is empty, the element is not found */
        if (array.length == 0) {
            return "-1";
        }

        /* Calculating the jump length over array elements */
        int jumpLength = (int) Math.sqrt(array.length);

        /* Finding a block where the element may be present */
        while (currentRight < array.length - 1) {

            /* Calculating the right border of the following block */
            currentRight = Math.min(array.length - 1, currentRight + jumpLength);

            if (array[currentRight] >= target) {
                break; // Found a block that may contain the target element
            }

            prevRight = currentRight + 1; // update the previous right block border
        }

        /* If the last block is reached and it cannot contain the target value => not found */
        if (currentRight == array.length - 1 && target > array[currentRight]) {
            return "-1";
        }

        /* Returning left and right indices of found block */
        if (currentRight - prevRight < jumpLength - 1) {
            return currentRight + " " + currentRight;
        } else {
            return prevRight + " " + currentRight;
        }
    }
}