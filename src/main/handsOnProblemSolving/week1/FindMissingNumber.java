package handsOnProblemSolving.week1;

import java.util.Arrays;

public class FindMissingNumber {

    public static int missingNumber(int[] nums) {

        int n = nums.length, sum = 0;

        for(int i = 0; i < n; i++) {
            sum += nums[i];
        }

        int sumn = (n * (n + 1)) / 2;

        return sumn - sum;
    }

    public static void main(String[] args) {

        int[] nums = new int[] {0, 1, 2, 4, 5};

        System.out.println("The array elements are: " + Arrays.toString(nums));
        System.out.println("The missing number is: " + missingNumber(nums));

    }
}
