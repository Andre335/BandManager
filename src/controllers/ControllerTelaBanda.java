package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import org.controlsfx.dialog.Dialog;

import Fonte.Banda;
import Fonte.GerenteDeUsuarios;
import Fonte.Usuario;

public class ControllerTelaBanda {
	private Banda banda;
	private Stage stage;
	private Dialog dialog;
	
	@FXML
	private TextArea taBandaInfo;
	
	@FXML
	void initialize(Banda banda) {
		taBandaInfo.setText(banda.toString());
	}
	
	public void setStage(Stage stage) {
		this.stage = stage;
	}

}
