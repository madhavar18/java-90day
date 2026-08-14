package Striver_A2Z_Sheet.Arrays;

import java.io.FilterOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class RearrangeElements {

    public static int[] rearrangeArray(int[] nums) {
        ArrayList<Integer> pos = new ArrayList<>();
        ArrayList<Integer> neg = new ArrayList<>();

        for(int num : nums) {
            if(num > 0) pos.add(num);
            else neg.add(num);
        }

        if(pos.size() > neg.size()) {
            for(int i = 0; i < neg.size(); i++) {
                nums[2 * i] = pos.get(i);
                nums[2 * i + 1] = neg.get(i);
            }
            int index = neg.size() * 2;
            for(int i = neg.size(); i < pos.size(); i++) {
                nums[index] = pos.get(i);
                index++;
            }
        }
        else {
            for(int i = 0; i < pos.size(); i++) {
                nums[2 * i] = pos.get(i);
                nums[2 * i + 1] = neg.get(i);
            }
            int index = pos.size() * 2;
            for(int i = pos.size(); i < neg.size(); i++) {
                nums[index] = neg.get(i);
                index++;
            }
        }
        return nums;
    }

    public static void main(String[] args) {

        int[] nums = new int[20];
        Random r = new Random();

        for(int i = 0; i < nums.length; i++) {
            nums[i] = r.nextInt(-20, 21);
        }

        System.out.println("The original array is: " + Arrays.toString(nums));
        System.out.println("The rearranged array is: " + Arrays.toString(rearrangeArray(nums)));
    }
}
