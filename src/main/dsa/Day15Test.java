package dsa;

/**
 * Day 15 Tests — LinkedList operations + LeetCode problems
 * LeetCode #206: Reverse Linked List
 * LeetCode #876: Middle of the Linked List
 */
public class Day15Test {
    public static void main(String[] args) {

        // ── TEST 1: Basic operations ──────────────────────────────────
        System.out.println("=== Basic LinkedList Operations ===");
        LinkedList<String> tasks = new LinkedList<>();

        tasks.addLast("Complete React assignment");
        tasks.addLast("Study for DBMS exam");
        tasks.addLast("Build portfolio");
        System.out.println("After addLast x3: " + tasks);
        // [Complete React assignment → Study for DBMS exam → Build portfolio]

        tasks.addFirst("URGENT: Submit project");
        System.out.println("After addFirst: " + tasks);
        // [URGENT: Submit project → Complete React assignment → ...]

        System.out.println("Size: " + tasks.size()); // 4
        System.out.println("Get(0): " + tasks.get(0)); // URGENT: Submit project
        System.out.println("Get(2): " + tasks.get(2)); // Study for DBMS exam

        // ── TEST 2: Removal ───────────────────────────────────────────
        System.out.println("\n=== Removal Operations ===");
        String removed = tasks.removeFirst();
        System.out.println("Removed first: " + removed);
        System.out.println("After removeFirst: " + tasks);

        tasks.remove("Study for DBMS exam");
        System.out.println("After removing by value: " + tasks);

        // ── TEST 3: Reverse (LeetCode #206 equivalent) ────────────────
        System.out.println("\n=== Reverse ===");
        LinkedList<Integer> nums = new LinkedList<>();
        for (int n : new int[]{1, 2, 3, 4, 5}) nums.addLast(n);
        System.out.println("Before: " + nums);
        nums.reverse();
        System.out.println("After:  " + nums);
        // [5 → 4 → 3 → 2 → 1]

        // ── TEST 4: Find Middle (LeetCode #876 equivalent) ────────────
        System.out.println("\n=== Find Middle ===");
        LinkedList<Integer> odd = new LinkedList<>();
        for (int n : new int[]{1, 2, 3, 4, 5}) odd.addLast(n);
        System.out.println("List: " + odd + " | Middle: " + odd.findMiddle());
        // Middle: 3 (index 2 of 5 elements)

        LinkedList<Integer> even = new LinkedList<>();
        for (int n : new int[]{1, 2, 3, 4}) even.addLast(n);
        System.out.println("List: " + even + " | Middle: " + even.findMiddle());
        // Middle: 3 (second middle for even-length — LeetCode convention)

        // ── TEST 5: Complexity demonstration ─────────────────────────
        System.out.println("\n=== Complexity Comparison: addFirst ===");
        int N = 100_000;

        // LinkedList addFirst — O(1) each = O(n) total
        LinkedList<Integer> ll = new LinkedList<>();
        long start = System.nanoTime();
        for (int i = 0; i < N; i++) ll.addFirst(i);
        long llTime = System.nanoTime() - start;

        // ArrayList addFirst equivalent (add at index 0) — O(n) each = O(n²) total
        java.util.ArrayList<Integer> al = new java.util.ArrayList<>();
        start = System.nanoTime();
        for (int i = 0; i < N; i++) al.add(0, i); // add at front — O(n) shift
        long alTime = System.nanoTime() - start;

        System.out.printf("LinkedList addFirst (%,d ops): %,d ms%n", N, llTime/1_000_000);
        System.out.printf("ArrayList  addFirst (%,d ops): %,d ms%n", N, alTime/1_000_000);
        System.out.printf("LinkedList is %.0fx faster for front insertions%n",
                (double) alTime / llTime);
        // This benchmark proves WHY LinkedList exists — same operation, dramatic difference
    }
}