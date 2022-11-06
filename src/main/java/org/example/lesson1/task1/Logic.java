package org.example.lesson1.task1;

import java.util.Arrays;
import java.util.Scanner;

public class Logic {

    /**
     * It asks the user to enter the length of the array, then it asks the user to enter the numbers of the array, and
     * finally it returns the array
     *
     * @return An array of integers.
     */
    public static int[] getInitialArrayFromUser() {

        //Determining the size of our initial array.
        System.out.println("Please, enter the length of your array!");
        Scanner in = new Scanner(System.in);
        int length = in.nextInt();
        int[] array = new int[length];

        //Filling our array with numbers
        System.out.println("Now, enter every number of your initial array!");
        for (int i = 0; i < length; i++) {
            array[i] = in.nextInt();
        }

        System.out.println("Your array is:\n" + Arrays.toString(array));
        return array;
    }

    /**
     * It removes all negative numbers and zeroes from the array.
     *
     * @param array The array that we want to remove negative numbers and zeroes from.
     * @return The method returns an array of positive numbers.
     */
    public static int[] removeNegativeAndZeroFromArray(int[] array) {
        //We should determine the size of the array before creating it.
        //This for loop counts every positive number.
        int posNumbers = 0;
        for (int k : array) {
            if (k > 0) {
                posNumbers++;
            }
        }

        //Creating new array with size posNumbers
        int[] arrayWithoutNegAndZero = new int[posNumbers];
        int size = 0;

        //This loop is adding every positive number to new array
        for (int j : array) {
            if (j > 0) {
                arrayWithoutNegAndZero[size] = j;
                size++;
            }
        }

        System.out.println("Your array without negative numbers and zeroes is: \n"
                + Arrays.toString(arrayWithoutNegAndZero));
        return arrayWithoutNegAndZero;
    }

    /**
     * For each element in the array, compare it to the element next to it and swap them if the element on the left is
     * greater than the element on the right
     *
     * @param array the array to be sorted
     * @return The sorted array is being returned.
     */
    public static int[] bubbleSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (array[j] > array[j + 1]) {
                    // swap arr[j+1] and arr[j]
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
        System.out.println("Your sorted array is:\n" + Arrays.toString(array));
        return array;
    }
}
