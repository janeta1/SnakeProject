/**
 * This code defines the Model class for a Snake game, 
 * managing the snake's movement, growth, shrinking, and 
 * interactions with the grid and fruits. It includes logic 
 * for detecting wall collisions, spawning and eating fruits 
 * (both beneficial and harmful), and updating the score and game \state based on these events.
 */

import java.util.ArrayList;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.shape.Rectangle;

public class Model { 
    // Functional variables
    View view;
    Controller controller;

    // Variables for the snake and other related items
    int dirX = 1, dirY = 0; 
    double centerX = 0, centerY = 0;
    SnakePart snake;
    int speed = 1;
    ArrayList<Rectangle> tiles;
    ArrayList<SnakePart> sBody;
    IntegerProperty score_property = new SimpleIntegerProperty(0);
    int currentDirection;
    int lastStoredDirection;
    ArrayList<Fruit> listOfFruits = new ArrayList<>();

    /**
     * Constructor for the class
     * Instantiates the snake directions to right
     * @param view
     */
    public Model(View view){
        this.view = view;
        this.snake = view.getSnake();
        this.tiles = view.getTiles();
        this.sBody = view.getsBody();
        this.listOfFruits = view.getListOfFruit();
        this.lastStoredDirection = 3;
    }

    /**
     * Updates the direction of the snake
     * @param lsd - the last direction
     * @param part - the part of the snake for which to change the direction
     */
    public void updateDirection(int lsd, SnakePart part) {
        // This part makes sure that the snake changes its direction only when it aligns with a tile
        if ((part.getCenterY() % tiles.get(0).getHeight() == 0 && (lsd == 1 || lsd == 3)) ||
            (part.getCenterX() % tiles.get(0).getWidth() == 0 && (lsd == 2 || lsd == 4))) {
            switch (lsd) {
                // Switching the direction to left
                case 1:
                    setDirX(-1);
                    setDirY(0);
                    currentDirection = 1;
                    break;
                // Switching the direction to up
                case 2: 
                    setDirX(0);
                    setDirY(-1);
                    currentDirection = 2;
                    break;
                // Switching the direction to right
                case 3:
                    setDirX(1);
                    setDirY(0);
                    currentDirection = 3;
                    break;
                // Switching the direction to down
                case 4:
                    setDirX(0);
                    setDirY(1);
                    currentDirection = 4;
                    break;
            }
        }
    }
    
    /**
     * Method to update the coordinates of the snake
     */
    public void moveSnake() {
        // First we update the coordinates of the head
        SnakePart head = sBody.get(0);
        head.setCenterX(head.getCenterX() + speed * dirX);
        head.setCenterY(head.getCenterY() + speed * dirY);
        
        // Based on the position of the part in front, we update the
        // position of each snake part
        for (int i = sBody.size() - 1; i > 0; i--) {
            sBody.get(i).setCenterX(sBody.get(i - 1).getCenterX());
            sBody.get(i).setCenterY(sBody.get(i - 1).getCenterY());
        }

        // Updating the direction
        updateDirection(lastStoredDirection, head);
    }

    /**
     * Adds a new body part to the snake
     * @param size - variable to check where to add the new part
     */
    public void growSnake(int size) {
        sBody.get(size).getRectangle().setVisible(true);
    }

    /**
     * Removes a body part
     * @param size - variable to check where to remove the body part
     */
    public void shrinkSnake(int size) {
        sBody.get(size).getRectangle().setVisible(false);
    }
    
    /**
     * Checks if the game is over
     * @return - true if the user has lost, false otherwise
     */
    public boolean isGameOver() {
        return (checkWallCollision()) || (score_property.getValue() < 0) || checkBodyCollision();
    }

    /**
     * Checks if the snake collided with a wall
     * @return - true if the snake collided with the wall, false otherwise
     */
    public boolean checkWallCollision() {
        return sBody.get(0).getCenterX() > tiles.get(0).getWidth()*view.getTilesColumns() - 36
        || sBody.get(0).getCenterX() < 0 || sBody.get(0).getCenterY() < 0 || 
        sBody.get(0).getCenterY() > tiles.get(0).getWidth()*view.getTilesRows() - 36;
    }

    /**
     * Checks if the user has won the game
     * @return - true if the user won, false otherwise
     */
    public boolean isGameWon() {
        return (score_property.getValue() == 10);
    }

    /**
     * Changes the direction of the snake
     * @param dir
     */
    public void changeDirection(int dir) {
        // Preventing the snake from reversing direction
        if ((dir == 1 && currentDirection != 3) || 
            (dir == 2 && currentDirection != 4) || 
            (dir == 3 && currentDirection != 1) || 
            (dir == 4 && currentDirection != 2)) {
            this.lastStoredDirection = dir;
        }
    }

    /**
     * Calculates the coordinates at the front of the snake's head based on its current direction.
     * @return an array containing the x and y coordinates of the front position.
     */
    public int[] getFrontCoordinate() {
        // Get the head of the snake
        SnakePart head = sBody.get(0);
        double frontX = -500;
        double frontY = -500;

        double centerSquare = view.getTile(0).getWidth();
        
        if(head.getCenterX() % view.getTile(0).getWidth() == 0 &&head.getCenterY() % view.getTile(0).getWidth()== 0){
            // Calculate the front position based on the current direction and speed
            frontX = head.getCenterX() /*+ dirX*/ + centerSquare;
            frontY = head.getCenterY() /*+ dirY */+ centerSquare;
        }



        // Return the front coordinates as an integer array
        return new int[] { (int) frontX, (int) frontY };
    }

    public boolean checkBodyCollision() {
        int[] front = getFrontCoordinate();
        for (int i = 1; i < sBody.size(); i++) {
            SnakePart part = sBody.get(i);
            if(part.getRectangle().isVisible()) {
                if(front[0] % view.getTile(0).getWidth() == 0 &&front[1] % view.getTile(0).getWidth()== 0)
                    if(part.getRectangle().getBoundsInParent().intersects(front[0], front[1], 0, 0)) {
                        return true;
                    }
            }
        }
        return false;
    }

    
    /**
     * Method to calculate where to put the spawned fruits
     * @return - an array containing the location of the fruit
     */
    public int[] randomXY(){
        int newFruitX, newFruitY;
        boolean positionValid;

        do{
            positionValid=true; 
            
            newFruitX=(int)(Math.floor(Math.random()*view.getTilesColumns())*tiles.get(0).getWidth());
            newFruitY=(int)(Math.floor(Math.random()*view.getTilesRows())*tiles.get(0).getWidth());

            if(newFruitX % view.getTiles().get(0).getWidth() == 0 && newFruitY % view.getTiles().get(0).getHeight() == 0){
                for(SnakePart part:sBody){
                    // Checking to make sure new fruits are not added where the snake is
                    if(part.getCenterX()==newFruitX && part.getCenterY() == newFruitY){
                        positionValid=false;
                        break;
                    }
                }
            }
        } while(!positionValid);

        int[] values = new int[2];
        values[0] = newFruitX;
        values[1] = newFruitY;

        return values;
    }

    /**
     * Method for spawning new fruits
     */
    public void spawnNewFruit() {

        // Deciding whether to add 0 or 1 more fruits
        int gamble = (int) (Math.random() * 2) + 1;
        goodAndEvil(gamble);

        // Adding the fruit to the grid
        view.addFruit();
    }

    public void addGoodFruit() {
        //Adding a guaranteed good fruit so the game can continue 
        int[] guaranteedCoordinates = randomXY();
        int newFruitX = guaranteedCoordinates[0];
        int newFruitY = guaranteedCoordinates[1];
        listOfFruits.add(new TastyFruit(newFruitX, newFruitY, tiles.get(0).getWidth()/2));

        // Adding the fruit to the grid
        view.addFruit();
    }

    /**
     * Adding a bad fruit or a good fruit
     * @param gamble - variable that decides whether a good fruit is added, or a bad one
     */
    public void goodAndEvil(int gamble) {
        int[] values = randomXY();
        int newX = values[0];
        int newY = values[1];

        switch (gamble) {
            // Adding the good fruit
            case 1:
                Fruit tastyFruit = new TastyFruit(newX, newY, tiles.get(0).getWidth() / 2);
                listOfFruits.add(tastyFruit);
                break;
            // Adding the bad fruit
            case 2:
                Fruit toxicFruit = new ToxicFruit(newX, newY, tiles.get(0).getWidth() / 2);
                listOfFruits.add(toxicFruit);
                break;
        }
    }

    public void eatFruit() {
        // The list will contain the fruits that need to be removed
        ArrayList<Fruit> fruitsToRemove = new ArrayList<>();

        // Variable to check if the number of good fruits on the board are enough
        int tastyFruitsOnBoard = 0;

        for (Fruit fruit : listOfFruits) {
            // Checking if the snake has eaten the fruit
            if (snake.getCenterX() == fruit.getCenterX() && snake.getCenterY() == fruit.getCenterY()) {
                // Adding fruit to the removal list
                fruitsToRemove.add(fruit);      
            }
            // Increasing the number of good fruits
            if(fruit instanceof TastyFruit){
                if(!fruitsToRemove.contains(fruit)){
                    tastyFruitsOnBoard++;
                }
            }
        }

        // Removing the fruits from the list after the iteration
        for (Fruit fruit : fruitsToRemove) {
            view.removeFruit(fruit);

            // If its a good fruit we grow the snake and increase the score
            if(fruit instanceof TastyFruit) {
                score_property.setValue(score_property.getValue() + 1);
                int score = score_property.getValue();
                growSnake((int)(score * view.getTiles().get(0).getWidth()));
            } else {
                // If its a bad fruit we decrease the size of the snake and lower the score
                if(!isGameOver()) {
                    shrinkSnake((int) (score_property.getValue() * view.getTiles().get(0).getWidth()));
                }
                score_property.setValue(score_property.getValue() - 1);
                
            }
        }

        // Adding new fruits
        if(listOfFruits.size() < 1) {
            spawnNewFruit();
        } 
        if(tastyFruitsOnBoard == 0) {
            addGoodFruit();
        }
    }

    // Getters and setter
    public int getDirX() {
        return dirX;
    }
    public int getDirY() {
        return dirY;
    }
    public void setDirX(int dirX) {
        this.dirX = dirX;
    }
    public void setDirY(int dirY) {
        this.dirY = dirY;
    }

    public double getCenterX() {
        return snake.getCenterX();
    }

    public double getCenterY() {
        return snake.getCenterY();
    }

    public int getLastStoredDirection() {
        return lastStoredDirection;
    }

    public IntegerProperty getScoreProperty() {
        return score_property;
    }

}