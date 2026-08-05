package Striver_A2Z_Sheet.Arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class UnionOfTwoArrays {

    public static ArrayList<Integer> union(int[] a, int[] b) {
        int i = 0, j = 0;
        int n1 = a.length, n2 = b.length;
        ArrayList<Integer> union = new ArrayList<>();

        while(i < n1 && j < n2) {
            if(a[i] <= b[j]) {
                if(union.isEmpty() || !union.contains(a[i])) {
                    union.add(a[i]);
                }
                i++;
            }
            else {
                if(union.isEmpty() || !union.contains(b[j])) {
                    union.add(b[j]);
                }
                j++;
            }
        }

        while(j < n2) {
            if(union.isEmpty() || !union.contains(b[j])) {
                union.add(b[j]);
            }
            j++;
        }

        while(i < n1) {
            if(union.isEmpty() || !union.contains(a[i])) {
                union.add(a[i]);
            }
            i++;
        }

        return union;
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
        System.out.println("The UNION of these two arrays is: ");
        System.out.println(union(a, b));

    }
}
