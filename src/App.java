import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application{
    @Override
    public void start(Stage primaryStage) {
        View myGameView = new View(primaryStage);
        myGameView.show();

        Model myGameModel = new Model(myGameView);
        Controller myGameController = new Controller(myGameModel, myGameView);
        myGameController.startGame();
    }
    public static void main(String[] args) throws Exception {
        Application.launch(args);
    }
}