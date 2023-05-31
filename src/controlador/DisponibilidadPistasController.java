/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlador;

import aplicacion.FilaBooking;
import aplicacion.JavaFXMLApplication;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Booking;
import model.Club;
import model.ClubDAOException;
import model.Court;

/**
 * FXML Controller class
 *
 * @author victo
 */
public class DisponibilidadPistasController implements Initializable {

    @FXML
    private Label etiquetaverreservasLabel;
    @FXML
    private TableView<FilaBooking> tabla;

    private Club club;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tabla.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("date"));
        tabla.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("fromTime"));
        tabla.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("toTime"));
        ((TableColumn<FilaBooking, String>) tabla.getColumns().get(3)).setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getPista().getName()));
        ((TableColumn<FilaBooking, String>) tabla.getColumns().get(4)).setCellValueFactory(cellData -> new ReadOnlyStringWrapper(
                cellData.getValue().getReservado().getNickName()
        ));

        try {
            club = Club.getInstance();
            refreshTable();
        } catch (IOException | ClubDAOException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    private void refreshTable() throws ClubDAOException, IOException {
        tabla.getItems().clear();
        club.getBookings().forEach(booking -> {
            tabla.getItems().add(new FilaBooking(booking));
        });
    }

    @FXML
    private void iniciarsesion3Action(ActionEvent event) throws IOException {
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

        etiquetaverreservasLabel.getScene().getWindow().hide();
    }

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

        etiquetaverreservasLabel.getScene().getWindow().hide();
    }
}
