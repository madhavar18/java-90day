package dsa;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Day 20 — Binary Search + Recursion + Memoization
 *
 * Visual invariant for binary search:
 * At every step, target is guaranteed to be in [left, right] if it exists.
 * When left > right, the search space is empty — target not found.
 */
public class BinarySearchAndRecursion {
    // ── BINARY SEARCH: ITERATIVE ─────────────────────────────────────
    // Time: O(log n) — halves search space each step
    // Space: O(1)  — no extra memory, just pointer variables
    //
    // WHY iterative preferred over recursive for binary search:
    // The iterative version uses O(1) space.
    // The recursive version uses O(log n) call stack frames.
    // For n = 1 billion, recursive binary search uses 30 stack frames.
    // Iterative uses 3 variables. Functionally identical, space is different.
    public int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while(left <= right) {
            // WHY left + (right - left) / 2 instead of (left + right) / 2:
            // If left = 1,000,000,000 and right = 1,000,000,001,
            // left + right overflows Integer.MAX_VALUE (2,147,483,647).
            // left + (right - left) / 2 never overflows because (right - left)
            // is always a small number. Classic interview trap.
            int mid = left + (right - left) / 2;

            if(nums[mid] == target) {
                return mid; // found - return index
            } else if(nums[mid] < target) {
                left = mid + 1; // target in right half
                // WHY mid + 1 not mid:
                // nums[mid] != target, so mid itself is eliminated.
                // Left boundary moves PAST mid.
            } else {
                right = mid - 1; // target in left half
            }
        }
        return -1; // left > right - Search space empty, not found
    }

    // ── BINARY SEARCH: RECURSIVE ─────────────────────────────────────
    // Time: O(log n) — same as iterative
    // Space: O(log n) — call stack depth = number of halvings
    public int binarySearchRecursive(int[] nums, int target, int left, int right) {
        // Base case: search space is empty
        if(left > right) return -1;

        int mid = left + (right - left) / 2;

        if(nums[mid] == target) return mid;
        if(nums[mid] < target) return binarySearchRecursive(nums, target, mid + 1, right);
        return binarySearchRecursive(nums, target, left, mid - 1);

        // Call stack for binarySearchRecursive([1,3,5,7,9], 7, 0, 4):
        // Frame 1: left=0, right=4, mid=2, arr[2]=5 < 7 → go right
        // Frame 2: left=3, right=4, mid=3, arr[3]=7 == 7 → return 3
        // Frame 2 returns 3 → Frame 1 returns 3
        // Two stack frames for 5 elements. log₂(5) ≈ 2.32 ✓
    }

    // ── FIND FIRST BAD VERSION (LeetCode #278) ───────────────────────
    // Binary search variant — finding a boundary, not an exact value.
    // Problem: versions [1..n], first bad version causes all after to be bad.
    // Find the first bad version with minimum API calls.
    //
    // WHY binary search: the array of versions has a property:
    // [good, good, good, BAD, BAD, BAD]
    // Everything left of first bad = good. Everything right = bad.
    // This is a sorted property — binary search applies.
    //
    // The invariant: first bad version is always in [left, right].
    // When left == right, we've found it.
    public int firstBadVersion(int n, int firstBad) {
        // Simulating isBadversion API
        // In the real Leetcode problem, isBadVersion is provided
        int left = 1;
        int right = n;

        while(left < right) { // WHY < not <=: when left == right, we have the answer
            int mid = left + (right - left) / 2;

            if(isBadVersion(mid, firstBad)) {
                right = mid; // mid could be the first bad - don't eliminate it
                // WHY right = mid not mid - 1:
                // if mid is bad, it might be the FIRST bad.
                // Setting right = mid -1 could eliminate the answer.
            } else {
                left = mid + 1; // mid is good - definitely not the first bad
            }
        }

        return left; // left == right == first bad version
    }

    private boolean isBadVersion(int version, int firstBad) {
        return version >= firstBad;
    }

    // ── FIBONACCI: NAIVE RECURSION ────────────────────────────────────
    // Time: O(2ⁿ) — exponential. NEVER use this in production.
    // Space: O(n) — maximum call stack depth
    public long fibNaive(int n) {
        if(n <= 1) return n; // base cases: fib(0) = 0, fib(1) = 1
        return fibNaive(n - 1) + fibNaive(n - 2);
        // fib(50) makes over 2 trillion calls. Try fib(45) and see it hang.
    }

    // ── FIBONACCI: MEMOIZED (Top-Down DP) ────────────────────────────
    // Time: O(n) — each value computed exactly once
    // Space: O(n) — memo map + call stack
    private Map<Integer, Long> memo = new HashMap<>();

    public long fibMemo(int n) {
        if(n <= 1) return n; // base cases
        // Check cache before computing
        if(memo.containsKey(n)) return memo.get(n);

        long result = fibMemo(n - 1) + fibMemo(n - 2);
        memo.put(n, result);
        return result;
    }

    // ── FIBONACCI: ITERATIVE (Bottom-Up DP) ──────────────────────────
    // Time: O(n) — single loop
    // Space: O(1) — only two variables needed
    //
    // WHY this is the best version:
    // Same O(n) time as memoized but O(1) space (no HashMap, no call stack).
    // Bottom-up builds from fib(0), fib(1) upward — no recursion.
    // This is the production version.
    public long fibIterative(int n) {
        if(n <= 1) return n; // base cases
        long prev2 = 0;
        long prev1 = 1;

        for(int i = 2; i <= n; i++) {
            long current = prev1 + prev2;
            prev2 = prev1;
            prev1 = current;
        }

        return prev1;
    }

    // ── MAIN ─────────────────────────────────────────────────────────
    public static void main(String[] args) {
        BinarySearchAndRecursion bsar = new BinarySearchAndRecursion();

        // Binary search tests
        System.out.println("=== Binary Search ===");
        int[] sorted = {2, 5, 8, 12, 16, 23, 38, 45, 56, 72, 91};
        System.out.println("Array: " + Arrays.toString(sorted));
        System.out.println("Search 23: index " + bsar.binarySearch(sorted, 23)); // 5
        System.out.println("Search 38: index " + bsar.binarySearch(sorted, 38)); // 6
        System.out.println("Search 99: index " + bsar.binarySearch(sorted, 99)); // -1
        System.out.println("Search 2:  index " + bsar.binarySearch(sorted, 2));  // 0

        // Recursive version
        System.out.println("\nRecursive search 56: index " +
                bsar.binarySearchRecursive(sorted, 56, 0, sorted.length - 1)); // 8

        // First bad version
        System.out.println("\n=== First Bad Version ===");
        System.out.println("n=10, firstBad=4: " + bsar.firstBadVersion(10, 4));  // 4
        System.out.println("n=5,  firstBad=1: " + bsar.firstBadVersion(5, 1));   // 1
        System.out.println("n=1,  firstBad=1: " + bsar.firstBadVersion(1, 1));   // 1

        // Fibonacci comparison
        System.out.println("\n=== Fibonacci: Naive vs Memoized vs Iterative ===");
        int n = 40;

        long start = System.nanoTime();
        long naiveResult = bsar.fibNaive(n);
        long naiveTime = System.nanoTime() - start;

        start = System.nanoTime();
        long memoResult = bsar.fibMemo(n);
        long memoTime = System.nanoTime() - start;

        start = System.nanoTime();
        long iterResult = bsar.fibIterative(n);
        long iterTime = System.nanoTime() - start;

        System.out.printf("fib(%d) = %d%n", n, naiveResult);
        System.out.printf("Naive:     %,d ms%n", naiveTime / 1_000_000);
        System.out.printf("Memoized:  %,d ns%n", memoTime);
        System.out.printf("Iterative: %,d ns%n", iterTime);
        System.out.printf("Naive is ~%.0fx slower than memoized%n",
                (double) naiveTime / memoTime);

        // Binary search scale demonstration
        System.out.println("\n=== Binary Search Scale ===");
        int[] billion = new int[1_000_000_000];
        // Can't actually create this — but show the math:
        System.out.println("For n = 1,000,000,000:");
        System.out.printf("  Linear scan average:  %,d comparisons%n", 500_000_000);
        System.out.printf("  Binary search max:    %d comparisons (log₂(1B) ≈ 30)%n", 30);
        System.out.printf("  Speedup factor:       %,dx%n", 500_000_000 / 30);
    }
}
