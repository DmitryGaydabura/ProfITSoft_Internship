package org.example.lesson1_2.task3;

import org.example.lesson1_2.task3.figures.Cube;
import org.example.lesson1_2.task3.figures.Cylinder;
import org.example.lesson1_2.task3.figures.Figure;
import org.example.lesson1_2.task3.figures.Sphere;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task3LogicTest {


    @Test
    void testAddRandomSpheres() {
        ArrayList<Figure> list = new ArrayList<>();

        Logic.addRandomSpheres(list, 3);

        Assertions.assertEquals(list.size(), 3);
    }

    @Test
    void addRandomCylinders() {

        ArrayList<Figure> list = new ArrayList<>();

        Logic.addRandomCylinders(list, 3);

        Assertions.assertEquals(list.size(), 3);
    }

    @Test
    void addRandomCubes() {

        ArrayList<Figure> list = new ArrayList<>();

        Logic.addRandomCubes(list, 3);

        Assertions.assertEquals(list.size(), 3);
    }

    @Test
    void testAddRandomSpheresNullCase() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                Logic.addRandomSpheres(null, 3), "List should not be null");

        assertEquals("List should not be null",
                exception.getMessage());
    }

    @Test
    void addRandomCylindersNullCase() {

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                Logic.addRandomCylinders(null, 3), "List should not be null");

        assertEquals("List should not be null",
                exception.getMessage());
    }

    @Test
    void addRandomCubesNullCase() {

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                Logic.addRandomCubes(null, 3), "List should not be null");

        assertEquals("List should not be null",
                exception.getMessage());
    }

    @Test
    void sortFigures() {

        Cube cube = new Cube(1);
        Cylinder cylinder = new Cylinder(1,2);
        Sphere sphere = new Sphere(4);

        ArrayList<Figure> list = new ArrayList<>();
        list.add(cube);
        list.add(cylinder);
        list.add(sphere);

        ArrayList<Figure> expected = new ArrayList<>();
        expected.add(sphere);
        expected.add(cylinder);
        expected.add(cube);

        ArrayList<Figure> actual = Logic.sortFigures(list);

        Assertions.assertEquals(expected,actual);
    }

    @Test
    void sortFiguresNullCase(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                Logic.sortFigures(null), "List should not be null");

        assertEquals("List should not be null",
                exception.getMessage());
    }

    @Test
    void sortFiguresEmptyListCase(){

        ArrayList<Figure> list = new ArrayList<>();

        ArrayList<Figure> expected = new ArrayList<>();

        ArrayList<Figure> actual = Logic.sortFigures(list);

        Assertions.assertEquals(expected,actual);
    }
}
