package algo101.binarySearch;

import java.util.*;

public class RandomizedBinarySearch {

    public static int randomBinarySearch(int[] a, int target) {

        int l = 0;
        int u = a.length - 1;
        int count = 0;

        Random rand = new Random();

        while (l <= u) {

            count++;

            int mid = l + rand.nextInt(u - l + 1);

            if (a[mid] == target)
                return count;

            if (a[mid] < target)
                l = mid + 1;
            else
                u = mid - 1;
        }

        return -1;
    }

    public static int binarySearch(int[] a, int target) {

        int l = 0;
        int u = a.length - 1;
        int count = 0;

        while (l <= u) {

            count++;

            int mid = l + (u - l) / 2;

            if (a[mid] == target)
                return count;

            if (a[mid] < target)
                l = mid + 1;
            else
                u = mid - 1;
        }

        return -1;
    }

    public static void main(String[] args) {

        Random r = new Random();

        int n = 1024;

        long randomTotal = 0;
        long binaryTotal = 0;

        int trials = 100000;

        for (int t = 0; t < trials; t++) {

            int[] arr = new int[n];

            for (int i = 0; i < n; i++)
                arr[i] = i;

            int target = r.nextInt(n);

            randomTotal += randomBinarySearch(arr, target);
            binaryTotal += binarySearch(arr, target);
        }

        System.out.println("Average Randomized = " +
                (double) randomTotal / trials);

        System.out.println("Average Standard   = " +
                (double) binaryTotal / trials);
    }

}
