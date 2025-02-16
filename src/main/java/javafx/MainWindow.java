package javafx;

import jarvis.Jarvis;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
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

    /**
     * Initializes the UI and displays a welcome message when the application starts.
     */
    @FXML
    public void initialize() {
        instance = this;
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        // Display welcome message
        String welcomeMessage = "Hello! I'm Jarvis.\nHow can I assist you today?";
        dialogContainer.getChildren().add(DialogBox.getJarvisDialog(welcomeMessage, jarvisImage));
    }

    /**
     * Sets the Jarvis instance.
     *
     * @param j The Jarvis instance.
     */
    public void setJarvis(Jarvis j) {
        this.jarvis = j;
    }

    /**
     * Handles user input and appends the user's input and Jarvis's response to the chat.
     * Clears the input field after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = jarvis.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getJarvisDialog(response, jarvisImage)
        );
        userInput.clear();
    }

    /**
     * Clears all chat messages in the dialog container.
     */
    public static void clearDialogContainer() {
        if (instance != null) {
            Platform.runLater(() -> instance.dialogContainer.getChildren().clear());
        }
    }

    /**
     * Gets the primary stage of the application.
     *
     * @return The application's main stage.
     */
    public static Stage getStage() {
        return instance.stage;
    }

    /**
     * Sets the primary stage of the application.
     *
     * @param stage The application's main stage.
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
