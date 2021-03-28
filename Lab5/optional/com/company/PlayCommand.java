package com.company;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;

public class PlayCommand implements Command {
    @Override
    public void execute(Object parameter) {
        Desktop desktop = Desktop.getDesktop();
        // Try creating a valid URL
        Item item = (Item) parameter;
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

    private URI makeURI(String url) {
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
