package Striver_A2Z_Sheet.Arrays;

import java.util.ArrayList;
import java.util.Arrays;

public class LeadersInArray {

    public static ArrayList<Integer> find(int[] a) {
        ArrayList<Integer> ans = new ArrayList<>();
        int max = -1;

        for(int i = a.length - 1; i >= 0; i--) {
            if(a[i] > max) {
                ans.add(a[i]);
            }
            max = Math.max(max, a[i]);
        }

        return ans;
    }

    public static void main(String[] args) {

        int[] a = new int[] {10,22,12,0,3,6};

        System.out.println("The array is: " + Arrays.toString(a));
        System.out.println("The leaders are: " + find(a));
    }
}
