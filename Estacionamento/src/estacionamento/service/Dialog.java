package estacionamento.service;

import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.effect.BoxBlur;

public class Dialog {
        
    public static void showErrorDialog(String message, Parent root) {
        if (root != null)
            root.setEffect(new BoxBlur());

        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.CLOSE);
        alert.setOnCloseRequest((event) -> {
            if (root != null)
                root.setEffect(null);
        });
        alert.showAndWait();
    }
    
    public static void showInformationDialog(String message, Parent root) {
        root.setEffect(new BoxBlur());

        Alert alert = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK);
        alert.setOnCloseRequest((event) -> {
            root.setEffect(null);
        });
        alert.showAndWait();
    }
    
}
