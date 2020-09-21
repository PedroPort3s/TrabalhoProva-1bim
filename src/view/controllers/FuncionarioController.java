package view.controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import control.Cliente.ControlCliente;
import controlFuncionario.ControlFuncionario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import negocio.Cliente;
import negocio.Funcionario;
import util.Verifica;

public class FuncionarioController implements Initializable {

    @FXML
    private Label lbQntRegistros;

    @FXML
    private TextField txtNome;

    @FXML
    private Label lbIdTexto;

    @FXML
    private Label lbIdValor;

    @FXML
    private TextField txtTelefone;

    @FXML
    private TextField txtSalario;
    
    @FXML
    private TextField txtEndereco;

    @FXML
    private ListView<Funcionario> lvFuncionario;

    @FXML
    private Label lbDtCadTexto;

    @FXML
    private Label lbDtCadastroValor;

    @FXML
    private Button btnCadastrar;

    @FXML
    private Button btnAlterar;

    @FXML
    private Button btnExcluir;
    
    @FXML
    private Button btnVoltar;
    
   
    
    @FXML
    void VoltarMenu(ActionEvent event) {
        Stage stage = (Stage) btnVoltar.getScene().getWindow();
        stage.close();
    }
    
    void LimparCadastro() {
    	txtEndereco.clear();
    	txtNome.clear();
    	txtSalario.clear();
    	txtTelefone.clear();
    }
    
    void EscondeIdData(boolean mostra) {
    	lbDtCadastroValor.setVisible(mostra);
    	lbDtCadTexto.setVisible(mostra);
    	lbIdTexto.setVisible(mostra);
    	lbIdValor.setVisible(mostra);
    }
    
    void ListarGrid() {    	
    	lbQntRegistros.setText(0+"");
    	lvFuncionario.getItems().clear();
    	
    	List<Funcionario> listaFuncionario;
    	
		try {			
			listaFuncionario = new ControlFuncionario().ListarFuncionarios();
			
			if(listaFuncionario != null && listaFuncionario.size()>0) {
	    		lbQntRegistros.setText(listaFuncionario.size()+"");    		
	    		listaFuncionario.forEach(i->lvFuncionario.getItems().add(i));
	    	}
		} catch (ClassNotFoundException | SQLException e) {
			Alert alert = new Alert(AlertType.ERROR,e.getMessage() != ""?e.getMessage() : "Erro ao listar os funcionarios",ButtonType.OK);
	    	alert.showAndWait();
		}
    }

    @FXML
    void Alterar(ActionEvent event) {
    	try {
    		
    			ChoiceDialog<Funcionario> choiceDialog = new ChoiceDialog<Funcionario>(null,new ControlFuncionario().ListarFuncionarios());
    			choiceDialog.setTitle("Editar");
    			choiceDialog.setHeaderText("Qual cliente voce deseja alterar?");    			
        		choiceDialog.showAndWait().ifPresent(retorno -> {
        			if(retorno != null) {
        				txtNome.setText(retorno.getNome());
        				txtTelefone.setText(retorno.getTelefone()+"");
                		txtSalario.setText(retorno.getSalario()+"");
                		txtEndereco.setText(retorno.getEndereco());
                		
                		this.EscondeIdData(true);
                		
                		lbIdValor.setText(retorno.getId()+"");
                		lbDtCadastroValor.setText(retorno.getData_cadastro()+"");
                		
                		btnCadastrar.setText("Atualizar");                		
        			}        		
            	});
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR,e.getMessage() != ""?e.getMessage() : "Erro ao alterar o cliente",ButtonType.OK);
	    	alert.showAndWait();
		}
    }

    @FXML
    void Cadastrar(ActionEvent event) {
    	try {
    		
    		Funcionario funcionario = new Funcionario();
    		
    		if(btnCadastrar.getText().toUpperCase().equals("CADASTRAR")) {
    			funcionario.setNome(txtNome.getText());
    			funcionario.setEndereco(txtEndereco.getText());    			
    			funcionario.setTelefone(txtTelefone.getText());
    			funcionario.setSalario(txtSalario.getText());
    			
    			if(new ControlFuncionario().GravarFuncionario(funcionario) == 1) {    				
    				this.ListarGrid();
    			}
    			else {
    				throw new Exception("Erro ao cadastrar o cliente");
    			}
    			
    			LimparCadastro();    			
    		}
    		else if (btnCadastrar.getText().toUpperCase().equals("ATUALIZAR")) {    			
    			funcionario.setId(Integer.parseInt(lbIdValor.getText()));
    			funcionario.setNome(txtNome.getText());
    			funcionario.setEndereco(txtEndereco.getText());    			
    			funcionario.setTelefone(txtTelefone.getText());
    			funcionario.setSalario(txtSalario.getText());
				
				if(new ControlFuncionario().AtualizarFuncionario(funcionario) == 1) {
					ListarGrid();
				}
				else {
					throw new Exception("Erro ao alterar o cliente");
				}
				
				this.EscondeIdData(false);
				
				btnCadastrar.setText("Cadastrar");
				LimparCadastro();
    		}
    		else {
    			throw new Exception("Erro ao definir tipo de cadastro " + btnCadastrar.getText().toUpperCase());    			
    		}
    		
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR,e.getMessage() != ""?e.getMessage() : "Erro ao alterar o cliente",ButtonType.OK);
	    	alert.showAndWait();
		}
    }

    @FXML
    void Excluir(ActionEvent event) {

    	try {
			
		
    		ChoiceDialog<Funcionario> choiseDialog = new ChoiceDialog<Funcionario>(null,new ControlFuncionario().ListarFuncionarios());
    		choiseDialog.setTitle("Excluir funcionario");
    		choiseDialog.setHeaderText("Selecione o funcionario que deseja excluir");    		
    		choiseDialog.showAndWait().ifPresent(retorno -> {
    			if(retorno != null) {    				
    				try {
    					Alert alert = new Alert(AlertType.CONFIRMATION);

    			        alert.setTitle("Excluir");
    			        alert.setHeaderText("Deseja realmente excluir o funcionario "+retorno.getId() + " " + retorno.getNome()+"?");

    			        Optional<ButtonType> result = alert.showAndWait();
    			         if (result.isPresent() && result.get() == ButtonType.OK) {
    			        	 if(new ControlFuncionario().DeletarFuncionario(retorno) == 1) {    						
    	    						this.ListarGrid();
    	    					}
    	    					else {
    	    						throw new Exception("Erro ao excluir o funcionario");
    	    					}
    			         }
    					
					} catch (Exception e) {
						Alert alert = new Alert(AlertType.ERROR,e.getMessage() != ""?e.getMessage() : "Erro ao excluir o funcionario",ButtonType.OK);
				    	alert.showAndWait();
					}    		
    			}        		
        	});		
    	} catch (Exception e) {
    		Alert alert = new Alert(AlertType.ERROR,e.getMessage() != ""?e.getMessage() : "Erro ao excluir o funcionario",ButtonType.OK);
	    	alert.showAndWait();
		}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.ListarGrid();
		EscondeIdData(false);
	}

}

