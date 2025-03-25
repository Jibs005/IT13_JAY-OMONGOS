
package it13_jay.omongos;

public class array {
  package Midterm;

public class CombinedArray {
    public static void main(String[] args) {
        String[] letters = {"A", "B", "C", "D", "E", "F", "G"};
        
        System.out.print("Single-dimensional array: ");
        for (int i = 0; i < letters.length; i++) {
            System.out.print(letters[i]);
            if (i < letters.length - 1) {
                System.out.print(",");
            }
        }
        System.out.println();

        int[][] arr = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12}
        };
        
        System.out.println("\nMulti-dimensional array:");
        for (int i = 0; i < arr.length; i++) {
            System.out.print("The elements in row " + (i + 1) + ": ");
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}  
}
