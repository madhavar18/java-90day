package Striver_A2Z_Sheet.Arrays;

// Rotate Image or Rotate Matrix by 90 degrees.

public class RotateMatrix {

    public static void rotate(int[][] matrix) {
        int n = matrix.length;

        for(int i = 0; i < n; i++) {
            for(int j = i + 1; j < n; j++) {
                swap(matrix, i, j);
            }
        }

        for(int i = 0; i < n; i++) {
            reverse(matrix[i]);
        }
    }

    public static void swap(int[][] a, int i, int j) {
        int temp = a[i][j];
        a[i][j] = a[j][i];
        a[j][i] = temp;
    }

    public static void reverse(int[] a) {
        int i = 0, j = a.length - 1;
        while(i <= j) {
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
            i++;
            j--;
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        System.out.println("The original matrix is: ");
        // Print original matrix
        for (int[] row : matrix) {
            for (int val : row)
                System.out.print(val + " ");
            System.out.println();
        }

        // Rotate the matrix
        rotate(matrix);

        System.out.println("The Rotated matrix is: ");
        // Print rotated matrix
        for (int[] row : matrix) {
            for (int val : row)
                System.out.print(val + " ");
            System.out.println();
        }
    }
}
