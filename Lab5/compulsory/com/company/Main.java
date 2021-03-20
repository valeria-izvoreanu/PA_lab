package com.company;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Main app = new Main();
        app.testCreateSave();
        app.testLoadView();
    }

    private void testCreateSave() {
        try {
            Catalog catalog =
                    new Catalog("My Books", "D:/0Sem_2/PA/Lab/Lab5/media/catalog.ser");
            Song song = new Song("bestSong", "D:/0Sem_2/PA/Lab/Lab5/media/shiny.mp3");
            Book book = new Book("bestBook", "D:/0Sem_2/PA/Lab/Lab5/media/mybook.txt");
            Book bookA = new Book("curs", "https://profs.info.uaic.ro/~acf/java/slides/en/exceptions_slide_en.pdf");
            catalog.add(song);
            catalog.add(book);
            catalog.add(bookA);
            CatalogUtil.save(catalog);
            CatalogUtil.list(catalog);
        } catch (InvalidArgumentException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println("I/O Error");
        }
    }

    private void testLoadView() {
        Catalog catalog = new Catalog();
        try {
            catalog = CatalogUtil.load("D:/0Sem_2/PA/Lab/Lab5/media/catalog.ser");
        } catch (InvalidCatalogException e) {
            System.err.println();
        }
        CatalogUtil.play(catalog.findById("bestSong"));
        CatalogUtil.play(catalog.findById("curs"));
    }
}
