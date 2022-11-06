package org.example.lesson1.task3.figures;

public class Cylinder extends Figure {
    int height;
    int baseRadius;
    double volume;

    public Cylinder(int height, int baseRadius) {
        this.height = height;
        this.baseRadius = baseRadius;
        this.volume = Math.pow(baseRadius, 2) * Math.PI * height;
    }


    public double getVolume() {
        return volume;
    }

    @Override
    public String toString() {
        return "Cylinder h= " + height + ", r= " + baseRadius + " (volume= " + volume + ")\n";
    }
}
