package com.vandd.solutions.maze.template;

import com.vandd.solutions.maze.GridModel.Grid;
import com.vandd.solutions.maze.template.serialization.LightweightGrid;

public class Template {

    private String templateId;
    private String name;
    private LightweightGrid grid;
    private String theme;

    public Template(String name, Grid grid, String theme) {
        this.name = name;
        this.grid = grid.toLightweight();
        this.theme = theme;
    }

    public LightweightGrid getGrid() {
        return grid;
    }

    public void setGrid(LightweightGrid grid) {
        this.grid = grid;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }
}
