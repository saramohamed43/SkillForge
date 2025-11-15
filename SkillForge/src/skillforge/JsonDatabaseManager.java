/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package skillforge;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.io.*;
import java.util.*;

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
            try (Writer writer = new FileWriter(file)) {
                writer.write("[]");
            } catch (IOException e) {
                throw new RuntimeException("Error creating JSON file: " + e.getMessage());
            }
        }
    }
    
    public List<T> read() {
        try (Reader reader = new FileReader(filePath)) {
            List<T> list = gson.fromJson(reader, listType);
            if (list != null) {
                return list;
            } else {
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
    
    public boolean add(T item) {
        List<T> items = read();
        if (isDuplicate(item, items)) {
            System.err.println("Duplicate entry detected!");
            return false;
        }
        items.add(item);
        return save(items);
    }
    
    public T getUserById(String id) {
        List<T> items = read();
        for (int i = 0; i < items.size(); i++) {
            T item = items.get(i);
            if (getId(item).equals(id)) {
                return item;
            }
        }
        return null;
    }
    
    public T getUserByEmail(String email) {
    List<T> items = read();
    for (T item : items) {
        if (getEmail(item).equalsIgnoreCase(email)) {
            return item;
        }
    }
    return null;
}
    
    // CHANGED: int → String
    public boolean update(String id, T newItem) {
        List<T> items = read();
        for (int i = 0; i < items.size(); i++) {
            if (getId(items.get(i)).equals(id)) {
                items.set(i, newItem);
                return save(items);
            }
        }
        System.err.println("Item not found for update");
        return false;
    }
    
    // CHANGED: int → String
    public boolean deleteById(String id) {
        List<T> items = read();
        for (int i = 0; i < items.size(); i++) {
            if (getId(items.get(i)).equals(id)) {
                items.remove(i);
                return save(items);
            }
        }
        System.err.println("Item not found for deletion");
        return false;
    }
    
    public List<T> getAll() {
        return read();
    }
    
    public abstract String getId(T item);
    
    public abstract String getEmail(T item);
    
    protected abstract boolean isDuplicate(T item, List<T> existingItems);
}