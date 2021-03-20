package com.company;

import java.awt.*;
import java.io.*;
import java.net.URI;
import java.net.URL;
import java.util.Arrays;

public class CatalogUtil {
    public static void save(Catalog catalog)
            throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(catalog.getPath()))) {
            oos.writeObject(catalog);
        }
    }

    public static Catalog load(String path)
            throws InvalidCatalogException {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(path))) {
            return (Catalog) ois.readObject();
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
            return null;
        } catch (IOException e) {
            System.out.println("Error opening file");
            return null;
        } catch (ClassNotFoundException e) {
            System.err.println("Class not found");
            return null;
        }
    }

    public static void play(Item item) {
        Desktop desktop = Desktop.getDesktop();
        // Try creating a valid URL
        URI uri = makeURI(item.getLocation());
        try {
            if (uri != null) {
                desktop.browse(uri);
            } else {
                File file = new File(item.getLocation());
                desktop.open(file);
            }
        } catch (IOException e) {
            System.out.println("Error playing file!");
        }
    }

    public static void list(Catalog catalog) {
        System.out.println(Arrays.asList(catalog.getItems()));
    }

    private static URI makeURI(String url) {
        // Try creating a valid URL
        try {
            return new URL(url).toURI();
        }

        // If not URI
        catch (Exception e) {
            return null;
        }
    }
}