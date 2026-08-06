package Striver_A2Z_Sheet.Arrays;

import java.util.Arrays;

public class FindElementThatAppearsOnce {

    public static int find(int[] a) {
        int xor = 0;
        for(int i : a) {
            xor = xor ^ i;
        }
        return xor;
    }

    public static void main(String[] args) {
        int[] a = new int[] {1,1,2,3,3,4,4,5,5,7,7,9,9};
        System.out.println("The array is: " + Arrays.toString(a));
        System.out.println("The missing number is: " + find(a));
    }
}
