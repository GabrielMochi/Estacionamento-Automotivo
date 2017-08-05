package estacionamento.repository;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import estacionamento.Estacionamento;
import estacionamento.domain.ClienteAvulso;
import estacionamento.service.Storage;
import java.io.IOException;

public class ClienteAvulsoRepository implements Rest<ClienteAvulso> {

    public static final String FILE_NAME = "clientesAvulso.json";
    
    @Override
    public JsonArray getClients() {
        try {
            return Storage.getFile(Estacionamento.ROOT_PATH, FILE_NAME);
        } catch (IOException ex) {
            return null;
        }
    }

    @Override
    public boolean createClient(ClienteAvulso client) {
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
            ClienteAvulso cliente = gson.fromJson(element, ClienteAvulso.class);
            
            if (cliente.getCpf() == null ? cpf == null : cliente.getCpf().equals(cpf))
                jsonClients.remove(element);
        });
        
        return Storage.writeFile(Estacionamento.ROOT_PATH, FILE_NAME, gson.toJson(jsonClients));
    }

}
