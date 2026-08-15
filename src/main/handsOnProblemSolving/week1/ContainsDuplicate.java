package handsOnProblemSolving.week1;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class ContainsDuplicate {

    public static boolean containsDuplicate(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        for(int num : nums) {
            if(seen.contains(num)) return true;
            seen.add(num);
        }
        return false;
    }

    public static void main(String[] args) {
        int[] a = new int[20];
        Random r = new Random();
        for(int i = 0; i < a.length; i++) {
            a[i] = r.nextInt(1, 175);
        }

        System.out.println("The array is: " + Arrays.toString(a));
        System.out.println("Does the array contain duplicates: " + (containsDuplicate(a) ? "yes" : "no"));
    }
}
