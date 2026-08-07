package Striver_A2Z_Sheet.Arrays;

import java.util.Arrays;
import java.util.Random;

public class LongestSubArrayWithSumK {

    public static int longest(int[] a, int k) {
        int i = 0, j = 0;
        int sum = a[0];
        int maxLen = 0;
        while(j < a.length) {
            while(i <= j && sum > k) {
                sum -= a[i];
                i++;
            }
            if(sum == k) {
                Math.max(maxLen, j - i + 1);
            }
            j++;
            if(j < a.length) sum += a[j];
        }
        return maxLen;
    }

    public static void main(String[] args) {
        Random r = new Random();
        int[] a = new int[10];

        for(int i = 0; i < a.length; i++) {
            a[i] = r.nextInt(1, 4);
        }

        System.out.println("The array is: " + Arrays.toString(a));
        System.out.println("The Longest Sub-Array with sum 3 is: " + longest(a, 3));
    }
}
