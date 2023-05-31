/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlador;

import aplicacion.FilaBooking;
import aplicacion.JavaFXMLApplication;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;
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
public class ReservarController implements Initializable {

    @FXML
    private JFXButton reservarBoton;
    @FXML
    private DatePicker fecha;
    @FXML
    private TableView<FilaBooking> tabla;

    private Club club;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        tabla.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("fromTime"));
        tabla.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("toTime"));
        ((TableColumn<FilaBooking, String>) tabla.getColumns().get(2)).setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getPista().getName()));
        ((TableColumn<FilaBooking, String>) tabla.getColumns().get(3)).setCellValueFactory(cellData -> new ReadOnlyStringWrapper(
                cellData.getValue().getReservado() == null ?
                        "No reservado" :
                        cellData.getValue().getReservado().getNickName()
        ));

        fecha.setValue(LocalDate.now());
        try {
            club = Club.getInstance();
            refreshTable();
        } catch (IOException | ClubDAOException ex) {
            throw new RuntimeException(ex);
        }

        reservarBoton.disableProperty().bind(
                Bindings.createBooleanBinding(() -> {
                    FilaBooking selectedItem = tabla.getSelectionModel().getSelectedItem();
                    return selectedItem == null || selectedItem.getReservado() != null;
                }, tabla.getSelectionModel().selectedItemProperty())
        );

    }

    private void refreshTable() throws ClubDAOException, IOException {
        LocalDate date = fecha.getValue();

        Map<Court, Map<Integer, Booking>> map = club.getCourts()
                .stream()
                .map(court -> Map.entry(court, club.getCourtBookings(court.getName(), date)))
                .map(entry -> Map.entry(entry.getKey(), entry.getValue().stream().collect(Collectors.toMap(booking -> booking.getFromTime().getHour(), booking -> booking))))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        tabla.getItems().clear();
        Stream.iterate(LocalTime.of(9, 0), dateTime -> dateTime.plusHours(1))
                .limit(22 - 9)
                .forEach(time -> {
                    club.getCourts().forEach(court -> {
                        Booking reservado = map.containsKey(court) && map.get(court).containsKey(time.getHour())
                                ? map.get(court).get(time.getHour()) : null;
                        FilaBooking fila = reservado == null ? new FilaBooking(date, time, court, null) : new FilaBooking(reservado);
                        tabla.getItems().add(fila);
                    });
                });
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

    @FXML
    private void reservar(ActionEvent event) {
        FilaBooking booking = tabla.getSelectionModel().getSelectedItem();
        try {
            club.registerBooking(
                    LocalDateTime.now(),
                    fecha.getValue(),
                    booking.getFromTime(),
                    true,
                    booking.getPista(),
                    JavaFXMLApplication.currentMember
            );
            refreshTable();
        } catch (ClubDAOException | IOException ex) {
            throw new RuntimeException(ex);
        }
    }

}
