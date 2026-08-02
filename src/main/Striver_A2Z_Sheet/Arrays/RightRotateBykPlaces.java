package Striver_A2Z_Sheet.Arrays;

import java.util.Arrays;
import java.util.Random;

public class RightRotateBykPlaces {

    public static void rotate(int[] nums, int k) {
        k = k % nums.length;
        reverse(nums.length - k, nums.length - 1, nums);
        reverse(0, nums.length - k - 1, nums);
        reverse(0, nums.length - 1, nums);
    }

    public static void reverse(int st, int end, int[] nums) {
        while(st <= end) {
            int temp = nums[st];
            nums[st] = nums[end];
            nums[end] = temp;
            st++;
            end--;
        }
    }

    public static void main(String[] args) {
        Random r = new Random();
        int[] nums = new int[20];
        int k = r.nextInt(21,100);
        System.out.println("Actual value of k: "+ k);
        System.out.println("Since the elements return to their original places when rotated by size of the array(n), the no.of rotations can decreased using k = k % n");
        System.out.println("Therefore the decreased value of k is:" + k % nums.length);
        for(int i = 0; i < nums.length; i++) {
            nums[i] = r.nextInt(-1000, 1000);
        }
        System.out.println("original array: ");
        System.out.println(Arrays.toString(nums));
        rotate(nums, k);
        System.out.println("rotated array: ");
        System.out.println(Arrays.toString(nums));
    }
}
