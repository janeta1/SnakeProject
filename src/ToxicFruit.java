/**
 * The class represents the bad fruit that the snake can eat. When the fruit is eaten
 * the score increases
 */

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class ToxicFruit implements Fruit{
       private double centerX, centerY;
    private Circle fruit;

    /**
     * Initiating the fruit
     * @param centerX - its center on the x axis
     * @param centerY - its center on the y axis
     * @param radius - radius
     */
    public ToxicFruit(double centerX, double centerY, double radius){
        this.centerX = centerX;
        this.centerY = centerY;
        fruit = new Circle(centerX, centerY, radius, Color.GRAY);

    }

    //Getters and setters
    public double getCenterX() {
        return centerX;
    }

    public double getCenterY() {
        return centerY;
    }

    public Circle getFruit() {
        return fruit;
    }
}