package Striver_A2Z_Sheet.Arrays;

import java.util.Arrays;
import java.util.Random;

public class MoveZeroesToEnd {

    public static void moveZeroes(int[] nums) {
        int j = -1;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == 0) {
                j = i;
                break;
            }
        }

        if(j == -1) return;

        for(int i = j + 1; i < nums.length; i++) {
            if(nums[i] != 0) {
                swap(nums, i, j);
                j++;
            }
        }
    }

    public static int[] swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
        return nums;
    }

    public static void main(String[] args) {
        Random r = new Random();
        int[] nums = new int[20];
        for(int i = 0; i < nums.length; i++) {
            nums[i] = r.nextInt(0, 50);
        }
        System.out.println("Array before moving zeroes to the end: ");
        System.out.println(Arrays.toString(nums));
        moveZeroes(nums);
        System.out.println("Array after moving zeroes to the end: ");
        System.out.println(Arrays.toString(nums));
    }
}
