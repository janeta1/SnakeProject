import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;

public class Controller {
    Model model;
    View view;
    Timeline timeline;

    /*
     * Setting up the game
     */
    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        setupGameControls();
    }

    
    /**
     * Calling all the methods that help setting up the game
     */
    public void setupGameControls() {
        animate();
        setupCounter();
        setUpKeyControls();
    }

    /**
     * Setting up the key controls
     */
    public void setUpKeyControls(){
        view.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.LEFT){
                model.changeDirection(1);
            }
            if(e.getCode() == KeyCode.UP){
                model.changeDirection(2);
            }
            if(e.getCode() == KeyCode.RIGHT){
                model.changeDirection(3);
            }
            if(e.getCode() == KeyCode.DOWN){
                model.changeDirection(4);
            }
        });
    }

    /**
     * Making the snake move
     */
    public void move() {
        model.eatFruit();
        model.moveSnake();
        view.updatePos(model.getCenterX(), model.getCenterY());
        if(model.isGameOver()) {
            endGame();
        }
        if(model.isGameWon()) {
            winGame();
        }
    }

   
    /**
     * Animating the game/Starting the timeline of the game
     */
    public void animate() {
        timeline = new Timeline(new KeyFrame(Duration.millis(10), e -> move())); // Adjust speed
        timeline.setCycleCount(Timeline.INDEFINITE);
    }
    

    /**
     * Showing the score of the game
     */
    public void setupCounter() {
        model.getScoreProperty().addListener(ov -> {
            view.setLabel(model.getScoreProperty().getValue());
        });
    }

    /**
     * Starting the game
     */
    public void startGame() {
        timeline.play();
    }

    /**
     * Ending the game
     */
    public void endGame() {
        timeline.stop();
        Label endLabel = new Label("GAME OVER. Your score: " + model.score_property.getValue() + " 🍎");
        endLabel.setStyle("-fx-font-size: 15; -fx-text-fill: red; -fx-font-weight: bold;");
        endLabel.setLayoutX(view.getWidth() / 4 - 80);
        endLabel.setLayoutY(view.getHeight() / 2 - 50);
        view.upperPane.getChildren().remove(0);
        view.upperPane.getChildren().add(endLabel);
    }

    /**
     * Winning the game
     */
    public void winGame() {
        timeline.stop();
        Label endLabel = new Label("YOU WON! Your score: " + model.score_property.getValue() + " 🍎");
        endLabel.setStyle("-fx-font-size: 15; -fx-text-fill: red; -fx-font-weight: bold;");
        endLabel.setLayoutX(view.getWidth() / 4 - 80);
        endLabel.setLayoutY(view.getHeight() / 2 - 50);
        view.upperPane.getChildren().remove(0);
        view.upperPane.getChildren().add(endLabel);
    }
}