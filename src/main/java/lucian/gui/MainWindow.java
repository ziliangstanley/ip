package lucian.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import lucian.Lucian;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Lucian lucian;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/senna_icon.png"));
    private Image lucianImage = new Image(this.getClass().getResourceAsStream("/images/lucian_icon.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Duke instance */
    public void setLucian(Lucian lucian) {
        this.lucian = lucian;
        String greeting = this.lucian.getGreeting();
        dialogContainer.getChildren().addAll(DialogBox.getLucianDialog(greeting, lucianImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = lucian.handleCommand(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getLucianDialog(response, lucianImage)
        );
        userInput.clear();

        if (response.equals("Bye. See you around.")) {
            Platform.exit();
            System.exit(0);
        }
    }
}

