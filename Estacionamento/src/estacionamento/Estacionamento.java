package estacionamento;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import estacionamento.service.Storage;
import java.io.File;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import estacionamento.repository.ClienteAvulsoRepository;
import estacionamento.repository.ClienteConvenioRepository;
import estacionamento.repository.ClienteFixoRepository;
import estacionamento.service.Dialog;
import javafx.application.Platform;

public class Estacionamento extends Application {
    
    public static String APP_NAME = "Estacionamento Automotivo";
    public static final String ROOT_PATH = (System.getProperty("os.name").equals("Mac OS X")) ? 
            System.getProperty("user.home") + File.separator + "Documents" :
            System.getenv("APPDATA");

    @Override
    public void start(Stage stage) throws Exception {                
        Gson gson = new Gson();
        JsonArray array = new JsonArray();
        
        if (!Storage.createFile(ROOT_PATH, ClienteAvulsoRepository.FILE_NAME) ||
                !Storage.createFile(ROOT_PATH, ClienteConvenioRepository.FILE_NAME) ||
                !Storage.createFile(ROOT_PATH, ClienteFixoRepository.FILE_NAME)) {
            Dialog.showErrorDialog("Um erro interno ocorreu ao listar os clientes", null);
            Platform.exit();
            System.exit(1);
        }
        
        Parent root = FXMLLoader.load(getClass().getResource("view/App.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setTitle("Estacionamento Automotivo");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
