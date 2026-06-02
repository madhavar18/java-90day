package dsa;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DynamicProgramming {

    // ── FIBONACCI ──────────────────────────────────────────────────────

    // Version 1: Naive recursion — O(2^n) time, O(n) space (call stack)
    // No optimization — for benchmarking only
    // Base cases: fib(0) = 0, fib(1) = 1
    // Recursive case: fib(n) = fib(n-1) + fib(n-2)
    public long fibNaive(int n) {
        if(n == 0 || n == 1) return n;
        return fibNaive(n - 1) + fibNaive(n - 2);
    }

    // Version 2: Top-down with memoization — O(n) time, O(n) space
    // Use a HashMap to store already-computed values
    // Before computing: check if memo contains n
    // After computing: store result in memo before returning
    private Map<Integer, Long> memo = new HashMap<>();
    public long fibMemo(int n) {
        if(n == 1 || n == 0) return n;

        if(memo.containsKey(n)) return memo.get(n);

        long result = fibMemo(n - 1) + fibMemo(n - 2);
        memo.put(n, result);

        return result;
    }

    // Version 3: Bottom-up iterative — O(n) time, O(1) space
    // No recursion, no HashMap
    // You only need the previous two values at any point
    // Think: prev2 = fib(n-2), prev1 = fib(n-1), current = fib(n)
    public long fibIterative(int n) {
        if(n == 1 || n == 0) return n;
        int prev1 = 0, prev2 = 1, current = 0;

        for(int i = 1; i < n; i++) {
            current = prev1 + prev2;
            prev1 = prev2;
            prev2 = current;
        }
        return current;
    }

    public static void main(String[] args) {
        DynamicProgramming dp = new DynamicProgramming();
        int n = 40;

        long start, end;

        start = System.nanoTime();
        long naive = dp.fibNaive(n);
        end = System.nanoTime();
        System.out.printf("Naive:     fib(%d) = %d, time = %,d ms%n",
                n, naive, (end - start) / 1_000_000);

        start = System.nanoTime();
        long memoResult = dp.fibMemo(n);
        end = System.nanoTime();
        System.out.printf("Memo:      fib(%d) = %d, time = %,d ns%n",
                n, memoResult, (end - start));

        start = System.nanoTime();
        long iterResult = dp.fibIterative(n);
        end = System.nanoTime();
        System.out.printf("Iterative: fib(%d) = %d, time = %,d ns%n",
                n, iterResult, (end - start));
    }
}
