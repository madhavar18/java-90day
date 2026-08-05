package Striver_A2Z_Sheet.Arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class IntersectionOfTwoArrays {

    public static ArrayList<Integer> intersection(int[] a, int[] b) {
        int n1 = a.length, n2 = b.length;
        int i = 0, j = 0;
        ArrayList<Integer> ins = new ArrayList<>();

        while(i < n1 && j < n2) {
            if(a[i] < b[j]) {
                i++;
            }
            else if(b[j] < a[i]) {
                j++;
            }
            else {
                ins.add(a[i]);
                i++;
                j++;
            }
        }
        return ins;
    }

    public static void main(String[] args) {
        Random r = new Random();
        int[] a = new int[20];
        int[] b = new int[18];
        for(int i = 0; i < a.length; i++) {
            a[i] = r.nextInt(0, 12);
        }
        Arrays.sort(a);
        for(int i = 0; i < b.length; i++) {
            b[i] = r.nextInt(0, 12);
        }
        Arrays.sort(b);

        System.out.println("The two arrays are: ");
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(b));
        System.out.println("The INTERSECTION of these two arrays is: ");
        System.out.println(intersection(a, b));

    }
}
