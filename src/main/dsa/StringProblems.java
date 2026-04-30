package dsa;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Day 10 - String Problems + Sliding Window Pattern
 *
 * Problems:
 * 1. Longest SubString without repeating characters (LeetCode #3)
 * 2. Valid Anagram (LeetCode #242)
 * 3. Contains Duplicate (LeetCode #217)
 *
 * Pattern : Sliding Window - O(n2) -> O(n) by maintaining
 * a window of valid state and only adjusting edges.
 */
public class StringProblems {
    // ── PROBLEM 1: Longest Substring Without Repeating Characters ───
    // LeetCode #3 (Medium)
    // Time: O(n) — each char enters and leaves the window at most once
    // Space: O(min(n, alphabet)) — window at most size of alphabet
    //
    // WHY HashMap over HashSet here:
    // We need to know WHERE we last saw a character, not just IF we saw it.
    // HashMap<Character, Integer> maps char → last seen index.
    // This lets us JUMP left pointer directly, not inch it forward.
    public int lengthOfLongestSubstring(String s) {
        // Maps each character to the index where we last saw it
        Map<Character, Integer> lastSeen = new HashMap<>();
        int maxLen = 0;
        int left = 0;

        for(int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);

            // If c was seen AND  it's inside our current window:
            // left must jump past the previous occurrence of c
            if(lastSeen.containsKey(c) && lastSeen.get(c) >= left) {
                left = lastSeen.get(c) + 1;
                // WHY not left++?
                // If 'a' appears at index 2 and right is at index 8,
                // we jump left to 3 directly — skipping the invalid range.
                // Inching left++ would be O(n²) in disguise.
            }

            lastSeen.put(c, right); // update last seen position
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }

    // ── PROBLEM 2: Valid Anagram ─────────────────────────────────────
    // LeetCode #242 (Easy)
    // Time: O(n) — two passes, both linear
    // Space: O(1) — frequency array is always size 26, regardless of n
    //
    // WHY int[26] instead of HashMap here:
    // We know characters are only lowercase English letters (a-z).
    // Fixed alphabet = fixed size array = O(1) space.
    // Array access by index is faster than HashMap for this case.
    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length()) return false;

        int[] freq = new int[26]; // index 0='a', index 25='z'

        for(char c : s.toCharArray()) {
            freq[c - 'a']++; // c - 'a' converts char to 0-25 index
        }

        for(char c : t.toCharArray()) {
            freq[c - 'a']--; // c - 'a' converts char to 0-25 index
        }

        // If anagram: every char in s is offset by same char in t
        // All frequencies should be 0
        for(int count : freq) {
            if(count != 0) return false;
        }
        return true;

        // WHY c - 'a'?
        // Characters are stored as ASCII values. 'a'=97, 'b'=98, ..., 'z'=122.
        // Subtracting 'a' maps: 'a'→0, 'b'→1, ..., 'z'→25.
        // This is a common pattern for fixed lowercase alphabet problems.
    }

    // ── PROBLEM 3: Contains Duplicate ────────────────────────────────
    // LeetCode #217 (Easy)
    // Time: O(n) — one pass
    // Space: O(n) — HashSet stores up to n elements
    //
    // WHY HashSet:
    // We only need to know IF we've seen a number before — not how many
    // times or where. HashSet is HashMap with only keys — simpler and
    // communicates intent more clearly.
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        for(int num : nums) {
            if(seen.contains(num)) return true;
            seen.add(num);
        }
        return false;
    }

    // ── BONUS: StringBuilder demonstration ───────────────────────────
    // Shows why String concatenation in loops is O(n²) and how
    // StringBuilder fixes it.
    public String reverseString(String s) {
        // WRONG way — O(n²) due to String immutability:
        // String result = "";
        // for (char c : s.toCharArray()) result = c + result;

        // RIGHT way — O(n) with StringBuilder:
        StringBuilder sb = new StringBuilder(s);
        return sb.reverse().toString();
        // StringBuilder.reverse() is O(n) — one pass, in-place swap
    }

    // Reverse manually to show the pattern:
    public String reverseManual(String s) {
        char[] chars = s.toCharArray(); // O(n) — copy to mutable array
        int left = 0, right = chars.length - 1;
        while (left < right) {
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }
        return new String(chars); // O(n) — copy back to String
        // Total: O(n) time, O(n) space
    }

    // ── MAIN: trace and test everything ──────────────────────────────
    public static void main(String[] args) {
        StringProblems sp = new StringProblems();

        // ── Longest Substring ─────────────────────────────────────────
        System.out.println("=== Longest Substring Without Repeating Chars ===");

        // Manually trace "abcabcbb" before running:
        // right=0: c='a', not in window. seen={a:0}. window="a". max=1
        // right=1: c='b', not in window. seen={a:0,b:1}. window="ab". max=2
        // right=2: c='c', not in window. seen={a:0,b:1,c:2}. window="abc". max=3
        // right=3: c='a', last seen at 0 >= left(0). left=1. seen={a:3,b:1,c:2}. window="bca". max=3
        // right=4: c='b', last seen at 1 >= left(1). left=2. seen={a:3,b:4,c:2}. window="cab". max=3
        // right=5: c='c', last seen at 2 >= left(2). left=3. seen={a:3,b:4,c:5}. window="abc". max=3
        // right=6: c='b', last seen at 4 >= left(3). left=5. window="cb". max=3
        // right=7: c='b', last seen at 6 >= left(5). left=7. window="b". max=3
        // Result: 3 ✓

        System.out.println(sp.lengthOfLongestSubstring("abcabcbb")); // 3
        System.out.println(sp.lengthOfLongestSubstring("bbbbb"));    // 1
        System.out.println(sp.lengthOfLongestSubstring("pwwkew"));   // 3
        System.out.println(sp.lengthOfLongestSubstring(""));         // 0
        System.out.println(sp.lengthOfLongestSubstring("au"));       // 2

        // ── Valid Anagram ──────────────────────────────────────────────
        System.out.println("\n=== Valid Anagram ===");
        System.out.println(sp.isAnagram("anagram", "nagaram")); // true
        System.out.println(sp.isAnagram("rat", "car"));         // false
        System.out.println(sp.isAnagram("a", "ab"));            // false
        System.out.println(sp.isAnagram("listen", "silent"));   // true

        // Trace "anagram" vs "nagaram":
        // After s pass: freq[0]+=1(a), freq[13]+=1(n), freq[0]+=1(a),
        //               freq[6]+=1(g), freq[17]+=1(r), freq[0]+=1(a), freq[12]+=1(m)
        // After t pass: same chars, all frequencies → 0. Return true. ✓

        // ── Contains Duplicate ─────────────────────────────────────────
        System.out.println("\n=== Contains Duplicate ===");
        System.out.println(sp.containsDuplicate(new int[]{1,2,3,1}));     // true
        System.out.println(sp.containsDuplicate(new int[]{1,2,3,4}));     // false
        System.out.println(sp.containsDuplicate(new int[]{1,1,1,3,3,4})); // true

        // ── String Reversal ────────────────────────────────────────────
        System.out.println("\n=== String Reversal ===");
        System.out.println(sp.reverseString("hello"));           // olleh
        System.out.println(sp.reverseManual("abcdef"));          // fedcba
        System.out.println(sp.reverseManual("racecar"));         // racecar

        // ── StringBuilder vs String concatenation benchmark ────────────
        System.out.println("\n=== StringBuilder vs String concat benchmark ===");
        int N = 50_000;

        long start = System.nanoTime();
        String s = "";
        for (int i = 0; i < N; i++) s += "a";
        long stringTime = System.nanoTime() - start;

        start = System.nanoTime();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) sb.append("a");
        String result = sb.toString();
        long sbTime = System.nanoTime() - start;

        System.out.printf("String concat (%,d iters):  %,d ms%n",
                N, stringTime / 1_000_000);
        System.out.printf("StringBuilder (%,d iters):  %,d ms%n",
                N, sbTime / 1_000_000);
        System.out.printf("Speedup: %.0fx%n", (double) stringTime / sbTime);
    }
}
