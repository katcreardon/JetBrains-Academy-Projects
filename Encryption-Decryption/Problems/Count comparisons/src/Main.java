import java.util.*;

public class Main {
    public static void main(String[] args) {
        // write your code here
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = in.nextInt();
        }
        int target = in.nextInt();
        System.out.println(jumpSearch(array, target));
    }

    public static int jumpSearch(int[] array, int target) {
        int currentRight = 0; // right border of the current block
        int prevRight = 0; // right border of the previous block
        int comparisons = 0;

        /* If array is empty, the element is not found */
        if (array.length == 0) {
            return -1;
        }

        /* Check the first element */
        if (array[currentRight] == target) {
            return 1;
        }

        comparisons += 1;

        /* Calculating the jump length over array elements */
        int jumpLength = (int) Math.sqrt(array.length);

        /* Finding a block where the element may be present */
        while (currentRight < array.length - 1) {

            /* Calculating the right border of the following block */
            currentRight = Math.min(array.length - 1, currentRight + jumpLength);

            if (array[currentRight] >= target) {
                break; // Found a block that may contain the target element
            }

            comparisons += 1;
            prevRight = currentRight; // update the previous right block border
        }

        /* If the last block is reached and it cannot contain the target value => not found */
        if (currentRight == array.length - 1 && target > array[currentRight]) {
            return comparisons;
        }

        /* Doing linear search in the found block */
        return comparisons + backwardSearch(array, target, prevRight, currentRight);
    }

    public static int backwardSearch(int[] array, int target, int leftExcl, int rightIncl) {
        int comps = 0;
        for (int i = rightIncl; i > leftExcl; i--) {
            if (array[i] == target || array[i] < target) {
                return comps += 1;
            }
            comps += 1;
        }
        return comps;
    }
}