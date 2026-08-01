package Striver_A2Z_Sheet.Arrays;

import java.util.Random;

public class SecondLargestAndSecondSmalles {
    public static int[] secondLargestAndSmallest(int[] a) {
        int max = a[0], smax = Integer.MIN_VALUE;
        int min = a[0], smin = Integer.MAX_VALUE;

        for (int i = 1; i < a.length; i++) {

            // Largest
            if (a[i] > max) {
                smax = max;
                max = a[i];
            } else if (a[i] > smax && a[i] != max) {
                smax = a[i];
            }

            // Smallest
            if (a[i] < min) {
                smin = min;
                min = a[i];
            } else if (a[i] < smin && a[i] != min) {
                smin = a[i];
            }
        }

        return new int[]{smin, smax};
    }

    public static void main(String[] args) {
        int[] a = new int[10];
        Random r = new Random();

        for (int i = 0; i < a.length; i++) {
            a[i] = r.nextInt(-1000, 1000);
        }

        System.out.println("--- The array elements are: ---");
        for (int j : a) {
            System.out.println(j);
        }

        int[] o = secondLargestAndSmallest(a);
        System.out.println("Second smallest element: "+o[0]);
        System.out.println("Second largest element: "+o[1]);


    }
}
