package org.example.lesson1_2.task3.figures;

/**
 * Sphere is a subclass of Figure.
 */
public class Sphere extends Figure {

    int radius;
    double volume;

    public Sphere(int radius) {
        this.radius = radius;
        this.volume = (4 * Math.PI * Math.pow(radius, 3)) / 3;
    }

    public double getVolume() {
        return volume;
    }

    @Override
    public String toString() {
        return "Sphere r= " + radius + " (volume= " + volume + ")\n";
    }

}
