import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		GraphPane myroot = new GraphPane(primaryStage);
		//setting size of the scene
		Scene scene = new Scene(myroot,900,800);
		//setting the title of the pane
	    primaryStage.setTitle("Socio-Economic Problem Solving with Graphs");
		//setting the scene
	    primaryStage.setScene(scene);
	    //show of the scene
	    primaryStage.show();
	}

}
