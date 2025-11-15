/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package skillforge;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lenovo
 */
public abstract class JsonDatabaseManager<T> {
    protected final String filePath;
    protected final Gson gson;
    protected final Type listType;
    
public JsonDatabaseManager(String filePath, Type elementType) {
    this.filePath = filePath;
    this.gson = new GsonBuilder().setPrettyPrinting().create();
    this.listType = TypeToken.getParameterized(List.class, elementType).getType();
    ensureFileExists();
}
public void ensureFileExists() {
    File file = new File(filePath);
    if (!file.exists()) {
        throw new RuntimeException("Error: JSON file does not exist: " + filePath);
    }
}
public List<T> read() {
    try (Reader reader = new FileReader(filePath)) {
        List<T> list = gson.fromJson(reader, listType);

        if (list != null) {
            return list;
        }
        else{
            return new ArrayList<>();
        }
    } catch (IOException e) {
        System.out.println("Error reading file: " + e.getMessage());
        return new ArrayList<>();
    }
}

    public boolean save(List<T> items) {
        try (Writer writer = new FileWriter(filePath)) {
            gson.toJson(items, listType, writer);
            return true;
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
            return false;
        }
    }
    
    public boolean add(T item){
        List<T> items = read();
        items.add(item);
        return save(items);
    }
    
    public T find(int id){
        for(i=0;i<items.size();i++){
            if(getID(item) == id){
                return item;
            }
            return null;
        }
    }
    
    public boolean update(int id, T newItem){
        List<T> items = read();
        for(i=0;i<items.size();i++){
            if(getID(items.get(i)) == id){
                items.set(i, newItem);
                return save(items);
            }
            return null;
        }
    }

}
