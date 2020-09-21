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
			//URL url = Paths.get("C:\\Users\\Pedro\\eclipse-workspace\\1bimPOO\\src\\view\\crudCliente.fxml").toUri().toURL();
			URL url = Paths.get("C:\\Users\\Pedro\\eclipse-workspace\\1bimPOO\\src\\view\\Menu.fxml").toUri().toURL();
			AnchorPane root = (AnchorPane)FXMLLoader.load(url);
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Trabalho - Prova");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
