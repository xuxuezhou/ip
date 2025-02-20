// MainWindow.java
package javafx;

import jarvis.Jarvis;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    private static MainWindow instance;
    private Stage stage;

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Jarvis jarvis;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/panda.jpg"));
    private Image jarvisImage = new Image(this.getClass().getResourceAsStream("/images/beauty.jpg"));

    @FXML
    public void initialize() {
        instance = this;
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.prefWidthProperty().bind(scrollPane.widthProperty().subtract(20));
        dialogContainer.prefHeightProperty().bind(scrollPane.heightProperty().subtract(20));

//        String welcomeMessage = "Hello! I'm Jarvis.\nHow can I assist you today?";
        String welcomeMessage = "Hello! I'm Shiyu.\nI'm a physicist.\nLet's discuss astronomy!";
        dialogContainer.getChildren().add(DialogBox.getJarvisDialog(welcomeMessage, jarvisImage));
    }

    public void setJarvis(Jarvis j) {
        this.jarvis = j;
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText().trim();
        if (input.isEmpty()) return; // Ignore empty input

        String response;
        try {
            response = jarvis.getResponse(input);
        } catch (Exception e) {
            response = "Error: " + e.getMessage();
        }

        if (response.startsWith("Error:")) {
            Label errorLabel = new Label(response);
            errorLabel.setTextFill(Color.RED);
            errorLabel.setFont(new Font("Arial", 16));
            errorLabel.setStyle("-fx-font-weight: bold; -fx-background-color: lightyellow; -fx-padding: 5;");
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    errorLabel
            );
        } else {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getJarvisDialog(response, jarvisImage)
            );
        }
        userInput.clear();
    }

    public static void clearDialogContainer() {
        if (instance != null) {
            Platform.runLater(() -> instance.dialogContainer.getChildren().clear());
        }
    }

    public static Stage getStage() {
        return instance.stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}