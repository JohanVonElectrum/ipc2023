/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlador;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author victo
 */
public class ReservarController implements Initializable {

    @FXML
    private JFXButton reservarBoton;

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
        stage.setTitle("GreenBall CLUB - Ver Reservas");
        stage.show();

        stage.setMinHeight(stage.getHeight());
        stage.setMaxHeight(stage.getHeight());
        stage.setMinWidth(stage.getWidth());
        stage.setMaxWidth(stage.getWidth());

        reservarBoton.getScene().getWindow().hide();
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
        stage.setTitle("GreenBall CLUB - Disponibilidad de pistas");
        stage.show();

        stage.setMinHeight(stage.getHeight());
        stage.setMaxHeight(stage.getHeight());
        stage.setMinWidth(stage.getWidth());
        stage.setMaxWidth(stage.getWidth());

        reservarBoton.getScene().getWindow().hide();
    }

    @FXML
    private void misreservasAction(ActionEvent event) throws IOException {
        FXMLLoader cargador = new FXMLLoader(getClass().getResource("/vista/FXMLMisReservas.fxml"));
        Parent root = cargador.load();
        
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        String css = this.getClass().getResource("/estilos/estiloscss.css").toExternalForm();
        
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.setTitle("GreenBall CLUB - Mis reservas");
        stage.show();

        stage.setMinHeight(stage.getHeight());
        stage.setMaxHeight(stage.getHeight());
        stage.setMinWidth(stage.getWidth());
        stage.setMaxWidth(stage.getWidth());

        reservarBoton.getScene().getWindow().hide();
    }
    
}
