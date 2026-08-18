package Striver_A2Z_Sheet.Arrays;

import java.util.Arrays;
import java.util.HashSet;

public class LongestConsecutiveSeq {

    public static int find(int[] nums) {

        if(nums.length == 0) return 0;

        HashSet<Integer> st = new HashSet<>();
        for(int num : nums) {
            st.add(num);
        }

        int longest = 1;

        for(int num : st) {
            if(!st.contains(num - 1)) {
                int cnt = 1;
                int n = num;
                while(st.contains(n + 1)) {
                    cnt += 1;
                    n = n + 1;
                }
                longest = Math.max(longest, cnt);
            }
        }

        return longest;
    }

    public static void main(String[] args) {
        // Input array
        int[] a = {100, 4, 200, 1, 3, 2};

        System.out.println("The array elements are: " + Arrays.toString(a));
        // Output the result
        System.out.println("The longest consecutive sequence is " + find(a));
    }
}
