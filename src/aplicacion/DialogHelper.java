package aplicacion;

import javafx.scene.control.Alert;

/**
 *
 * @author joan
 */
public class DialogHelper {
    
    public static Alert createAlert(
            Alert.AlertType type,
            String title,
            String header,
            String content
    ) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        
        
        return alert;
    }
    
    public static void showAlert(Alert alert) {
        showAlert(alert, true);
    }
    
    public static void showAlert(Alert alert, boolean wait) {
        if (wait)
            alert.showAndWait();
        else
            alert.show();
    }
    
    public static void showAlert(
            Alert.AlertType type,
            String title,
            String header,
            String content
    ) {
        showAlert(createAlert(type, title, header, content));
    }
    
}
