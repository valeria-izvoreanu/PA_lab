package com.company;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class ReportCommand implements Command {

    @Override
    public void execute(Object parameter) {

        Catalog catalog = (Catalog) parameter;

        //Freemarker configuration object
        Configuration cfg = new Configuration();
        try {
            //Load template from source folder
            Template template = cfg.getTemplate("src/myTemplate.ftl");

            // Build the data-model
            Map<String, Object> data = new HashMap<>();
            data.put("name", catalog.getName());

            //List parsing

            data.put("items", catalog.getItems());

            // File output
            Writer file = new FileWriter("d:/0Sem_2/PA/MyCatalog.html");
            template.process(data, file);
            file.flush();
            file.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }
}
