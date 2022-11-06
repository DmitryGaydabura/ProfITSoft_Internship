package org.example.lesson1.task3.figures;

/**
 * Cube is a subclass of Figure.
 */
public class Cube extends Figure {
    int length;
    double volume;

    public Cube(int length) {
        this.length = length;
        this.volume = Math.pow(length, 3);
    }

    public double getVolume() {
        return volume;
    }

    @Override
    public String toString() {
        return "Cube l= " + length + " (volume= " + volume + ")\n";
    }
}
