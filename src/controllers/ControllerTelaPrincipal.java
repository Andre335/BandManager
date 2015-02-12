package controllers;

import java.io.IOException;
import java.util.List;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;

import org.controlsfx.dialog.Dialog;

import Fonte.Banda;
import Fonte.GerenteDeUsuarios;
import Fonte.Usuario;
import controllers.ControllerTelaCadastro.Eventos;
import excecao.BandManagerException;

public class ControllerTelaPrincipal {
	private final EventHandler<ActionEvent> eventos = (EventHandler<ActionEvent>) new Eventos();
	private Usuario usuarioAtivo;
	private Stage stage;
	private Dialog dialog;
	
	@FXML
	private Button btAddBanda;
	
	@FXML
	private Button btRemoveBanda;
	
	@FXML
	private Button btProcurarBanda;
	
	@FXML
	private TextField tfNomeBanda;
	
	@FXML
	private TableView<Banda> tabBandas;
	
	@FXML
	private TableColumn<Banda, String> colBandas;
	
	@FXML
	private Button btSair;
	
	@FXML
	private AnchorPane content;
	
	@FXML
	void initialize(Usuario usuario) {
		usuarioAtivo = usuario;
				
		btAddBanda.setOnAction(eventos);
		btRemoveBanda.setOnAction(eventos);
		btProcurarBanda.setOnAction(eventos);
		tfNomeBanda.setOnAction(eventos);
		btSair.setOnAction(eventos);
		
		dialog = new Dialog(null, "BandManager");
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	public class Eventos implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent evento) {
			if (evento.getSource() == btSair) {
				try {
					content.getChildren().setAll(FXMLLoader.load(getClass()
							.getResource("../gui/TelaLogin.fxml")));
				} catch (IOException e) {
					System.err.println(e.getMessage());
				}
			}
			else if (evento.getSource() == btProcurarBanda) {
				try {
					Banda banda = usuarioAtivo.pesquisaBanda(tfNomeBanda.getText());
					FXMLLoader fxmlLoader = new FXMLLoader(getClass()
							.getResource(
									"../gui/TelaBanda.fxml"));
					Parent root = (Parent) fxmlLoader.load();
					ControllerTelaBanda controller = fxmlLoader
							.<ControllerTelaBanda> getController();
					controller.initialize(banda);
					content.getChildren().setAll(root);
				} catch (BandManagerException e) {
					dialog.setContent(e.getMessage());
					dialog.show();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else if (evento.getSource() == btAddBanda) {
				try {
					Banda banda = new Banda(tfNomeBanda.getText());
					usuarioAtivo.addBanda(banda);
					
					dialog.setContent("Banda adicionada com sucesso!");
					dialog.show();
				} catch (Exception e) {
					dialog.setContent(e.getMessage());
					dialog.show();
				}
			}
			else if (evento.getSource() == btRemoveBanda) {
				Banda banda;
				try {
					banda = usuarioAtivo.pesquisaBanda(tfNomeBanda.getText());
					usuarioAtivo.removeBanda(banda);
					
					dialog.setContent("Banda removida com sucesso!");
					dialog.show();
				} catch (BandManagerException e) {
					dialog.setContent(e.getMessage());
					dialog.show();
				}
			}
		}
	}
}
