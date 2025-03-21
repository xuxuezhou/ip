package javafx;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

/**
 * Represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a TextArea containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private TextArea dialog;

    @FXML
    private ImageView displayPicture;

    /**
     * Constructs a DialogBox with specified text and image.
     *
     * @param text The text to display.
     * @param img  The image to display.
     */
    public DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/DialogBox.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        dialog.setWrapText(true);
        dialog.setEditable(false);
        dialog.setPrefWidth(350);

        displayPicture.setImage(img);
        displayPicture.setClip(new Circle(45, 45, 45));

        setAlignment(Pos.TOP_RIGHT);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Returns a DialogBox for Jarvis (flipped).
     *
     * @param text The text to display.
     * @param img  The image to display.
     * @return DialogBox instance for Jarvis.
     */
    public static DialogBox getJarvisDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.flip();
        return db;
    }

    /**
     * Returns a DialogBox for the user.
     *
     * @param text The text to display.
     * @param img  The image to display.
     * @return DialogBox instance for user.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }
}
