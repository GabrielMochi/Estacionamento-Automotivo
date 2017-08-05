package estacionamento.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import estacionamento.Estacionamento;
import estacionamento.domain.Cliente;
import estacionamento.domain.ClienteAvulso;
import estacionamento.domain.ClienteConvenio;
import estacionamento.domain.ClienteFixo;
import estacionamento.repository.ClienteAvulsoRepository;
import estacionamento.repository.ClienteConvenioRepository;
import estacionamento.repository.ClienteFixoRepository;
import estacionamento.service.Dialog;
import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class NewClientController implements Initializable {

    @FXML private JFXComboBox<String> clientType, paymentOption;
    @FXML private JFXTextField name, cpf, address, phone;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clientType.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (newValue != null && !newValue.isEmpty())
                switch (newValue) {
                    case "Fixo":
                        paymentOption.setDisable(false);
                        break;
                    default:
                        paymentOption.setDisable(true);
                        break;
                }
        });
    }    
    
    @FXML
    private void onRegister(Event event) {
        String clientTypeSelectedItem = clientType.getSelectionModel().getSelectedItem();
        String paymentOptionSelected = paymentOption.getSelectionModel().getSelectedItem();
        Cliente cliente;
        
        if (clientTypeSelectedItem != null && !clientTypeSelectedItem.isEmpty()) {
            switch (clientTypeSelectedItem) {
                case "Avulso":
                    cliente = new ClienteAvulso(name.getText(), cpf.getText(), address.getText(), phone.getText(), ZoneId.of("Brazil/East"));
                    ClienteAvulsoRepository avusso = new ClienteAvulsoRepository();
                    if (avusso.createClient((ClienteAvulso) cliente))
                        Dialog.showInformationDialog("O cliente foi criado com sucesso", ((Node)event.getTarget()).getScene().getRoot());
                    else
                        Dialog.showErrorDialog("Erro ao criar o cliente", ((Node)event.getTarget()).getScene().getRoot());
                    break;
                case "Convênio":
                    cliente = new ClienteConvenio(name.getText(), cpf.getText(), address.getText(), phone.getText(), ZoneId.of("Brazil/East"));
                    ClienteConvenioRepository convenio = new ClienteConvenioRepository();
                    if (convenio.createClient((ClienteConvenio) cliente))
                        Dialog.showInformationDialog("O cliente foi criado com sucesso", ((Node)event.getTarget()).getScene().getRoot());
                    else
                        Dialog.showErrorDialog("Erro ao criar o cliente", ((Node)event.getTarget()).getScene().getRoot());
                    break;
                case "Fixo":
                    if (paymentOptionSelected != null && !paymentOptionSelected.isEmpty()) {
                        cliente = new ClienteFixo(name.getText(), cpf.getText(), address.getText(), phone.getText(), ZoneId.of("Brazil/East"),
                                (paymentOption.getSelectionModel().getSelectedItem().equals("Quinzenal")) ? ClienteFixo.Payment.QUINZENAL : ClienteFixo.Payment.MENSAL);
                        ClienteFixoRepository fixo = new ClienteFixoRepository();
                        if (fixo.createClient((ClienteFixo) cliente))
                            Dialog.showInformationDialog("O cliente foi criado com sucesso", ((Node)event.getTarget()).getScene().getRoot());
                        else
                            Dialog.showErrorDialog("Erro ao criar o cliente", ((Node)event.getTarget()).getScene().getRoot());
                    } else
                        Dialog.showInformationDialog("Por favor, selecione um tipo de pagamento.", ((Node)event.getTarget()).getScene().getRoot());
                    break;
                default:
                    Dialog.showInformationDialog("Por favor, selecione um tipo de cliente.", ((Node)event.getTarget()).getScene().getRoot());
                    break;
            }
        } else
            Dialog.showInformationDialog("Por favor, selecione um tipo de cliente.", ((Node)event.getTarget()).getScene().getRoot());
        
        
    }
    
    @FXML
    private void onCancel(Event event) {
        ((Node) event.getSource()).getScene().getWindow().hide();
    }
    
}
