package application;


import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.*;







public class MessageList {
	private static final List<Messages> messageAnswers = new ArrayList<>();
	
	
	 public void show(Stage primaryStage) {
	        VBox layout = new VBox(10);
	
	        ListView<String> replyListView = new ListView<>();
	        
	        Label replyLabel = new Label ("Here are your messages!");
	        replyLabel.setStyle("-fx-text-fill: black; -fx-font-weight: bold;");
	        
	        getMessages(replyListView);
	        
	        
	        
	        layout.getChildren().addAll(replyLabel, replyListView);
	        primaryStage.setScene(new Scene(layout, 800, 500));
	        primaryStage.setTitle("Message List");
	 }
	
	
	public static void addMessage(String message) {
	        messageAnswers.add(new Messages(message));
	    }

	  
	private void getMessages ( ListView<String> messageListView)
	{
		
		List<String> msg = new ArrayList<>();
		
		for (Messages message : messageAnswers) { // iterate through questions list
            if (message.getReply() != null) {
                msg.add(message.getReply()); // Add similar questions to list to return
            }
        }
		
		messageListView.getItems().setAll(msg);
		
	}
	  
	  
	  private static class Messages {
	        private final String message;
	   
	      
	        // Set question
	        public Messages(String replies) {
	            this.message = replies;
	        }
	        
	        // returns question
	        public String getReply() {
	            return message;
	        }

	        
	     
	  }
}