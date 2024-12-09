/**
 * The SnakePart class represents an individual segment of the snake in the game
 */
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class SnakePart {
    private double centerX;
    private double centerY;
    private Rectangle head;
    boolean isHead;
    int dirX, dirY;

    public SnakePart(double x, double y, double size, Color color, boolean isHead) {
        this.centerX = x;
        this.centerY = y;
        this.head = new Rectangle(size, size, color);
        this.head.setTranslateX(x);
        this.head.setTranslateY(y);
        this.isHead = isHead;
        dirX = 1;
        dirY = 0;
    }

    public double getCenterX() {
        return centerX;
    }

    public double getCenterY() {
        return centerY;
    }

    public void setCenterX(double centerX) {
        this.centerX = centerX;
    }

    public void setCenterY(double centerY) {
        this.centerY = centerY;
    }

    public Rectangle getRectangle() {
        return head;
    }

    public void setDirX(int dirX) {
        this.dirX = dirX;
    }

    public void setDirY(int dirY) {
        this.dirY = dirY;
    }

    public int getDirX() {
        return dirX;
    }

    public int getDirY() {
        return dirY;
    }
}