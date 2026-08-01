package Striver_A2Z_Sheet.Arrays;

import java.util.Random;

public class ArraySortCheck {

    public static boolean sortedCheck(int[] a) {
        for(int i = 1; i < a.length; i++) {
            if(a[i] >= a[i - 1]) continue;
            else return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int[] a = new int[10];
        // unsorted check
        Random r = new Random();

        for(int i = 0; i < a.length; i++) {
            a[i] = r.nextInt(-10000, 10000);
        }
        System.out.println(sortedCheck(a) ? "sorted" : "not sorted");

        // sorted check
        int[] b = new int[] {1, 2, 3, 4, 5, 6, 7 ,8 , 9, 10};
        System.out.println(sortedCheck(b) ? "sorted" : "not sorted");
    }
}
