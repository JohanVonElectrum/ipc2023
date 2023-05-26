/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import aplicacion.DialogHelper;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Club;
import model.ClubDAOException;
import model.Member;

/**
 *
 * @author jsoler
 */
public class LoginController implements Initializable {

    @FXML
    private TextField usuarioTextField;
    @FXML
    private PasswordField contraseñaTextField;
    //========================================================
    // objects defined into FXML file with fx:id 
    @FXML
    private TextField showpasswordTextField;
    @FXML
    private CheckBox showpasswordCheckBox;

    //=========================================================
    // event handler, fired when button is clicked or 
    //                      when the button has the focus and enter is pressed
    //=========================================================
    // you must initialize here all related with the object 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void iniciarsesionAction(ActionEvent event) {
        String usuario = usuarioTextField.getText();
        String contraseña = contraseñaTextField.getText();

        if (usuario.isEmpty() || contraseña.isEmpty()) {
            DialogHelper.showAlert(
                    Alert.AlertType.ERROR,
                    "Diálogo de excepción",
                    "Error al iniciar sesión",
                    "Los campos usuario y contraseña no pueden estar vaciós."
            );
            return;
        }

        try {
            Member member = Club.getInstance().getMemberByCredentials(usuario, contraseña);
        } catch (ClubDAOException | IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @FXML
    private void registrarAction(ActionEvent event) throws IOException {
        FXMLLoader cargador = new FXMLLoader(getClass().getResource("/vista/FXMLRegister.fxml"));
        Parent root = cargador.load();

        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("GreenBall CLUB - Registrar una cuenta");
        stage.show();

        stage.setMinHeight(stage.getHeight());
        stage.setMaxHeight(stage.getHeight());
        stage.setMinWidth(stage.getWidth());
        stage.setMaxWidth(stage.getWidth());

        usuarioTextField.getScene().getWindow().hide();
    }

    @FXML
    private void verreservasAction(ActionEvent event) throws IOException {
        FXMLLoader cargador = new FXMLLoader(getClass().getResource("/vista/FXMLVerReservas.fxml"));
        Parent root = cargador.load();

        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("GreenBall CLUB - Ver Reservas");
        stage.show();

        stage.setMinHeight(stage.getHeight());
        stage.setMaxHeight(stage.getHeight());
        stage.setMinWidth(stage.getWidth());
        stage.setMaxWidth(stage.getWidth());

        usuarioTextField.getScene().getWindow().hide();

    }

    @FXML
    private void showpasswordAction(ActionEvent event) {
        if (showpasswordCheckBox.isSelected()) {
            showpasswordTextField.setText(contraseñaTextField.getText());
            showpasswordTextField.setVisible(true);
            contraseñaTextField.setVisible(false);
        } else {
            contraseñaTextField.setText(showpasswordTextField.getText());
            showpasswordTextField.setVisible(false);
            contraseñaTextField.setVisible(true);
        }
    }
}
