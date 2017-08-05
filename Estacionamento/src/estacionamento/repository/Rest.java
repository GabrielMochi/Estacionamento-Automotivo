package estacionamento.repository;

import com.google.gson.JsonArray;

public interface Rest<T> {
    
    public JsonArray getClients();
    public boolean createClient(T client);
    public boolean deleteClcient(String cpf);
    
}
