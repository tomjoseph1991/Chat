package secureChat;

import javafx.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class SecureChat extends Application {

	public static void main(String[] args) 
	{
		launch(args); 
	}

	
	public void start(Stage stage) throws Exception 
	{
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root,800,600);
		
		Stage loginStage = new Stage();
		GridPane loginPane = new GridPane();
		Scene loginScene = new Scene(loginPane,400,150);
		
		TextField loginField = new TextField();
		Button loginButton = new Button("Login");
		Label nicknameLabel = new Label("Nickname:");
		Label errorLabel = new Label ("");
		
		loginButton.setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent arg0) {
				if(loginField.getText().length() >=3)
				{
					User thisUser = new User();
					thisUser.setScreenname(loginField.getText());
					
					ChatBox chatbox = new ChatBox();
					chatbox.login(thisUser);
					
					StackPane userPane = new StackPane();
					Label userLabel = new Label(loginField.getText());
					userPane.getChildren().add(userLabel);
					root.setRight(userPane);
					
					loginStage.close();
					stage.show();
				}
				else {
					errorLabel.setText("Nickname is too long");
				}
				
			}
			
			
		}
		);
		
		GridPane.setConstraints(nicknameLabel,0,0);
		GridPane.setConstraints(loginField,1,0);
		GridPane.setConstraints(errorLabel,1,1);
		GridPane.setConstraints(loginButton,1,2);
		
		loginPane.getChildren().addAll(loginField,loginButton,errorLabel,nicknameLabel);
		
		
		loginStage.setScene(loginScene);
		loginStage.show();
		
		stage.setScene(scene);
	//	stage.show();
		
	}

}