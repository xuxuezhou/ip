package javafx;

import java.io.IOException;

import jarvis.Jarvis;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The main entry point for the JavaFX application.
 */
public class Main extends Application {
    private Jarvis jarvis = new Jarvis("text-ui-test/input.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Jarvis");
            stage.setScene(scene);

            MainWindow mainWindow = fxmlLoader.getController();
            mainWindow.setStage(stage); // Pass the stage instance

            mainWindow.setJarvis(jarvis); // Inject the Jarvis instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
