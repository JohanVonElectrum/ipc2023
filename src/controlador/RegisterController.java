/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import model.ClubDAOException;
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
    public void initialize(URL url, ResourceBundle rb)  {
        // TODO
    
    }    

    @FXML
    private void registrar2Action(ActionEvent event) throws IOException, ClubDAOException {
        String nombre = nombreTextField.getText();
        String contraseña = contraseñaTextField.getText();
        String contraseña2 = contraseña2TextField.getText();
        String apellidos = apellidosTextField.getText();
        String usuario = usuarioTextField.getText();
        String tarjeta = tarjetaTextField.getText();
        String telefono = numtelefonoTextField.getText();
        String cvc = cvcTextField.getText();
   

        //COMPROBAR ESPACIOS VACIOS
        
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Diálogo de excepción");
        alert.setHeaderText("Error al crear el usuario");
        alert.setContentText("Se tienen que rellenar todos los campos");
        if(nombre.isEmpty() || apellidos.isEmpty() || contraseña.isEmpty() || contraseña2.isEmpty() ||
                usuario.isEmpty() || telefono.isEmpty() ){
            alert.showAndWait();
        }
        
        //COMPROBAR USUARIO EXISTE
        
        
        
        
        
        
        
        
        
        // COMPROBAR CONTRASEÑAS
        
        else if (contraseña.length() < 6 ) {
        Alert alert2 = new Alert(AlertType.ERROR);
        alert2.setTitle("Diálogo de excepción");
        alert2.setHeaderText("Error al crear el usuario");
        alert2.setContentText("La contraseña tiene que tener al menos 6 caracteres");
        alert2.showAndWait();
        }
        
        else if (!(contraseña.equals(contraseña2) && contraseña.length() == contraseña2.length())) {
            
            errorcontraseñasLabel.setText("Las contraseñas no coinciden");
            }
        
        
        //COMPROBAR NUMEROS
           
           else if (!(esNumero(telefono)) || (!(tarjeta.isEmpty() && cvc.isEmpty()) && !(esNumero(tarjeta) && esNumero(cvc)))) {
        Alert alert3 = new Alert(AlertType.ERROR);
        alert3.setTitle("Diálogo de excepción");
        alert3.setHeaderText("Error");
        alert3.setContentText("Los campos Num. Teléfono, Tarjeta y CVC deben ser valores numéricos");
        alert3.showAndWait();
        errorcontraseñasLabel.setText("");
        }
        
        Club club = Club.getInstance();
        
        
        Member r = club.registerMember(nombre, apellidos, telefono, cvc, telefono, tarjeta, 0, null);
    }

        
        
// ...

      
    
            
            



        
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
    
    
  





    

    


