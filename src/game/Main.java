package game;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	Background background = new Background();
	Events events = new Events(background);
	
	public static void main(String[] args) {
		launch(args);
    }
	
	@Override
    public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Pikmin");
        primaryStage.setWidth(1200);
        primaryStage.setHeight(625);
        primaryStage.setMinHeight(500);
        primaryStage.setMinWidth(1000);
        primaryStage.setScene(events);
       
        // primaryStage.sizeToScene();
        // primaryStage.setFullScreen(true);
         primaryStage.show();
	}
}
