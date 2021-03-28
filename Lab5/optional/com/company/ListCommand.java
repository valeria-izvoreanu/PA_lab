package com.company;

import java.util.Arrays;

public class ListCommand implements Command {

    @Override
    public void execute(Object parameter) {
        Catalog catalog = (Catalog) parameter;
        System.out.println(Arrays.asList(catalog.getItems()));
    }
}
