package Striver_A2Z_Sheet.Arrays;

import java.util.Arrays;

public class KadaneAlgo{

    public static int[] maxSubarraySum(int[] a) {
        int sum = 0, max = Integer.MIN_VALUE, i, start = 0,ansStart = 0, ansEnd = 0;

        for(i = 0; i < a.length; i++) {
            if(sum == 0) start = i;
            sum += a[i];
            if(sum > max) {
                max = sum;
                ansStart = start;
                ansEnd = i;
            }
            if(sum <= 0) sum = 0;
        }

        return new int[] {max, ansStart, ansEnd};
    }

    public static void main(String[] args) {
        int[] a = new int[] {-2,-3,4,-1,-2,1,5,-3};
        System.out.println("The array is: " + Arrays.toString(a));
        System.out.println("The maximum subarray sum is: " + Arrays.toString(maxSubarraySum(a)));
    }
}
