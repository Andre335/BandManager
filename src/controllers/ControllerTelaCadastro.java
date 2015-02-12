package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import org.controlsfx.dialog.Dialog;

import Fonte.GerenteDeUsuarios;
import Fonte.Usuario;
import controllers.ControllerTelaLogin.Eventos;

public class ControllerTelaCadastro {
	private final EventHandler<ActionEvent> eventos = (EventHandler<ActionEvent>) new Eventos();
	private final GerenteDeUsuarios gerente = new GerenteDeUsuarios();
	private Usuario usuarioAtivo;
	private Stage stage;
	private Dialog dialog;
	
	@FXML
	private TextField tfLogin;
	
	@FXML
	private TextField tfEmail;
	
	@FXML
	private PasswordField pfSenha;
	
	@FXML
	private PasswordField pfConfSenha;
	
	@FXML
	private Button btVoltar;
	
	@FXML
	private Button btCadastrar;
	
	@FXML
	private AnchorPane content;
	
	@FXML
	void initialize() {
		btVoltar.setOnAction(eventos);
		btCadastrar.setOnAction(eventos);
		tfLogin.setOnAction(eventos);
		tfEmail.setOnAction(eventos);
		pfSenha.setOnAction(eventos);
		pfConfSenha.setOnAction(eventos);
		
		dialog = new Dialog(null, "BandManager");
	}
	
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	public class Eventos implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent evento) {
			if (evento.getSource() == btVoltar) {
				try {
					content.getChildren().setAll(FXMLLoader.load(getClass()
							.getResource("../gui/TelaLogin.fxml")));
				} catch (IOException e) {
					System.err.println(e.getMessage());
				}
			}
			
			else if (evento.getSource() == btCadastrar) {
				Usuario userCriar = gerente.pesquisaUsuario(tfLogin.getText().toString());
				if (userCriar == null && checaSenhas(pfSenha.getText(), pfConfSenha.getText())) {
					try {
						userCriar = new Usuario(tfLogin.getText(), tfEmail.getText(),
								pfSenha.getText());
						gerente.adicionaUsuario(userCriar);
						dialog.setContent("Usuario criado com sucesso!");
						dialog.show();
					} catch (Exception e) {
						dialog.setContent(e.getMessage());
						dialog.show();
					}
				} else if (userCriar != null) {
					dialog.setContent("Usuario ja existente!");
					dialog.show();
				}
			}
			
		}

		private boolean checaSenhas(String senha, String confSenha) {
			return senha.equals(confSenha) ? true : false;
		}
		
	}
}
