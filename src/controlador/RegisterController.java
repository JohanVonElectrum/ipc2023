/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlador;

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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Club;
import model.Member;

/**
 * FXML Controller class
 *
 * @author victo
 */
public class RegisterController implements Initializable {

    @FXML
    private TextField usuarioTextField;
    @FXML
    private TextField contraseñaTextField;
    @FXML
    private TextField nombreTextField;
    @FXML
    private TextField apellidosTextField;
    @FXML
    private TextField tarjetaTextField;
    @FXML
    private TextField cvcTextField;
    @FXML
    private ComboBox<Member> fotoperfilComboBox;
    @FXML
    private TextField numtelefonoTextField;
    @FXML
    private TextField contraseña2TextField;
    @FXML
    private Label errorcontraseñasLabel;
    private ActionEvent ActionEvent;
    @FXML
    private Label errortelefonoLabel;
    @FXML
    private Label errorcvcLabel;
    @FXML
    private Label errortarjetaLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void registrar2Action(ActionEvent event) throws IOException {
        String nombre = nombreTextField.getText();
        String contraseña = contraseñaTextField.getText();
        String contraseña2 = contraseña2TextField.getText();
        String apellidos = apellidosTextField.getText();
        String usuario = usuarioTextField.getText();
        String tarjeta = tarjetaTextField.getText();
        String telefono = numtelefonoTextField.getText();
        String cvc = cvcTextField.getText();
        boolean contraseñasIguales = true;
        
        
        
            
        
        
        
        //COMPROBAR ESPACIOS VACIOS
        
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Diálogo de excepción");
        alert.setHeaderText("Error al crear el usuario");
        alert.setContentText("Se tienen que rellenar todos los campos");
        if(nombre.isEmpty() || apellidos.isEmpty() || contraseña.isEmpty() || contraseña2.isEmpty() ||
                usuario.isEmpty() || telefono.isEmpty() ){
            alert.showAndWait();
        }
        
        // COMPROBAR CONTRASEÑAS
        else if (contraseña.length() < 6 ) {
        Alert alert2 = new Alert(AlertType.ERROR);
        alert2.setTitle("Diálogo de excepción");
        alert2.setHeaderText("Error al crear el usuario");
        alert2.setContentText("La contraseña tiene que tener al menos 6 caracteres");
        alert2.showAndWait();
        }
        
        else if (contraseña.equals(contraseña2) && contraseña.length() == contraseña2.length()) {
            errorcontraseñasLabel.setText("");
        }else{
            errorcontraseñasLabel.setText("*Las contraseñas no coinciden*");
        }
        
        
        
            Alert alert3 = new Alert(AlertType.ERROR);
            alert3.setTitle("Diálogo de excepción");
            alert3.setHeaderText("Error");
            alert3.setContentText("Los campos Num. Teléfono, Tarjeta y CVC han de ser valores numéricos");

        try {
                double numericValueTelefono = Double.parseDouble(telefono); 
                double numericValueTarjeta = Double.parseDouble(tarjeta); 
                double numericValue = Double.parseDouble(tarjeta);
            } catch (NumberFormatException ex) {
                 alert3.showAndWait();
            }
           
            if (telefono.length() < 9) {
                errortelefonoLabel.setText("Teléfono no válido");
            } else {
            errortelefonoLabel.setText("");
    
            
            if (cvc.length() < 3) {
            errorcvcLabel.setText("CVC no válido");
                } else {
            errorcvcLabel.setText("");
            }
            
            
            if (tarjeta.length() < 16) {
            errortarjetaLabel.setText("Tarjeta no válida");
                } else {
            errortarjetaLabel.setText("");
            }
            }


        
        //Club club = Club.getInstance();
        //Member result = club.registerMember(nombre, apellidos, telefono, usuario, contraseña, tarjeta, cvc, null);

        // registerClear();
        // iniciarsesion2Action(ActionEvent);

  
    
        
   
    //LIMPIAR CAMPOS DEL REGISTRO
    /*private void registerClear() {
        nombreTextField.setText("");
        contraseñaTextField.setText("");
        contraseña2TextField.setText("");
        apellidosTextField.setText("");
        usuarioTextField.setText("");
        numtelefonoTextField.setText("");
        tarjetaTextField.setText("");
        cvcTextField.setText("");
        
    } */
        
        
        
        
    }
    

    @FXML
    private void iniciarsesion2Action(ActionEvent event) throws IOException {
            FXMLLoader cargador = new FXMLLoader(getClass().getResource("/vista/FXMLLogin.fxml"));
            Parent root = cargador.load();
            
            Stage stage = new Stage();
            Scene scene = new Scene(root);
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



    

    


    

