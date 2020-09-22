package view.Principal;

import java.net.URL;
import java.nio.file.Paths;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import negocio.Cliente;

public class Principal  extends Application {
	public static List<Cliente> listaDeClientes;
	
	public static void main(String[] args) {		
			launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/Menu.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Trabalho - Prova");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
