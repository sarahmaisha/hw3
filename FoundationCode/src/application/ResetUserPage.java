package application;
import databasePart1.DatabaseHelper;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class ResetUserPage {
	
	 private DatabaseHelper databaseHelper;
	    // DatabaseHelper to handle database operations.
	    public void SetupAccountPage(DatabaseHelper databaseHelper) {
	        this.databaseHelper = databaseHelper;
	    }

	
	public void show(Stage primaryStage) {
    	VBox layout = new VBox();
	    layout.setStyle("-fx-alignment: center; -fx-padding: 20;");
	    
	    // Username
	    Label UNL = new Label("Enter Username");
        UNL.setStyle("-fx-font-size: 14px; -fx-font-style: italic;");
	    Label space2 = new Label("");
	    TextField un = new TextField();
	    un.setPromptText("Enter username");
	    un.setMaxWidth(250);
	    Label space3 = new Label("");
	    
	    // Reset Text
	    Label rstIntro = new Label("Enter given Reset Password");
        rstIntro.setStyle("-fx-font-size: 14px; -fx-font-style: italic;");
        Label space = new Label("");
	    TextField rstText = new TextField();
	    rstText.setPromptText("Enter reset password");
	    rstText.setMaxWidth(250);
	    
	    // Reset Button
        Label rstSpace = new Label("");
        rstSpace.setStyle("-fx-font-size: 14px; -fx-font-style: italic;");
	    Button rstUserButton = new Button("Reset");	
	    
	    User user=new User(un.getText(), rstText.getText(), "user");
	    
	    rstUserButton.setOnAction(a -> {
	    	// Add code to database for reset
	    	new WelcomeLoginPage(databaseHelper).show(primaryStage,user);
	    });

	    layout.getChildren().addAll(UNL, space2, un, space3, rstIntro, space, rstText, rstSpace, rstUserButton);
	    Scene rstScene = new Scene(layout, 800, 400);

	    // Set the scene to primary stage
	    primaryStage.setScene(rstScene);
	    primaryStage.setTitle("Reset User Password Page");
    }
}