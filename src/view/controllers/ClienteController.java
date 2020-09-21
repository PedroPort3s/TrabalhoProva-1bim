package view.controllers;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import control.Cliente.ControlCliente;
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
import javafx.stage.Stage;
import negocio.Cliente;
import util.Verifica;
import javafx.scene.control.Alert.AlertType;

public class ClienteController implements Initializable {
	
	private List<Cliente> listaCliente;

	@FXML
    private Button btnCadastrar;

    @FXML
    private Button btnAlterar;

    @FXML
    private Button btnExcluir;

    @FXML
    private TextField txtPesquisa;

    @FXML
    private Button btnPesquisar;

    @FXML
    private ListView<Cliente> lvCliente;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtTelefone;

    @FXML
    private TextField txtEndereco;

    @FXML
    private Label lbIdTexto;

    @FXML
    private Label lbIdValor;
    
    @FXML
    private Button btnListar;
    
    @FXML
    private Button btnVoltar;
    
    @FXML
    private Button btnSair;
    
    @FXML
    void Sair(ActionEvent event) {
    	
    	Alert alert = new Alert(AlertType.CONFIRMATION);

        alert.setTitle("Sair");
        alert.setHeaderText("Deseja sair do prgrama?");
        Optional<ButtonType> result = alert.showAndWait();
         if (result.isPresent() && result.get() == ButtonType.OK) {
             System.exit(0);
         }

    }
    
    @FXML
    void VoltarMenu(ActionEvent event) {
        Stage stage = (Stage) btnVoltar.getScene().getWindow();
        stage.close();
    }

    @FXML
    void Alterar(ActionEvent event) {
    	try {
    		if(listaCliente != null && listaCliente.size() > 0) {
    			ChoiceDialog<Cliente> choiceDialog = new ChoiceDialog<Cliente>(null,listaCliente);
    			choiceDialog.setTitle("Editar");
    			choiceDialog.setHeaderText("Qual cliente voce deseja alterar?");    			
        		choiceDialog.showAndWait().ifPresent(retorno -> {
        			if(retorno != null) {
        				txtEndereco.setText(retorno.getEndereço());
                		txtNome.setText(retorno.getNome());
                		txtTelefone.setText(retorno.getTelefone()+"");
                		
                		lbIdTexto.setVisible(true);
                		lbIdValor.setVisible(true);
                		lbIdValor.setText(retorno.getId()+"");
                		
                		btnCadastrar.setText("Atualizar");                		
        			}        		
            	});
    		}
    		else {
    			throw new Exception("Não há clientes para alterar");
    		}
			
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR,e.getMessage() != ""?e.getMessage() : "Erro ao alterar o cliente",ButtonType.OK);
	    	alert.showAndWait();
		}
    	finally {
    		ListarClientes();
    	}
    }
    
    @FXML
    void Listar(ActionEvent event) {
    	ListarClientes();
    }
    
    void ListarClientes() {
    	this.listaCliente = new ControlCliente().ListarClientes();   
    	lvCliente.getItems().clear();
    	listaCliente.forEach(i->lvCliente.getItems().add(i));
    }
    
    void ListarClientes(List<Cliente> lstCliente) {
    	lvCliente.getItems().clear();
    	
    	if(lstCliente!=null && lstCliente.size() > 0) {
    		lstCliente.forEach(i->lvCliente.getItems().add(i));    		
    	}
    }

    @FXML
    void Cadastrar(ActionEvent event) {
    	try {
    		
    		Cliente cliente = new Cliente();
    		
    		if(btnCadastrar.getText().toUpperCase().equals("CADASTRAR")) {
    			cliente.setNome(txtNome.getText());
    			cliente.setEndereço(txtEndereco.getText());    			
    			cliente.setTelefone(Verifica.ehNumeroLong(txtTelefone.getText()) == true ? Long.parseLong(txtTelefone.getText()) : 0);
    			
    			if(new ControlCliente().CadastrarCliente(cliente) == 1) {    				
    				ListarClientes();
    			}
    			else {
    				throw new Exception("Erro ao cadastrar o cliente");
    			}
    			
    			LimparCadastro();    			
    		}
    		else if (btnCadastrar.getText().toUpperCase().equals("ATUALIZAR")) {    			
    			cliente.setId(Integer.parseInt(lbIdValor.getText()));
    			cliente.setNome(txtNome.getText());
    			cliente.setEndereço(txtEndereco.getText());    			
				cliente.setTelefone(Verifica.ehNumeroLong(txtTelefone.getText()) == true ? Long.parseLong(txtTelefone.getText()) : 0);
				
				if(new ControlCliente().EditarCliente(cliente) == 1) {
					ListarClientes();
				}
				else {
					throw new Exception("Erro ao alterar o cliente");
				}
				
				EscondeId();
				
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
    	finally {
    		ListarClientes();
    	}
    }
    
    void EscondeId() {
    	lbIdValor.setText("");
		lbIdValor.setVisible(false);
		lbIdTexto.setVisible(false);
    }
    
    void LimparCadastro() {
    	txtNome.clear();
    	txtEndereco.clear();
    	txtTelefone.clear();
    }

    @FXML
    void Excluir(ActionEvent event) {
    	
    	if(listaCliente != null && listaCliente.size() > 0) {
    		ChoiceDialog<Cliente> choiseDialog = new ChoiceDialog<Cliente>(null,listaCliente);
    		choiseDialog.setTitle("Excluir cliente");
    		choiseDialog.setContentText("Selecione o cliente que deseja excluir");
    		choiseDialog.showAndWait().ifPresent(retorno -> {
    			if(retorno != null) {    				
    				try {
    					
    					if(new ControlCliente().DeletarCliente(retorno.getId()) == 1) {    						
    						ListarClientes();
    					}
    					else {
    						throw new Exception("Erro ao excluir o cliente");
    					}
    					
					} catch (Exception e) {
						Alert alert = new Alert(AlertType.ERROR,e.getMessage() != ""?e.getMessage() : "Erro ao excluir o cliente",ButtonType.OK);
				    	alert.showAndWait();
					}    		
    			}        		
        	});
    	}
    	else {
    		Alert alert = new Alert(AlertType.ERROR,"Não há clientes cadastrados",ButtonType.OK);
	    	alert.showAndWait();
    	}
    }

    @FXML
    void Pesquisar(ActionEvent event) {
    	try {
			ListarClientes(new ControlCliente().PesquisarLista(txtPesquisa.getText()));
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR,e.getMessage() != ""?e.getMessage() : "Erro ao excluir o cliente",ButtonType.OK);
	    	alert.showAndWait();
		}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		EscondeId();
		this.ListarClientes();
	}

}

