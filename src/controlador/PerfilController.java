/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlador;

import aplicacion.DialogHelper;
import aplicacion.JavaFXMLApplication;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import static controlador.RegisterController.esNumero;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Member;

/**
 * FXML Controller class
 *
 * @author victo
 */
public class PerfilController implements Initializable {

    @FXML
    private JFXButton cancelarBoton;
    @FXML
    private JFXTextField nombreField;
    @FXML
    private JFXTextField apellidosField;
    @FXML
    private JFXPasswordField passwordField;
    @FXML
    private JFXTextField tarjetaField;
    @FXML
    private JFXTextField cvcField;
    @FXML
    private JFXTextField telefonoField;
    @FXML
    private ChoiceBox<String> fotoField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Member member = JavaFXMLApplication.currentMember;
        nombreField.setText(member.getName());
        apellidosField.setText(member.getSurname());
        passwordField.setText(member.getPassword());
        tarjetaField.setText(member.getCreditCard());
        cvcField.setText(member.getSvc() > 0 ? String.format("%03d", member.getSvc()) : null);
        telefonoField.setText(member.getTelephone());
        fotoField.getSelectionModel().select(member.getImage().getUrl());
    }

    @FXML
    private void cancelarAction(ActionEvent event) throws IOException {
        FXMLLoader cargador = new FXMLLoader(getClass().getResource("/vista/FXMLMenuInicio.fxml"));
        Parent root = cargador.load();

        Stage stage = new Stage();
        Scene scene = new Scene(root);
        String css = this.getClass().getResource("/estilos/estiloscss.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.setTitle("GreenBall CLUB - Menú Inicio");
        stage.show();

        stage.setMinHeight(stage.getHeight());
        stage.setMinWidth(stage.getWidth());

        cancelarBoton.getScene().getWindow().hide();
    }

    @FXML
    private void guardarBoton(ActionEvent event) throws IOException {
        String nombre = emptyIfNull(nombreField.getText());
        String contraseña = emptyIfNull(passwordField.getText());
        String apellidos = emptyIfNull(apellidosField.getText());
        String tarjeta = emptyIfNull(tarjetaField.getText());
        String telefono = emptyIfNull(telefonoField.getText());
        String cvc = emptyIfNull(cvcField.getText());
        String foto = emptyIfNull(fotoField.getValue());
        
        if (nombre.isEmpty()
                || apellidos.isEmpty()
                || contraseña.isEmpty()
                || telefono.isEmpty()) {
            DialogHelper.showAlert(
                    Alert.AlertType.ERROR,
                    "Diálogo de excepción",
                    "Error al crear el usuario",
                    "Por favor, rellene los campos obligatorios."
            );
            return;
        }
        if (contraseña.length() < 6) {
            DialogHelper.showAlert(
                    Alert.AlertType.ERROR,
                    "Diálogo de excepción",
                    "Error al crear el usuario",
                    "La contraseña tiene que tener al menos 6 caracteres."
            );
            return;
        }
        
        if (!(esNumero(telefono)) || (!(tarjeta.isEmpty() && cvc.isEmpty()) && !(esNumero(tarjeta) && esNumero(cvc)))) {
            DialogHelper.showAlert(
                    Alert.AlertType.ERROR,
                    "Diálogo de excepción",
                    "Error al crear el usuario",
                    "Los campos Num. Teléfono, Tarjeta y CVC deben ser valores numéricos."
            );
            return;
        }
        
        JavaFXMLApplication.currentMember.setName(nombre);
        JavaFXMLApplication.currentMember.setSurname(apellidos);
        JavaFXMLApplication.currentMember.setPassword(contraseña);
        JavaFXMLApplication.currentMember.setTelephone(telefono);
        if (!tarjeta.isEmpty() && !cvc.isEmpty()) {
            JavaFXMLApplication.currentMember.setCreditCard(tarjeta);
            JavaFXMLApplication.currentMember.setSvc(Integer.parseInt(cvc));
        }
        if (foto != null && !foto.isEmpty()) {
            JavaFXMLApplication.currentMember.setImage(new Image(foto));
        }
        
        FXMLLoader cargador = new FXMLLoader(getClass().getResource("/vista/FXMLMenuInicio.fxml"));
        Parent root = cargador.load();

        Stage stage = new Stage();
        Scene scene = new Scene(root);
        String css = this.getClass().getResource("/estilos/estiloscss.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.setTitle("GreenBall CLUB - Menú Inicio");
        stage.show();

        stage.setMinHeight(stage.getHeight());
        stage.setMinWidth(stage.getWidth());

        cancelarBoton.getScene().getWindow().hide();
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        JavaFXMLApplication.currentMember = null;
        
        FXMLLoader cargador = new FXMLLoader(getClass().getResource("/vista/FXMLLogin.fxml"));
        Parent root = cargador.load();
        
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        String css = this.getClass().getResource("/estilos/estiloscss.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.setTitle("GreenBall CLUB - Iniciar Sesión");
        stage.show();

        stage.setMinHeight(stage.getHeight());
        stage.setMinWidth(stage.getWidth());

        cancelarBoton.getScene().getWindow().hide();
    }
    
    private String emptyIfNull(String input) {
        if (input == null) return "";
        else return input;
    }
}
