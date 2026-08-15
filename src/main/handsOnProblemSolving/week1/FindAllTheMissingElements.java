package handsOnProblemSolving.week1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Spliterator;

public class FindAllTheMissingElements {

    public static ArrayList<Integer> findDisappearedNumbers(int[] nums) {
        ArrayList<Integer> n=new ArrayList<>();
        for(int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;

            if(nums[index] > 0) {
                nums[index] = -nums[index];
            }
        }

        for(int i = 0; i < nums.length; i++) {
            if(nums[i] > 0)
                n.add(i + 1);
        }
        return n;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {4,3,2,7,8,2,3,1};

        System.out.println("The array elements are: " + Arrays.toString(nums));
        System.out.println("The missing elements are: " + findDisappearedNumbers(nums));
    }
}
