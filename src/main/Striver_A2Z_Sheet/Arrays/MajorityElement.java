package Striver_A2Z_Sheet.Arrays;

import java.util.Arrays;

public class MajorityElement {

    public static int mooreVotingAlgo(int[] a) {
        int count = 0;
        int ele = a[0];
        // implementing moore's voting algorithm.
        /* the algorithm goes like this
        * consider the first element of the array as the majority element
        * start iterating through the array
        * when ever the considered element appears, increment the count
        * otherwise, decrement count
        * if count reaches zero, consider the current element as majority element
        * iterate till the end of the array to find the majority element*/

        for(int i = 0; i < a.length; i++) {
            if(count == 0) {
                count = 1;
                ele = a[i];
            }
            else if(a[i] == ele) count++;
            else count--;
        }

        // Verification of the considered element
        count = 0;
        for(int i = 0; i <a.length; i++) {
            if(a[i] == ele) count++;
        }
        return count > (a.length / 2) ? ele : -1;
    }

    public static void main(String[] args) {
        int[] a = new int[] {7,7,5,7,5,1,5,7,5,5,7,7,5,5,5,5};
        System.out.println("The arrays is: " + Arrays.toString(a));
        System.out.println("The majority element is: " + mooreVotingAlgo(a));
    }
}
