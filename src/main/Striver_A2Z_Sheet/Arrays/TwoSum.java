package Striver_A2Z_Sheet.Arrays;

import java.util.Arrays;
import java.util.Random;

public class TwoSum {

    public static String find(int[] a, int target) {
        int left = 0, right = a.length - 1;
        Arrays.sort(a);
        while(left < right) {
            if(a[left] + a[right] == target) return "YES, they are: " + a[left] + " " + a[right];
            else if(a[left] + a[right] < target) left++;
            else right--;
        }
        return "NO";
    }

    public static void main(String[] args) {
        Random r = new Random();
        int[] a = new int[20];
        for(int i = 0; i < a.length; i++) {
            a[i] = r.nextInt(1, 31);
        }
        System.out.println("The array is: " + Arrays.toString(a));
        System.out.println("Are there two numbers that add up to 40: " + find(a, 40));
    }
}
