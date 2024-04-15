package az.developia.notepad.main;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application{
   public static void main(String[] args) {
	   launch(args);
   }

@Override
public void start(Stage stage1){
	AnchorPane pane;
	try {
		pane = FXMLLoader.load(getClass().getClassLoader().getResource("az/developia/notepad/view/notepad.fxml"));
		Scene scene = new Scene(pane);
		stage1.setScene(scene);
		stage1.setResizable(false);
		stage1.setTitle("Notepad App");
		stage1.show();
	} catch (IOException e) {
		e.printStackTrace();
	}

}
}
