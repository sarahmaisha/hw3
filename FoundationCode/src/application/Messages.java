package application;




import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Messages {
	public void show(Stage primaryStage) {
		VBox layout = new VBox(10);
		 layout.setStyle("-fx-padding: 20; -fx-alignment: center;");
		
		
		Label replyLabel = new Label("See replies");
		replyLabel.setStyle("-fx-text-fill: black; -fx-font-weight: bold;-fx-font-size: 20px;");
		Button checkButton = new Button("Open here!");
		checkButton.setStyle("-fx-allignment: center;");
		checkButton.setOnAction(a -> {
		 
			new MessageList().show(primaryStage);
			
		});
		

		
		
		layout.getChildren().addAll(replyLabel, checkButton);
		Scene userScene = new Scene(layout, 800, 500);
		primaryStage.setScene(userScene);
        primaryStage.setTitle("Messages");
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}