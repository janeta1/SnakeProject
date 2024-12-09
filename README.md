### Snake Game
This is a Snake Game is a classic grid based game where players can control the snake, control the snake to eat the fruit, and the snake can grow longer while avoiding obstacles, toxic fruits and collision with itself when it hits the grid.The game ends when the snake hits the grid or itself and the score drops below zero.

## Features
- Fruit Variants: 
    Tasty Fruits: Increases the score while the      snake grows.
    Toxic Fruits: Decreases the score while the snake gets shorten.
- Collision Detection: Ends the game if the snake    collides with itsekf or the grid edges
- Key Controls: Arrow keys navigate with directing the snake to the fruits 
- Game Over Display: A game over message is shown at the end of the snake.
- Fruit generation: Randomly spawns new fruits inside the grid.
- Gameplay: The snake moves continuously and grows longer as it eats a fruit.

## Technical 
- Programming Language: Java
- Framework: JavaFX 
- Classes: 
    -- Controller: Handles the user input, game setup and animation.
    -- Model: Manages the game logic, snake behavior, and score.
    -- View: Handles the snake grid, snake, fruit, and user interface.
    -- Fruit, TastyFruit, ToxicFruit: Different types of fruits to appear randomly while the game starts
    -- SnakePart: The snake has indiviadual part of the snake.

## Contributions:
1. Janeta Grigoras:
    - Constructed the grid tiles.
    - Implemented the endGame() and startGame() logic.
    - Made sure the snake stays inside the borders.
    - Implemented the movement of the snake logic (with Mark).
    - Implemented the snake body and made it appear on the pane.
    - Implemented the grow and shrink snake logic.
    - Made sure there is alwasy enough good fruits on the screen.
    - Implemented the snake collision with itself logic (with Mark).
2. Mark Turner:
    - Implemented the movement of the snake logic (with Janeta).
    - Implemented fruit collision logic (with Sree).
    - Implemented the fruit spawning logic (with Sree). 
    - Implemented the snake collision with itself logic (with Janeta).
    - Implemented the logic of changing the direction of the snake (with Adan).
    - Implemented the key controls logic.
3. Sreelakshmi Atholi:
    - Worked on the fruit to be implemented in the game.
    - Implemented the spawning of fruits logic (with Mark).
    - Implemented fruit collision logic (with Mark).
    - Wrote the ReadMe file.
    - Updated the User guide. 
4. Adan Morones:
    - Created the fruit classes/interface.
    - Made sure the snake cannot move in the opposite direction of its current movement.
