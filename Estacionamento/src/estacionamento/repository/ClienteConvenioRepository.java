package estacionamento.repository;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import estacionamento.Estacionamento;
import estacionamento.domain.ClienteConvenio;
import estacionamento.service.Storage;
import java.io.IOException;

public class ClienteConvenioRepository implements Rest<ClienteConvenio> {

    public static final String FILE_NAME = "clientesConvenio.json";
    
    @Override
    public JsonArray getClients() {
        try {
            return Storage.getFile(Estacionamento.ROOT_PATH, FILE_NAME);
        } catch (IOException ex) {
            return null;
        }
    }

    @Override
    public boolean createClient(ClienteConvenio client) {
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonObject jsonClient = (JsonObject)parser.parse(gson.toJson(client));
        JsonArray jsonClients = getClients();
        jsonClients.add(jsonClient);
        return Storage.writeFile(Estacionamento.ROOT_PATH, FILE_NAME, gson.toJson(jsonClients));
    }

    @Override
    public boolean deleteClcient(String cpf) {
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonArray jsonClients = getClients();
        
        jsonClients.forEach((JsonElement element) -> {
            ClienteConvenio cliente = gson.fromJson(element, ClienteConvenio.class);
            
            if (cliente.getCpf() == null ? cpf == null : cliente.getCpf().equals(cpf))
                jsonClients.remove(element);
        });
        
        return Storage.writeFile(Estacionamento.ROOT_PATH, FILE_NAME, gson.toJson(jsonClients));
    }

}
