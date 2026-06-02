package infosys_prep;
import java.util.*;
public class FibonacciDP {
    public int fibonacci(int n, int[] dp) {
        // recursion base case
        if(n <= 1) return n;

        // Memoization kicks in - precomputed values used from the dp list
        if(dp[n] != -1) return dp[n];

        // Recursion - for values that have to be computed for the first time
        return dp[n] = fibonacci(n - 1, dp) + fibonacci(n - 2, dp);
    }

    public static void main(String[] args) {
        FibonacciDP f = new FibonacciDP();
        int n = 5;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        System.out.println(f.fibonacci(n, dp));
    }
}
