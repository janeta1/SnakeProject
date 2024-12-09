import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class SnakeBody {
    private double centerX,centerY;
    private Rectangle snake;

    public SnakeBody(double width, double height, Color color, int centerX, int centerY){
        this.snake = new Rectangle(width, height, color);
        this.centerX = centerX;
        this.centerY = centerY;
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
    public Rectangle getSnake() {
        return snake;
    }
}
