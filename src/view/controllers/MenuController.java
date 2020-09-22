package view.controllers;

import java.net.URL;
import java.nio.file.Paths;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MenuController {

    @FXML
    private Button btnProva;

    @FXML
    private Button btnTrabalho;

    @FXML
    void Prova(ActionEvent event) {
    	try {
			Stage primaryStage = new Stage();
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/crudFuncionario.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("CRUD de Funcionario - Banco MySql");
			primaryStage.show();
		} catch(Exception e) {
			Alert alert = new Alert(AlertType.ERROR,e.getMessage(),ButtonType.OK);
	    	alert.showAndWait();
		}
    }
    

    @FXML
    void Trabalho(ActionEvent event) {
    	try {
			Stage primaryStage = new Stage();			
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/crudCliente.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("CRUD de Cliente - Collections");
			primaryStage.show();
		} catch(Exception e) {
			Alert alert = new Alert(AlertType.ERROR,e.getMessage(),ButtonType.OK);
	    	alert.showAndWait();
		}
    }
}

