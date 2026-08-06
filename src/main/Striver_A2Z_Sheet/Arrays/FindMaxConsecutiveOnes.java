package Striver_A2Z_Sheet.Arrays;

import java.util.Arrays;
import java.util.Random;

public class FindMaxConsecutiveOnes {

    public static int count(int[] a) {
        int cnt = 0, max = 0;
        for(int i = 0; i < a.length; i++) {
            cnt = a[i] == 1 ? cnt + 1 : 0;
            max = Math.max(cnt, max);
        }
        return max;
    }

    public static void main(String[] args) {
        Random r = new Random();
        int[] a = new int[20];
        for(int i = 0; i < a.length; i++) {
            a[i] = r.nextInt(0, 2);
        }
        System.out.println("The array is: " + Arrays.toString(a));
        System.out.println("The maximum number of Consecutive ones are: " + count(a));
    }
}
