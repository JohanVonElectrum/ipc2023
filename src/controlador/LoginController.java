/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import aplicacion.DialogHelper;
import aplicacion.JavaFXMLApplication;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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
    @FXML
    private AnchorPane rootAnchorPane;

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
                    "Los campos usuario y contraseña no pueden estar vacíos.",
                    rootAnchorPane
            );
            return;
        }

        try {
            JavaFXMLApplication.currentMember = Club.getInstance().getMemberByCredentials(usuario, contraseña);
            if (JavaFXMLApplication.currentMember == null) {
                DialogHelper.showAlert(
                        Alert.AlertType.ERROR,
                        "Diálogo de excepción",
                        "Error al iniciar sesión",
                        "Los campos usuario y contraseña no son correctos.",
                        rootAnchorPane
                );
                return;
            }
            DialogHelper.showAlert(
                    Alert.AlertType.INFORMATION,
                    "Diálogo de información",
                    "Sesión iniciada",
                    String.format("Ha iniciado sesión con el usuario %s.", JavaFXMLApplication.currentMember.getNickName()),
                    rootAnchorPane
            );
            FXMLLoader cargador = new FXMLLoader(getClass().getResource("/vista/FXMLMenuInicio.fxml"));
            Parent root = cargador.load();

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            String css = this.getClass().getResource("/estilos/estiloscss.css").toExternalForm();
            scene.getStylesheets().add(css);
            stage.setScene(scene);
            stage.setTitle("GreenBall CLUB - Menú inicio");
            stage.show();
            
            stage.setMinHeight(stage.getHeight());
            stage.setMinWidth(stage.getWidth());

            

            usuarioTextField.getScene().getWindow().hide();

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
        String css = this.getClass().getResource("/estilos/estiloscss.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.setTitle("GreenBall CLUB - Registrar una cuenta");
        stage.show();
        
        stage.setMinHeight(stage.getHeight());
        stage.setMinWidth(stage.getWidth());
        

        usuarioTextField.getScene().getWindow().hide();
    }

    @FXML
    private void verreservasAction(ActionEvent event) throws IOException {
        FXMLLoader cargador = new FXMLLoader(getClass().getResource("/vista/FXMLDisponibilidadPistas.fxml"));
        Parent root = cargador.load();

        Stage stage = new Stage();
        Scene scene = new Scene(root);
        String css = this.getClass().getResource("/estilos/estiloscss.css").toExternalForm();

        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.setTitle("GreenBall CLUB - Disponibilidad pistas");
        stage.show();

        stage.setMinHeight(stage.getHeight());
        stage.setMinWidth(stage.getWidth());

        usuarioTextField.getScene().getWindow().hide();

    }

    @FXML
    private void showpasswordAction(ActionEvent event) {
        if (showpasswordCheckBox.isSelected()) {
            showpasswordTextField.setDisable(false);
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
