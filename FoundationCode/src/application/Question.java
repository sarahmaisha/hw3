
package application;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Question screen where users can ask or search for questions.
 */
public class Question {
    public void show(Stage primaryStage, User user) {
        VBox layout = new VBox();
        
        // Ask a question UI
        Label askLabel = new Label("Ask a Question");
        TextField questionField = new TextField();
        Button askButton = new Button("Ask");
        
        // Search for question UI
        Label space = new Label("");
        Label searchForQuestions = new Label("Search for a question");
        Button searchButton = new Button("Search");
        
        // Status message
        Label success = new Label();
        success.setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
        
        // Ask button logic
        askButton.setOnAction(a -> {
            String questionText = questionField.getText().trim();
            if (!questionText.isEmpty()) {
                QuestionList.addQuestion(questionText, user);
                questionField.clear();
                success.setText("Question asked");
                success.setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
            } else {
                success.setText("No question asked");
                success.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
            }
        });
        
        // Navigate to question list
        searchButton.setOnAction(a -> {
            new QuestionList().show(primaryStage);
        });

        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");
        layout.getChildren().addAll(
            askLabel, questionField, askButton, success,
            space, searchForQuestions, searchButton
        );

        Scene userScene = new Scene(layout, 800, 400);
        primaryStage.setScene(userScene);
        primaryStage.setTitle("Questions");
    }

	public Object getAuthor() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
