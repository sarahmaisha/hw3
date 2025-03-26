package application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class ResetPassword {
    private static String resetPassword;
    
    public static void setResetPassword(String password) {
        resetPassword = password;
    }
    
    public static String getResetPassword() {
        return resetPassword;
    }
    
	public void show(Stage primaryStage) {
    	VBox layout = new VBox();
	    layout.setStyle("-fx-alignment: center; -fx-padding: 20;");
	    
	    Label ResetLabel = new Label("Enter password for User to enter to reset");
	    Label space = new Label("");
	    
	    TextField rstText = new TextField();
	    rstText.setPromptText("");
	    rstText.setMaxWidth(250);
	    
        Label rstSpace = new Label("");
        rstSpace.setStyle("-fx-font-size: 14px; -fx-font-style: italic;");
	    Button rstButton = new Button("Reset");	
	    
	    Label space2 = new Label("");
	    
	    Label success = new Label();
        success.setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
	    
	    rstButton.setOnAction(a -> {
	    	// Add code to database for reset
	    	setResetPassword(rstText.getText());
	    	success.setText("Reset Password Now Set");
	    	System.out.println(rstText.getText());
	    });

	    layout.getChildren().addAll(ResetLabel, space, rstText, rstSpace, rstButton, space2, success);
	    Scene adminScene = new Scene(layout, 800, 400);

	    // Set the scene to primary stage
	    primaryStage.setScene(adminScene);
	    primaryStage.setTitle("Reset Password Page");
    }
}