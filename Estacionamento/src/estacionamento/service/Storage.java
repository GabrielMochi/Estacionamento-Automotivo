package estacionamento.service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import estacionamento.Estacionamento;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Storage {
    
    public static boolean createFile(String path, String fileName) {
        File file = new File(path + File.separator + Estacionamento.APP_NAME + File.separator + fileName);
        if (Files.notExists(Paths.get(file.getPath()))) {
            if (createFolder(path)) {
                try {
                    file.createNewFile();
                    return writeFile(path, fileName, new Gson().toJson(new JsonArray()));
                } catch (IOException ex) {
                    Logger.getLogger(Storage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return false;
        }
        return true;
    }
    
    public static boolean createFolder(String path) {
        File folder = new File(path + File.separator + Estacionamento.APP_NAME);
        if (Files.exists(Paths.get(folder.getPath())))
            return true;
        return folder.mkdirs();
    }
    
    public static boolean writeFile(String path, String fileName, String data) {
        File file = new File(path + File.separator + Estacionamento.APP_NAME + File.separator + fileName);
        PrintWriter writer;
        
        try {
            writer = new PrintWriter(file, "UTF-8");
            
            System.out.println(data);
            writer.print(data);
            writer.close();
            
            return true;
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            return false;
        }
        
    }
    
    public static JsonArray getFile(String path, String fileName) throws FileNotFoundException, IOException {
        try (FileReader reader = new FileReader(path + File.separator + Estacionamento.APP_NAME + File.separator + fileName)) {
            JsonArray jsonClients = (JsonArray)new JsonParser().parse(reader);
            return jsonClients;
        }
    }
    
}
