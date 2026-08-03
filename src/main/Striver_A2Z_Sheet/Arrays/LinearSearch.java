package Striver_A2Z_Sheet.Arrays;

import java.util.Arrays;
import java.util.Random;

public class LinearSearch {

    public static int linearSearch(int[] nums, int target) {
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == target) return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        Random r = new Random();
        int[] nums = new int[20];
        for(int i = 0; i < nums.length; i++) {
            nums[i] = r.nextInt(1, 100);
        }
        int target = r.nextInt(1, 100);
        System.out.println("Array: " + Arrays.toString(nums));
        System.out.println("target element: " + target);
        System.out.println("Search result: " + linearSearch(nums, target));

    }
}
