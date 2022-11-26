package org.example.lesson1_2.task3;

//3. Реалізувати метод, який сортує геометричні 3d фігури за об'ємом.
// Метод приймає параметром колекцію довільних геометричних фігур (куб, кулю, циліндр).
// Написати unit-тести для методу.


import org.example.lesson1_2.task3.figures.Figure;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        // Creating a new ArrayList of type Figure.
        ArrayList<Figure> list = new ArrayList<>();

        // Generating random numbers from 0 to 9.
        int numberOfCubes = (int) (Math.random() * 10);
        int numberOfCylinders = (int) (Math.random() * 10);
        int numberOfSpheres = (int) (Math.random() * 10);

        // Adding random figures to the list.
        list = Logic.addRandomCubes(list, numberOfCubes);
        list = Logic.addRandomCylinders(list, numberOfCylinders);
        list = Logic.addRandomSpheres(list, numberOfSpheres);

        //Printing the unsorted list.
        System.out.println("Initial List:");
        Logic.printList(list);


        // Printing the sorted list.
        ArrayList<Figure> sortedList = Logic.sortFigures(list);
        System.out.println("Sorted List:");
        Logic.printList(sortedList);



    }


}
