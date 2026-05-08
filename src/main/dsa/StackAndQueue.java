package dsa;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Day 16 — Stack and Queue implementations
 *
 * WHY implement these when Java has java.util.Stack and java.util.Queue?
 * Same reason we built LinkedList: implementation reveals understanding.
 * java.util.Stack is actually deprecated — Java recommends using Deque.
 * Understanding why Stack is deprecated teaches you about interface design.
 */

public class StackAndQueue {
    // ── STACK using ArrayList ─────────────────────────────────────────
    //
    // WHY ArrayList for Stack implementation:
    // Stack operations happen at ONE end — the top.
    // ArrayList's addLast (add to end) = O(1) amortised.
    // ArrayList's removeLast = O(1).
    // Both stack operations are O(1) with ArrayList.
    // If we used the front: addFirst = O(n) shift. Slow.
    // Stack top = ArrayList's last element. Natural mapping.
    static class Stack<T> {
        private final ArrayList<T> data = new ArrayList<>();

        // Push - add to top - O(1) amortised
        public void push(T item) {
            data.add(item); // adds to end = top of stack
        }

        // Pop - remove from top - O(1)
        public T pop() {
            if(data.isEmpty()) throw new RuntimeException("Stack underflow - stack is empty");
            return data.remove(data.size() - 1);
        }

        // Peek — read top without removing — O(1)
        // WHY peek: you often need to know what's on top before deciding to pop
        // Popping to read, then pushing back is wasteful and error-prone
        public T peek() {
            if(data.isEmpty()) throw new RuntimeException("Stack is empty");
            return data.get(data.size() - 1);
        }

        public boolean isEmpty() { return data.isEmpty(); }
        public int size() { return data.size(); }

        @Override
        public String toString() { return data.toString(); }
    }

    // WHY LinkedList over ArrayList for Queue:
    // ArrayList dequeue (removeFirst) = O(n) — shifts everything left.
    // For a queue processing 10,000 items, that's 10,000 shifts per dequeue.
    // LinkedList removeFirst = O(1) — just move the head pointer.
    // LinkedList addLast = O(n)... UNLESS we keep a TAIL pointer.
    //
    // Real LinkedList-based queues keep both HEAD and TAIL references:
    // - enqueue: add new node at TAIL. O(1) — no walking.
    // - dequeue: remove HEAD. O(1) — just move head pointer.
    // This is how java.util.LinkedList implements it internally.
    static class Queue<T> {
        private final LinkedList<T> data = new LinkedList<>();

        // Enqueue - add to back - O(1)
        public void enqueue(T item) {
            data.addLast(item); // java.util.LinkedList maintains a tail pointer
        }

        // Dequeue - remove from front - O(1)
        public T dequeue() {
            if(data.isEmpty()) throw new RuntimeException("Queue underflow - queue is empty");
            return data.removeFirst();
        }

        // Peek front - O(1)
        public T peekFront() {
            if(data.isEmpty()) throw new RuntimeException("Queue is empty");
            return data.getFirst();
        }

        public boolean isEmpty() { return data.isEmpty(); }
        public int size() { return data.size(); }

        @Override
        public String toString() { return data.toString(); }
    }

    // ── PROBLEM 1: Valid Parentheses (LeetCode #20) ──────────────────
    //
    // WHY a Stack solves this naturally:
    // When you see an opening bracket, you don't know yet if it'll be
    // closed correctly — you have to wait. But you need to REMEMBER it.
    // When you see a closing bracket, you need the MOST RECENT unmatched
    // opening bracket — because inner brackets must close before outer ones.
    // "Most recent unmatched" = top of a stack. LIFO = natural fit.
    //
    // The problem structure TELLS you to use a stack.
    // Any time a problem requires matching the "most recent" of something,
    // a stack is almost certainly the right tool.
    //
    // Time: O(n) — one pass through the string
    // Space: O(n) — stack stores at most n/2 opening brackets
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for(char c :  s.toCharArray()) {
            // Opening brakets: push and wait
            if(c == '(' || c == '{' || c == '[') {
                stack.push(c);
            }
            // Closing brackets: must match the most recent opening bracket
            else {
                // If stack is empty, there's no matching opener - invalid
                if(stack.isEmpty()) return false;

                char top = stack.pop();

                // Each closing bracket must match its corresponding opener
                if(c == ')' && top != '(') return false;
                if (c == ']' && top != '[') return false;
                if (c == '}' && top != '{') return false;
            }
        }
        // After processing all characters:
        // If stack is empty — every opener was matched — valid
        // If stack is not empty — some openers were never closed — invalid
        return stack.isEmpty();
    }

    // ── PROBLEM 2: Daily Temperatures (LeetCode #739) ────────────────
    //
    // Problem: given temperatures = [73,74,75,71,69,72,76,73],
    // return how many days until a warmer temperature for each day.
    // Output: [1,1,4,2,1,1,0,0]
    //
    // Brute force O(n²): for each day, scan forward until warmer day found.
    //
    // WHY a Stack gives O(n):
    // We're looking for the NEXT GREATER element for each position.
    // This is the "monotonic stack" pattern — a stack that maintains
    // a monotonic (always increasing or always decreasing) order.
    //
    // Key insight: maintain a stack of INDICES of days waiting for a warmer day.
    // The stack stays in decreasing temperature order (monotonic decreasing).
    // When we see a temperature WARMER than the stack top:
    // — the current day is the answer for the stack top's day
    // — pop the stack top and record the distance
    // — repeat until stack top is warmer than current day
    //
    // Time: O(n) — each index pushed once, popped at most once = 2n total
    // Space: O(n) — stack holds at most n indices
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] result = new int[n]; // default 0 - days with no warmer future days
        Stack<Integer> stack = new Stack<>(); // stores INDICES, not temperatures

        for(int i = 0; i < n; i++) {
            // While current temperature is warmer than the day at stack top:
            while(!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int prevIndex = stack.pop();
                result[prevIndex] = i - prevIndex;
            }
            stack.push(i); // push current index - waiting for its warmer day
        }

        return result;
    }

    // Trace for [73, 74, 75, 71, 69, 72, 76, 73]:
    // i=0: t=73, stack empty, push 0.        stack=[0]
    // i=1: t=74 > t[0]=73, pop 0, result[0]=1-0=1. push 1.  stack=[1]
    // i=2: t=75 > t[1]=74, pop 1, result[1]=2-1=1. push 2.  stack=[2]
    // i=3: t=71 < t[2]=75, push 3.           stack=[2,3]
    // i=4: t=69 < t[3]=71, push 4.           stack=[2,3,4]
    // i=5: t=72 > t[4]=69, pop 4, result[4]=5-4=1.
    //             t=72 > t[3]=71, pop 3, result[3]=5-3=2.
    //             t=72 < t[2]=75, stop. push 5.  stack=[2,5]
    // i=6: t=76 > t[5]=72, pop 5, result[5]=6-5=1.
    //             t=76 > t[2]=75, pop 2, result[2]=6-2=4.
    //             stack empty. push 6.        stack=[6]
    // i=7: t=73 < t[6]=76, push 7.           stack=[6,7]
    // End: indices 6,7 never found warmer day — result[6]=result[7]=0 (default)
    // Output: [1,1,4,2,1,1,0,0] ✓

    // ── MAIN ─────────────────────────────────────────────────────────
    public static void main(String[] args) {
        StackAndQueue saq = new StackAndQueue();

        // Stack tests
        System.out.println("=== Stack ===");
        Stack<String> pageHistory = new Stack<>();
        pageHistory.push("google.com");
        pageHistory.push("github.com");
        pageHistory.push("leetcode.com");
        System.out.println("History: " + pageHistory);
        System.out.println("Current page: " + pageHistory.peek());
        System.out.println("Back: " + pageHistory.pop());
        System.out.println("Back: " + pageHistory.pop());
        System.out.println("Now at: " + pageHistory.peek());

        // Queue tests
        System.out.println("\n=== Queue ===");
        Queue<String> printQueue = new Queue<>();
        printQueue.enqueue("Resume.pdf");
        printQueue.enqueue("CoverLetter.pdf");
        printQueue.enqueue("Portfolio.pdf");
        System.out.println("Print queue: " + printQueue);
        System.out.println("Printing: " + printQueue.dequeue());
        System.out.println("Printing: " + printQueue.dequeue());
        System.out.println("Remaining: " + printQueue);

        // Valid Parentheses
        System.out.println("\n=== Valid Parentheses ===");
        String[] tests = {"()", "()[]{}", "(]", "([)]", "{[]}", ""};
        for (String t : tests) {
            System.out.printf("'%s' → %s%n", t, saq.isValid(t));
        }
        // (), ()[]{}, {[]} → true
        // (], ([)] → false

        // Daily Temperatures
        System.out.println("\n=== Daily Temperatures ===");
        int[] temps = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] result = saq.dailyTemperatures(temps);
        System.out.print("Input:  ");
        for (int t : temps) System.out.print(t + " ");
        System.out.print("\nOutput: ");
        for (int r : result) System.out.print(r + " ");
        System.out.println();
        // Expected: 1 1 4 2 1 1 0 0
    }
}
