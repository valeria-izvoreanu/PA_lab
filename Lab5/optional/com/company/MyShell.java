package com.company;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

//D:/0Sem_2/PA/Lab/Lab5/media/
@ShellComponent
public class MyShell {

    @ShellMethod("Add new item.")
    public void add(String pathCatalog, String nameItem, String typeItem, String pathItem) {
        try {
            Command command = new LoadCommand();
            command.execute(pathCatalog);
            LoadCommand loadCommand = (LoadCommand) command;
            Catalog catalog = loadCommand.getCatalog();

            command = new AddCommand(catalog);
            if (typeItem.equals("Song")) {
                Song item = new Song(nameItem, pathItem);
                command.execute(item);
            } else if (typeItem.equals("Book")) {
                Book item = new Book(nameItem, pathItem);
                command.execute(item);
            } else {
                throw new InvalidCommandException("item type doesnt exist");
            }
            command = new SaveCommand();
            command.execute(catalog);
        } catch (InvalidArgumentException e) {
            System.out.println(e);
        } catch (InvalidCommandException e) {
            System.out.println(e);
        }
    }

    @ShellMethod("List catalog.")
    public void list(String pathCatalog) {
        Command command = new LoadCommand();
        command.execute(pathCatalog);
        LoadCommand loadCommand = (LoadCommand) command;
        Catalog catalog = loadCommand.getCatalog();

        command = new ListCommand();
        command.execute(catalog);
    }

    @ShellMethod("Play Item.")
    public void play(String pathCatalog, String nameItem) {
        try {
            Command command = new LoadCommand();
            command.execute(pathCatalog);
            LoadCommand loadCommand = (LoadCommand) command;
            Catalog catalog = loadCommand.getCatalog();

            if (catalog.findById(nameItem) != null) {
                command = new PlayCommand();
                command.execute(catalog.findById(nameItem));
            } else {
                throw new InvalidCommandException("item doesn't exist");
            }
        } catch (InvalidCommandException e) {
            System.out.println(e);
        }
    }

    @ShellMethod("Create report.")
    public void report(String pathCatalog) {
        Command command = new LoadCommand();
        command.execute(pathCatalog);
        LoadCommand loadCommand = (LoadCommand) command;
        Catalog catalog = loadCommand.getCatalog();

        command = new ReportCommand();
        command.execute(catalog);
    }
}
