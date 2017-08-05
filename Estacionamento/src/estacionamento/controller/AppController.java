package estacionamento.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.jfoenix.controls.JFXListView;
import estacionamento.Estacionamento;
import estacionamento.domain.ClienteAvulso;
import estacionamento.domain.ClienteConvenio;
import estacionamento.domain.ClienteFixo;
import estacionamento.repository.ClienteAvulsoRepository;
import estacionamento.repository.ClienteConvenioRepository;
import estacionamento.repository.ClienteFixoRepository;
import estacionamento.service.Dialog;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


/**
 * Apontamento: calcular a taxa de cada cliente (método pronto porém não implementado);
 *              Listar os clientes já cadastrados;
 */

public class AppController implements Initializable {
    
    @FXML private JFXListView<Label> listClient ;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ClienteAvulsoRepository avulso = new ClienteAvulsoRepository();
        ClienteConvenioRepository convenio = new ClienteConvenioRepository();
        ClienteFixoRepository fixo = new ClienteFixoRepository();
        
        Gson gson = new Gson();
        JsonArray clientesAvulsoJson = avulso.getClients();
        JsonArray clientesConvenioJson = convenio.getClients();
        JsonArray clientesFixoJson = fixo.getClients();
        
        ObservableList<Label> items = FXCollections.observableArrayList();
        
        clientesAvulsoJson.forEach((JsonElement element) -> {
            System.out.println(element);
            /*Label clientName = new Label(gson.fromJson(element, ClienteAvulso.class).getNome());
            clientName.setFont(Font.font("Roboto Light", 16));
            items.add(clientName);*/
        });
        
        clientesConvenioJson.forEach((JsonElement element) -> {
            System.out.println(element);
            /*Label clientName = new Label(gson.fromJson(element, ClienteConvenio.class).getNome());
            clientName.setFont(Font.font("Roboto Light", 16));
            items.add(clientName);*/
        });
        
        clientesFixoJson.forEach((JsonElement element) -> {
            System.out.println(element);
            /*Label clientName = new Label(gson.fromJson(element, ClienteFixo.class).getNome());
            clientName.setFont(Font.font("Roboto Light", 16));
            items.add(clientName);*/
        });
        
        listClient.setItems(items);
    }
    
    @FXML
    private void openRegisterStage(Event event) {
        try {
            Parent newClient = FXMLLoader.load(Estacionamento.class.getResource("view/NewClient.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Novo registro");
            stage.setScene(new Scene(newClient));
            stage.setResizable(false);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException e) {
            Dialog.showErrorDialog("Um erro interno ocorreu.", ((Node)event.getTarget()).getScene().getRoot());
        }
    }
    
}
