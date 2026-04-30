package dsa;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Day 9 - Two Sum
 * LeetCode #1 (Easy)
 *
 * Problem: given int[] nums and int target, return indices of two
 * number that add up to target. Exactly one solution guaranteed.
 *
 * WHY this problem matters: it teaches the single most important
 * DSA insight - trading O(n) space for O(n) time using a HashMap.
 * This patter appears in 30%+ of LeetCode mediums once recognized.
 */
public class TwoSum {

    // -- Approach 1: Brute Force ------------------------------------------
    // Time: O(n2) - nested loops check every pair
    // Space: O(1) - no extra data structures
    // WHY show this first: understanding the naive solution
    // makes the optimised ones's insight more meaningful.
    public int[] twoSumBrute(int[] nums, int target) {
        for(int i = 0; i < nums.length; i++) {
            for(int j = i; j < nums.length; j++) {
                if(nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{}; // no solution found
    }

    // -- Approach 2: HashMap (Optimal) --------------------------------------
    // Time: O(n) - single pass through array
    // Space: O(n) - HashMap stores at most n entries
    //
    // KEY INSIGHT: for nums[i], the number we NEED is (target - nums[i]).
    // If we can check in O(1) whether that number exists and where,
    // one pass is enough. HashMap gives us that O(1) check.
    public int[] twoSumOptimal(int[] nums, int target) {
        // Maps: number value -> its index in nums
        Map<Integer, Integer> seen = new HashMap<>();
        for(int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];

            // O(1) lookup: have we seen the number we need?
            if(seen.containsKey(complement)) {
                // Yes - complement was seen at index seen.get(complement)
                return new int[]{seen.get(complement), i};
            }

            // No - record this number so future iterations can find it
            seen.put(nums[i], i);
        }

        return new int[]{}; // no solution found
    }

    // -- COMPLEXITY ANALYSIS --------------------------------------------
    // Why O(n) time and O(n2)?
    // The outer loop runs n times.
    // Inside: HashMap.containsKey() = O(1), HashMap.put() = O(1).
    //
    // Why O(n) space?
    // In the worst case, we scan the entire array before finding the pari.
    //The HashMap grows to hold n-1 entries.
    // Space scales with input =O(n).

    // -- MAIN:  test both approaches ---------------------------------------
    public static void main(String[] args) {
        TwoSum solver = new TwoSum();

        // Test case 1: standard case
        int[] nums1 = {2, 7, 11, 15};
        int target1 = 9;
        System.out.println("Test 1 — Brute:   " + Arrays.toString(solver.twoSumBrute(nums1, target1)));
        System.out.println("Test 1 — Optimal: " + Arrays.toString(solver.twoSumOptimal(nums1, target1)));
        // Expected: [0, 1]

        // Test case 2: answer not at the beginning
        int[] nums2 = {3, 2, 4};
        int target2 = 6;
        System.out.println("Test 2 — Brute:   " + Arrays.toString(solver.twoSumBrute(nums2, target2)));
        System.out.println("Test 2 — Optimal: " + Arrays.toString(solver.twoSumOptimal(nums2, target2)));
        // Expected: [1, 2]

        // Test case 3: answer uses same element twice? (problem says no — but test anyway)
        int[] nums3 = {3, 3};
        int target3 = 6;
        System.out.println("Test 3 — Brute:   " + Arrays.toString(solver.twoSumBrute(nums3, target3)));
        System.out.println("Test 3 — Optimal: " + Arrays.toString(solver.twoSumOptimal(nums3, target3)));
        // Expected: [0, 1]

        // Test case 4: answer at the end
        int[] nums4 = {1, 5, 3, 8, 2, 9};
        int target4 = 11;
        System.out.println("Test 4 — Brute:   " + Arrays.toString(solver.twoSumBrute(nums4, target4)));
        System.out.println("Test 4 — Optimal: " + Arrays.toString(solver.twoSumOptimal(nums4, target4)));
        // Expected: 3 + 8 = 11 → [2, 3]

        // ── BENCHMARK: feel the complexity difference ─────────────────
        System.out.println("\n=== Complexity Benchmark ===");
        int SIZE = 10_000;
        int[] large = new int[SIZE];
        for (int i = 0; i < SIZE; i++) large[i] = i;
        int largeTarget = (SIZE - 1) + (SIZE - 2); // last two elements

        long start = System.nanoTime();
        solver.twoSumBrute(large, largeTarget);
        long bruteTime = System.nanoTime() - start;

        start = System.nanoTime();
        solver.twoSumOptimal(large, largeTarget);
        long optimalTime = System.nanoTime() - start;

        System.out.printf("Brute force (n=10k):  %,d ns%n", bruteTime);
        System.out.printf("HashMap optimal (n=10k):      %,d ns%n", optimalTime);
        System.out.printf("Speedup:              %.0fx%n", (double) bruteTime / optimalTime);
    }
}
