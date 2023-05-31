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
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author victo
 */
public class MisReservasController implements Initializable {

    @FXML
    private Button inicioBoton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void inicioAction(ActionEvent event) throws IOException {
        FXMLLoader cargador = new FXMLLoader(getClass().getResource("/vista/FXMLMenuInicio.fxml"));
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
            
            inicioBoton.getScene().getWindow().hide();
    }

    @FXML
    private void disponibilidadAction(ActionEvent event) throws IOException {
        FXMLLoader cargador = new FXMLLoader(getClass().getResource("/vista/FXMLDisponibilidadPistasLogeado.fxml"));
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
            
            inicioBoton.getScene().getWindow().hide();
    }

    @FXML
    private void reservarAction(ActionEvent event) throws IOException {
        FXMLLoader cargador = new FXMLLoader(getClass().getResource("/vista/FXMLreservar.fxml"));
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
            
            inicioBoton.getScene().getWindow().hide();
    }
    
}
