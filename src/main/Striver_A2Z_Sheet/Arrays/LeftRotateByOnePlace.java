package Striver_A2Z_Sheet.Arrays;

import java.util.Arrays;
import java.util.Random;

public class LeftRotateByOnePlace {

    public static int[] rotate(int[] a) {
        int st = a[0];
        for(int i = 0; i < a.length - 1; i++) {
            int temp = a[i];
            a[i] = a[i + 1];
            a[i + 1] = temp;
        }
        a[a.length - 1] = st;
        return a;
    }

    public static void main(String[] args) {
        Random r = new Random();
        int[] a = new int[20];
        for(int i = 0; i < a.length; i++) {
            a[i] = r.nextInt(0, 30);
        }
        System.out.println("The original array is: " + Arrays.toString(a));
        System.out.println("Array after left rotating each element by one place: " + Arrays.toString(rotate(a)));
    }
}
