package controlador;

import aplicacion.DialogHelper;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Club;
import model.ClubDAOException;
import model.Member;

/**
 * FXML Controller class
 *
 * @author victo, joan
 */
public class RegisterController implements Initializable {

    @FXML
    private TextField usuarioTextField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField nombreTextField;
    @FXML
    private TextField apellidosTextField;
    @FXML
    private TextField tarjetaTextField;
    @FXML
    private TextField cvcTextField;
    @FXML
    private TextField numtelefonoTextField;
    @FXML
    private PasswordField password2Field;
    @FXML
    private Label errorcontraseñasLabel;
    private ActionEvent ActionEvent;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cvcTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(
                    ObservableValue<? extends String> observable,
                    String oldValue,
                    String newValue
            ) {
                if (!newValue.matches("\\d{1,4}")) {
                    String formatted = newValue.replaceAll("[^\\d]", "");
                    formatted = formatted.isEmpty() ? formatted : formatted.substring(0, Math.min(formatted.length(), 4));
                    cvcTextField.setText(formatted);
                }
            }
        });
    }

    @FXML
    private void registrar2Action(ActionEvent event) {
        String nombre = nombreTextField.getText();
        String contraseña = passwordField.getText();
        String contraseña2 = password2Field.getText();
        String apellidos = apellidosTextField.getText();
        String usuario = usuarioTextField.getText();
        String tarjeta = tarjetaTextField.getText();
        String telefono = numtelefonoTextField.getText();
        String cvc = cvcTextField.getText();

        if (nombre.isEmpty()
                || apellidos.isEmpty()
                || contraseña.isEmpty()
                || contraseña2.isEmpty()
                || usuario.isEmpty()
                || telefono.isEmpty()) {
            DialogHelper.showAlert(
                    AlertType.ERROR,
                    "Diálogo de excepción",
                    "Error al crear el usuario",
                    "Por favor, rellene los campos obligatorios."
            );
            return;
        }
        if (contraseña.length() < 6) {
            DialogHelper.showAlert(
                    AlertType.ERROR,
                    "Diálogo de excepción",
                    "Error al crear el usuario",
                    "La contraseña tiene que tener al menos 6 caracteres."
            );
            return;
        }
        if (contraseña.equals(contraseña2)) {
            errorcontraseñasLabel.setText("");
        } else {
            errorcontraseñasLabel.setText("Las contraseñas no coinciden");
            return;
        }
        if (!(esNumero(telefono)) || (!(tarjeta.isEmpty() && cvc.isEmpty()) && !(esNumero(tarjeta) && esNumero(cvc)))) {
            DialogHelper.showAlert(
                    AlertType.ERROR,
                    "Diálogo de excepción",
                    "Error al crear el usuario",
                    "Los campos Num. Teléfono, Tarjeta y CVC deben ser valores numéricos."
            );
            return;
        }

        try {
            Club.getInstance().registerMember(
                    nombre,
                    apellidos,
                    telefono,
                    usuario,
                    telefono,
                    tarjeta.isEmpty() ? null : tarjeta,
                    cvc.isEmpty() ? 0 : Integer.parseInt(cvc),
                    null
            );
            DialogHelper.showAlert(
                    AlertType.INFORMATION,
                    "Diálogo de información",
                    "Usuario creado",
                    String.format("El usuario %s ha sido registrado exitosamente.", usuario)
            );
            iniciarsesion2Action(null);
        } catch (ClubDAOException ex) {
            if (ex.getMessage().contains("SQLITE_CONSTRAINT_PRIMARYKEY")) {
                usuarioTextField.textProperty().setValue("");
                DialogHelper.showAlert(
                        AlertType.ERROR,
                        "Diálogo de excepción",
                        "Error al crear el usuario",
                        "El nombre de usuario ya está siendo utilizado por otro usuario. Por favor, elija uno distinto."
                );
            }
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public boolean esNumero(String numero) {
        try {
            Double.valueOf(numero);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @FXML
    private void iniciarsesion2Action(ActionEvent event) throws IOException {
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
        stage.setMaxHeight(stage.getHeight());
        stage.setMinWidth(stage.getWidth());
        stage.setMaxWidth(stage.getWidth());

        usuarioTextField.getScene().getWindow().hide();
    }
}
