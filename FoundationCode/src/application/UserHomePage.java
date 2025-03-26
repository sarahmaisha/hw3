package application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * This page displays a simple welcome message for the user.
 */
public class UserHomePage {

    private final User user;

    // Constructor to receive the current user
    public UserHomePage(User user) {
        this.user = user;
    }

    public void show(Stage primaryStage) {
        VBox layout = new VBox(10);
        layout.setStyle("-fx-alignment: center; -fx-padding: 20;");

        // Personalized welcome message
        Label userLabel = new Label("Hello, " + user.getUserName() + "!");
        userLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        Label space = new Label("");
        Button questionButton = new Button("Questions");
        Button messageButton = new Button("Messages");

        questionButton.setOnAction(a -> {
            new Question().show(primaryStage, user); // ✅ Pass user
        });

        messageButton.setOnAction(a -> {
            new Messages().show(primaryStage);
        });

        layout.getChildren().addAll(userLabel, space, questionButton, messageButton);
        Scene userScene = new Scene(layout, 800, 400);

        primaryStage.setScene(userScene);
        primaryStage.setTitle("User Page");
    }
}
