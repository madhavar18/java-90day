package Striver_A2Z_Sheet.Arrays;

import java.util.HashMap;

public class FindAllSubarraysWithGivenSumK {

    public static int subarraySum(int[] nums, int k) {
        int preSum = 0, cnt = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for(int i = 0; i < nums.length; i++) {
            preSum += nums[i];
            int remove = preSum - k;
            if (map.containsKey(remove)) {
                cnt += map.get(remove);
            }

            // Update the frequency of the current prefix sum
            map.put(preSum, map.getOrDefault(preSum, 0) + 1);
        }

        return cnt;
    }

    public static void main(String[] args) {
        // Input array
        int[] arr = {1,2,3,-3,1,1,1,4,2,-3};

        // Target sum
        int k = 3;

        // Print the count of subarrays
        System.out.println("The number of subarrays is: " + subarraySum(arr, k));
    }
}
