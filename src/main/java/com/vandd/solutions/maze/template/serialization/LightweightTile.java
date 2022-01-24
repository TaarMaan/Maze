package com.vandd.solutions.maze.template.serialization;

import com.vandd.solutions.maze.GridModel.Tile;

public class LightweightTile {
    public int x;
    public int y;
    public Tile.Type type;
    public int size;
    public int weight;

    public LightweightTile(int x, int y, Tile.Type type, int size, int weight) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.size = size;
        this.weight = weight;
    }

    public LightweightTile() {
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Tile.Type getType() {
        return type;
    }

    public void setType(Tile.Type type) {
        this.type = type;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
