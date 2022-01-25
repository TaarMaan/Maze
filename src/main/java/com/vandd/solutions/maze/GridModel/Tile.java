
package com.vandd.solutions.maze.GridModel;


import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

import com.vandd.solutions.maze.template.serialization.LightweightTile;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;


public class Tile extends Observable {

    public static enum Type {
        EXIT,
        ENTRANCE,
        EMPTY,
        PATH,
        WALL,
        HIGHLIGHT,
        VISITED,
    }

    private final Map<Type, Color> typeMap;
    private final Map<Integer, Color> weightMap;
    private final Map<Integer, Color> visitedMap;
    private final int[] WEIGHTS = {
            this.getDefaultWeight(), 3, 6, 9, 12};

    private final StackPane pane;
    private final int x;
    private final int y;
    private final Rectangle rectangle;
    private final int defaultWeight = 1;
    private int weight;
    private Type type;
    private final double tileGap = 0;
    private final int size;

    public Tile(int x, int y, int size, String theme) {

        pane = new StackPane();
        typeMap = new HashMap<>();
        typeMap.put(Type.EXIT, Color.BLACK);
        typeMap.put(Type.ENTRANCE, Color.YELLOW);
        typeMap.put(Type.EMPTY, Color.WHITE);
        typeMap.put(Type.WALL, Color.BLACK);
        typeMap.put(Type.PATH, Color.DARKTURQUOISE);
        typeMap.put(Type.HIGHLIGHT, Color.RED);
        typeMap.put(Type.VISITED, Color.BLUEVIOLET);

        if (theme.equals("autumn"))
            typeMap.put(Type.WALL, Color.SADDLEBROWN);
        if (theme.equals("spring"))
            typeMap.put(Type.WALL, Color.GREEN);
        if (theme.equals("summer"))
            typeMap.put(Type.WALL, Color.DARKRED);
        if (theme.equals("winter"))
            typeMap.put(Type.WALL, Color.DARKBLUE);

        visitedMap = new HashMap<>();
        visitedMap.put(this.WEIGHTS[0], Color.MOCCASIN);


        weightMap = new HashMap<>();
        weightMap.put(this.WEIGHTS[0], Color.TRANSPARENT);

        this.x = x;
        this.y = y;

        this.weight = defaultWeight;
        this.type = Type.EMPTY;
        this.size = size;

        this.rectangle = new Rectangle(size - tileGap, size - tileGap);
        this.rectangle.setFill(Color.TRANSPARENT);
        this.setTileStroke(true);

        pane.getChildren().add(rectangle);
        pane.getStyleClass().add("cell");
        pane.setTranslateX(x * size);
        pane.setTranslateY(y * size);

        setEvents();
    }

    public StackPane getStackPane() {
        return this.pane;
    }

    public int getSize() {
        return size;
    }

    public void setTileStroke(boolean setStroke) {
        this.rectangle.setStroke((setStroke) ? Color.LIGHTGRAY : null);
    }

    public void setAttributes(Type type, int weight) {
        Color color;

        switch (type) {
            case VISITED:
                color = this.visitedMap.get(this.getWeight());
                break;
            case EMPTY:
                color = this.weightMap.get(weight);
                break;
            default:
                color = this.typeMap.get(type);
                break;
        }

        this.rectangle.setFill(color);
        this.type = type;
        this.weight = weight;
    }

    public int getWeight() {
        return this.weight;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void clearTile() {
        this.setAttributes(Type.EMPTY, defaultWeight);
    }

    public int getDefaultWeight() {
        return this.defaultWeight;
    }

    public Type getType() {
        return this.type;
    }

    public boolean isWall() {
        return (this.type == Type.WALL);
    }

    private void setEvents() {
        pane.addEventFilter(MouseEvent.MOUSE_PRESSED, (MouseEvent me) ->
        {
            setChanged();
            notifyObservers();
        });
    }

    public LightweightTile toLightweight() {
        return new LightweightTile(this.getX(), this.getY(), this.getType(), this.getSize(), this.getWeight());
    }

    public void setTheme(String theme) {
        if (theme.equals("autumn"))
            typeMap.put(Type.WALL, Color.SADDLEBROWN);
        if (theme.equals("spring"))
            typeMap.put(Type.WALL, Color.GREEN);
        if (theme.equals("summer"))
            typeMap.put(Type.WALL, Color.DARKRED);
        if (theme.equals("winter"))
            typeMap.put(Type.WALL, Color.DARKBLUE);
        setAttributes(type, weight);
    }

    @Override
    public String toString() {
        return String.format("%s - (%d,%d) W:%d", this.type, this.x, this.y, this.weight);
    }
}


