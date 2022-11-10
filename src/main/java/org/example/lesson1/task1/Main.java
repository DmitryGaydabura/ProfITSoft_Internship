package org.example.lesson1.task1;

/* 1. Зробити метод, який приймає на вхід масив цілих чисел,
і повертає тільки ті з них, які позитивні (>=0), відсортувавши їх за спаданням.
Зробити unit-тести для цього методу.
*/

public class Main {
    public static void main(String[] args) {
        //Step 1: Create initial Array
        int[] array = Logic.getInitialArrayFromUser();

        //Step 2: Remove all negative integers and zeroes
        int[] positiveArray = Logic.removeNegAndZero(array);

        //Step 3: Sort array
        int[] sortedArray = Logic.bubbleSort(positiveArray);


    }






}
