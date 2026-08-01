package Striver_A2Z_Sheet.Arrays;

public class NoOfUniqueElements {

    public static int noOfUniqueElement(int[] a) {
        int i = 0, j = 1;
        while(i < a.length && j < a.length) {
            if(a[i] != a[j]) {
                a[i + 1] = a[j];
                i = i + 1;
            }
            else j++;
        }
        return i + 1;
    }

    public static void main(String[] args) {
        int[] a = new int[] {1,1,2,2,2,3,3,3,3};
        System.out.println(noOfUniqueElement(a));
    }
}
