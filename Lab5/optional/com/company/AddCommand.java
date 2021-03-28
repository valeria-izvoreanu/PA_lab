package com.company;

public class AddCommand implements Command {

    Catalog catalog;

    AddCommand(Catalog catalog) {
        this.catalog = catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    @Override
    public void execute(Object parameter) {
        Item item = (Item) parameter;
        catalog.getItems().add(item);
    }

}
