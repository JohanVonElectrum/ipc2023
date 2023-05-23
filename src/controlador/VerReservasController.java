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
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author victo
 */
public class VerReservasController implements Initializable {

    @FXML
    private Label etiquetaverreservasLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void iniciarsesion3Action(ActionEvent event) throws IOException {
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
            
            etiquetaverreservasLabel.getScene().getWindow().hide();
    }
    }
    

