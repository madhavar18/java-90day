package Striver_A2Z_Sheet.Arrays;

import java.util.Arrays;
import java.util.Random;

public class NextPermutation {

    public static int[] find(int[] a) {
        int index = -1, n = a.length;

        for(int i = n - 2; i > 0; i--) {
            if(a[i] < a[i + 1]) {
                index = i;
                break;
            }
        }

        if(index == -1) {
            reverse(a, 0, n - 1);
            return a;
        }

        for(int i = n - 1; i > 0; i--) {
            if(a[i] > a[index]) {
                swap(a, index, i);
                break;
            }
        }

        reverse(a, index + 1, n - 1);
        return a;
    }

    public static void reverse(int[] a, int start, int end) {
        int j = end;
        for(int i = start; i < j; i++) {
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
            j--;
        }
    }

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] a = new int[5];
        Random r = new Random();

        for(int i = 0; i < a.length; i++) {
            a[i] = r.nextInt(1, 10);
        }

        System.out.println("The current array is: " + Arrays.toString(a));
        System.out.println("The next permutation is: " + Arrays.toString(find(a)));
    }
}
