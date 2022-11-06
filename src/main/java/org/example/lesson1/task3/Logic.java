package org.example.lesson1.task3;

import org.example.lesson1.task3.figures.Cube;
import org.example.lesson1.task3.figures.Cylinder;
import org.example.lesson1.task3.figures.Figure;
import org.example.lesson1.task3.figures.Sphere;

import java.util.ArrayList;

public class Logic {

    /**
     * We create a new ArrayList<Figure> and add the figures with the greatest volume to it, one by one, until the original
     * list is empty
     *
     * @param list The list of figures to be sorted.
     * @return ArrayList<Figure>
     */
    public static ArrayList<Figure> sortFigures(ArrayList<Figure> list) {
        //Creating new ArrayList<Figure>
        ArrayList<Figure> sortedList = new ArrayList<>();

        // Sorting the list of figures by volume.
        while (list.size() >= 1) {
            //First element will be the biggest, by default
            double maxVolume = list.get(0).getVolume();
            Figure maxFigure = list.get(0);
            int maxIndex = 0;

            for (int i = 0; i < list.size(); i++) {
                Figure f = list.get(i);
                //Checking if the figure has greater volume then our maxFigure
                if (f.getVolume() > maxVolume) {
                    maxVolume = f.getVolume();
                    maxFigure = f;
                    maxIndex = i;
                }

            }
            //Adding figure with the greatest volume to our sorted list
            sortedList.add(maxFigure);
            //Removing figure with the greatest volume from our unsorted list
            list.remove(maxIndex);
        }
        return sortedList;

    }

    /**
     * This function adds a random number of spheres to a list of figures.
     *
     * @param list The list of figures to add the spheres to.
     * @param numberOfSpheres The number of spheres to add to the list.
     */
    public static void addRandomSpheres(ArrayList<Figure> list, int numberOfSpheres) {
        for (int i = 0; i < numberOfSpheres; i++) {
            Sphere sphere = new Sphere((int) (Math.random() * 10) + 1);
            list.add(sphere);
        }
    }

    /**
     * This function adds a random number of cylinders to an ArrayList of Figures
     *
     * @param list the ArrayList of Figure objects
     * @param numberOfCylinders The number of cylinders to add to the list.
     */
    public static void addRandomCylinders(ArrayList<Figure> list, int numberOfCylinders) {
        for (int i = 0; i < numberOfCylinders; i++) {
            Cylinder cylinder = new Cylinder((int) (Math.random() * 10) + 1, (int) (Math.random() * 10)+1);
            list.add(cylinder);
        }
    }

    /**
     * This function adds a random number of cubes to a list of figures.
     *
     * @param list The list of figures to add the cubes to.
     * @param numberOfCubes The number of cubes to add to the list.
     */
    public static void addRandomCubes(ArrayList<Figure> list, int numberOfCubes) {
        for (int i = 0; i < numberOfCubes; i++) {
            Cube cube = new Cube((int) (Math.random() * 10) + 1);
            list.add(cube);
        }
    }
}
