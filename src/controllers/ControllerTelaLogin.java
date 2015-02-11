package controllers;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;
import org.controlsfx.dialog.Dialogs.CommandLink;

import excecao.BandManagerException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;	
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;
import Fonte.GerenteDeUsuarios;
import Fonte.Usuario;

public class ControllerTelaLogin {
	
	private final EventHandler<ActionEvent> eventos = (EventHandler<ActionEvent>) new Eventos();
	private final GerenteDeUsuarios gerente = new GerenteDeUsuarios();
	private Usuario usuarioAtivo;
	private Stage stage;
	private Dialog dialog;
	
	@FXML
	private TextField tfLogin;
	
	@FXML
	private PasswordField pfSenha;
	
	@FXML
	private Button btEntrar;
	
	@FXML
	private Button btCadastrar;
	
	@FXML
	private AnchorPane content;
	
	@FXML
	private Label lAviso;
	
	@FXML
	void initialize() {
		btEntrar.setOnAction(eventos);
		btCadastrar.setOnAction(eventos);
		tfLogin.setOnAction(eventos);
		pfSenha.setOnAction(eventos);
		
		dialog = new Dialog(null, "BandManager");
	}
	
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	public class Eventos implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent evento) {
			if (evento.getSource() == btCadastrar) {
				try {
					content.getChildren().setAll(FXMLLoader.load(getClass()
							.getResource("../gui/TelaCadastro.fxml")));
				} catch (IOException e) {
					System.err.println(e.getMessage());
				}
			} 
			
			else if (evento.getSource() == btEntrar) {
				Usuario userLogar = gerente.pesquisaUsuario(tfLogin.getText().toString());
				if (userLogar != null && pfSenha.getText().toString().equals(userLogar.getSenha())) {
					try {
						gerente.login(userLogar.getNome(), userLogar.getSenha());
						content.getChildren().setAll(FXMLLoader.load(getClass()
								.getResource("../gui/TelaPrincipal.fxml")));
					} catch (BandManagerException | GeneralSecurityException
							| IOException e) {
						dialog.setContent(e.getMessage());
						dialog.show();
					}
				} else if (userLogar == null) {
					dialog.setContent("Usuario nao existe!");
					dialog.show();
				}
			}
			
		}
		
	}
	
}
