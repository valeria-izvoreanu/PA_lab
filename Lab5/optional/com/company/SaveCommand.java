package com.company;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SaveCommand implements Command{

    @Override
    public void execute(Object parameter)
    {
        Catalog catalog = (Catalog) parameter;
        try {
            ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(catalog.getPath()));
            oos.writeObject(catalog);
        } catch (IOException e){
            System.out.println("I/O Error");
        }
    }
}
