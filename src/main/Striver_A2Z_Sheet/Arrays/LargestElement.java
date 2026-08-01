package Striver_A2Z_Sheet.Arrays;

import java.util.Random;

public class LargestElement {

    public static int largestElement(int[] a) {
        int max = a[0];
        for(int i = 1; i < a.length; i++) {
            if(max < a[i]) max = a[i];
        }
        return max;
    }

    public static void main(String[] args) {
        int[] a = new int[10];
        Random r = new Random();

        for(int i = 0; i < a.length; i++) {
            a[i] = r.nextInt(10000);
        }
        System.out.println("--- The array elements are: ---");
        for(int i = 0; i < a.length; i++) {
            System.out.println(a[i]);

        }
        System.out.println("--- The largest element is: ---");
        System.out.println(largestElement(a));
    }
}
