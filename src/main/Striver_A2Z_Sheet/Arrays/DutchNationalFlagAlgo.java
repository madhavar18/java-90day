package Striver_A2Z_Sheet.Arrays;

import java.util.Arrays;
import java.util.Random;

public class DutchNationalFlagAlgo {

    public static void sort(int[] a) {

        int low = 0, mid = 0, high = a.length - 1;

        while(mid <= high) {
            if(a[mid] == 0) {
                swap(low, mid, a);
                low++;
                mid++;
            }
            else if(a[mid] == 1) mid++;
            else {
                swap(high, mid, a);
                high--;
            }
        }
    }

    public static void swap(int i, int j, int[] a) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {
        Random r = new Random();
        int[] a = new int[20];

        for(int i = 0; i < a.length; i++) {
            a[i] = r.nextInt(0, 3);
        }

        System.out.println("The original array is: " + Arrays.toString(a));
        sort(a);
        System.out.println("The sorted array is: " + Arrays.toString(a));
    }
}
