package com.company;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class LoadCommand implements Command {

    Catalog catalog = new Catalog();

    public Catalog getCatalog() {
        return catalog;
    }

    @Override
    public void execute(Object parameter) {
        String path = (String) parameter;
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(path))) {
            catalog = (Catalog) ois.readObject();
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
            catalog = null;
        } catch (IOException e) {
            System.out.println("Error opening file");
            catalog = null;
        } catch (ClassNotFoundException e) {
            System.err.println("Class not found");
            catalog = null;
        }
    }
}
