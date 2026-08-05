package Striver_A2Z_Sheet.Arrays;

import java.util.Arrays;
import java.util.Random;

public class FindMissingNumber {

    public static int find(int[] a, int N) {
        int n = N - 1;
        int xor1 = 0, xor2 = 0;
        for (int i = 0; i < n; i++) {
            xor2 = xor2 ^ a[i];
            xor1 = xor1 ^ (i + 1);
        }
        xor1 = xor1 ^ N;
        return xor1 ^ xor2;
    }

    public static void main(String[] args) {

    Random r = new Random();
    int[] a = new int[] {1,2,4,5};
    int N = 5;
    System.out.println("The array is: ");
    System.out.println(Arrays.toString(a));
    System.out.println(find(a, N));

    }
}
