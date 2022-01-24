package com.vandd.solutions.maze.template.serialization;

import java.util.List;

public class LightweightGrid {
    public int xSize;
    public int ySize;
    public List<LightweightTile> grid;
    public LightweightTile exit, entrance;
    public int tileSize;

    public LightweightGrid() {
    }

    public LightweightGrid(int xSize, int y_size, List<LightweightTile> grid, LightweightTile exit, LightweightTile entrance) {
        this.xSize = xSize;
        this.ySize = y_size;
        this.grid = grid;
        this.exit = exit;
        this.entrance = entrance;
    }

    public int getxSize() {
        return xSize;
    }

    public void setxSize(int xSize) {
        this.xSize = xSize;
    }

    public int getySize() {
        return ySize;
    }

    public void setySize(int ySize) {
        this.ySize = ySize;
    }

    public List<LightweightTile> getGrid() {
        return grid;
    }

    public void setGrid(List<LightweightTile> grid) {
        this.grid = grid;
    }

    public LightweightTile getExit() {
        return exit;
    }

    public void setExit(LightweightTile exit) {
        this.exit = exit;
    }

    public LightweightTile getEntrance() {
        return entrance;
    }

    public void setEntrance(LightweightTile entrance) {
        this.entrance = entrance;
    }

    public int getTileSize() {
        return tileSize;
    }

    public void setTileSize(int tileSize) {
        this.tileSize = tileSize;
    }
}
