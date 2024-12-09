import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class View extends BorderPane {
    //Goodies :)
    private ArrayList<Rectangle> tiles = new ArrayList<>();
    private Stage stage;
    public GridPane tileGrid = new GridPane();
    private HBox bottomPane = new HBox();
    public HBox upperPane = new HBox();
    private double sceneWidth = 600, sceneHeight = 600;
    private int time = 0, score = 0;
    private int rows = 15, columns = 15; // Adjust grid size for the Snake game
    private SnakePart snake;
    private ArrayList<SnakePart> sBody = new ArrayList<>();;
    Button quitButton;
    private ArrayList<Fruit> listOfFruit = new ArrayList<>();//for multiple fruits
    //private ToxicFruit xFruit = new ToxicFruit(0 , 0, 0);
    //:)

    /**
     * Setting up the view of the game
     * @param stage
     */
    public View(Stage stage) {
        this.stage = stage;
        quitButton = new Button("Quit");
        setGrid();
        displaySetup();
    }

    /**
     * Displaying the setup of the game
     */
    public void displaySetup() {
        setCenter(tileGrid);
        setBottom(bottomPane);
        setTop(upperPane);

        quitButton.setOnAction(e -> {
            System.exit(0);
        });
        bottomPane.autosize();
        bottomPane.getChildren().add(quitButton);
        bottomPane.setAlignment(Pos.CENTER);

        //Create the snake head at row 7 and column 7;
        snake = new SnakePart(252, 252, 36, Color.BLACK, true);

        //Add the snake head to the ArrayList that will hold all of the snakes
        sBody.add(snake);
        tileGrid.getChildren().add(snake.getRectangle());

        //Add the fruit to appear in front of the snake head.
        Fruit fruit = new TastyFruit(432, 252, tiles.get(0).getWidth()/2);
        listOfFruit.add(fruit);

        //call the method to add the fruit to the grid;
        addFruit();

        tileGrid.setAlignment(Pos.CENTER);
        for (int i = 1; i < rows * columns * 36; i++) {
            SnakePart snake2 = new SnakePart(252, 252, 36, Color.BLACK, false);
            tileGrid.getChildren().add(snake2.getRectangle());
            snake2.getRectangle().setVisible(false);
            sBody.add(snake2);
        }

        //Create a Label to display the current score and add it to the display.
        Label newLabel = new Label("Score: " + score + " 🍎");
        upperPane.getChildren().add(newLabel);
        upperPane.setAlignment(Pos.CENTER);

        //               |    /
        //Set the stage [|:D-----<
        //               |    \
        Scene scene = new Scene(this, sceneWidth, sceneHeight);
        stage.setScene(scene);
        stage.setResizable(false);
    }

    /**
     * Setting the tile grid with a checkerboard pattern
     */
    public void setGrid() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Rectangle tile = new Rectangle(36, 36);
                
                // Checkerboard pattern: alternate between dark and light green
                if ((i + j) % 2 == 0) {
                    tile.setFill(Color.GREEN); // Dark green tile
                } else {
                    tile.setFill(Color.LIGHTGREEN); // Light green tile
                }
                
                tiles.add(tile);
                tileGrid.add(tile, j, i); // Note: GridPane uses (column, row)
            }
        }
    }

    /**
     * Returning a specific tile
     * @param index - index of the tile
     * @return - the tile at the given index
     */
    public Rectangle getTile(int index) {
        return tiles.get(index);
    }

    /**
     * Showing the setup
     */
    public void show() {
        stage.show();
        // this will ensure that the methods of the listener will actually work
        this.requestFocus();
    }

    /**
     * Showing the elapsed time
     * @param timeElapsed - the time elapsed
     */
    public void setLabel(int score) {
        Label myLabel = (Label)upperPane.getChildren().get(0);
        myLabel.setText("Score: " + score + " 🍎");
    }
    /**
     * Remove the Fruit visually and update the score acordingly. 
     */
    public void removeFruit(Fruit fruit){
    
        // Remove the fruit from the grid and the list
        tileGrid.getChildren().remove(fruit.getFruit()); // Removes the graphical fruit
        listOfFruit.remove(fruit); // Removes the fruit object from the list
    }
    
    public void addFruit(){
        // Add each fruit to the grid
        for (Fruit fruit : listOfFruit) {
            if (!tileGrid.getChildren().contains(fruit.getFruit())) {
                tileGrid.getChildren().add(fruit.getFruit()); // Add fruit to the grid
            }
        }
    }

    public void updatePos(double centerX, double centerY) {
        for (int i = 0; i < sBody.size(); i++) {
                sBody.get(i).getRectangle().setTranslateX(sBody.get(i).getCenterX());
                sBody.get(i).getRectangle().setTranslateY(sBody.get(i).getCenterY());
            
        }
        for (Fruit fruit : listOfFruit) {
            fruit.getFruit().setTranslateX(fruit.getCenterX());
            fruit.getFruit().setTranslateY(fruit.getCenterY());
        }
  
    }

    public ArrayList<Rectangle> getTiles() {
        return tiles;
    }
    public ArrayList<SnakePart> getsBody() {
        return this.sBody;
    }

    public double getTilesColumns() {
        return columns;
    }

    public double getTilesRows() {
        return columns;
    }

    public double getSceneWidth() {
        return sceneWidth;
    }
    public double getSceneHeight() {
        return sceneHeight;
    }
    public SnakePart getSnake() {
        return snake;
    }
    public HBox getBottomPane() {
        return bottomPane;
    }
    public ArrayList<Fruit> getListOfFruit() {
        return listOfFruit;
    }

    public int getScore() {
        return score;
    }
}