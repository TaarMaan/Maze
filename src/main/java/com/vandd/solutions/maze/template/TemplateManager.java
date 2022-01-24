package com.vandd.solutions.maze.template;

import com.google.gson.Gson;

import java.io.*;
import java.util.*;

public class TemplateManager {

    //название папки
    private static final String TEMPLATES_PATH = "maze_templates";
    private boolean isLoaded;
    private final Gson gson = new Gson();
    private final Random random = new Random(System.currentTimeMillis());

    private final Map<String, Template> templates = new HashMap<>();

    public void save(Template template) {
        String templateId = System.currentTimeMillis() + "_" + random.nextInt();
        String filename = TEMPLATES_PATH + "\\" + "tmpl" + "_" + templateId + ".json";
        template.setTemplateId(templateId);
        String jsonString = gson.toJson(template);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(jsonString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Template load(String templateId) {
        preLoadAllTemplates();
        return templates.get(templateId);
    }

    public List<Template> allTemplates() {
        preLoadAllTemplates();
        return templates.values().stream().toList();
    }

    private void preLoadAllTemplates() {
        if (!isLoaded) {
            File folder = new File(TEMPLATES_PATH);
            for (final File fileEntry : Objects.requireNonNull(folder.listFiles())) {
                String filename = TEMPLATES_PATH + "\\" + fileEntry.getName();
                Template template = loadFromFile(filename);
                templates.put(template.getTemplateId(), template);
            }
            isLoaded = true;
        }
    }

    private Template loadFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            return gson.fromJson(reader, Template.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
