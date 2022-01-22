
package com.vandd.solutions.maze.GridModel;



import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

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
        VISITED_LIGHT,
        VISITED_MEDIUM,
        VISITED_DENSE,
        VISITED_MAX
    }

    private final Map<Type, Color> typeMap;
    private final Map<Integer, Color> weightMap;
    private final Map<Integer, Color> visitedMap;
    private final int[] WEIGHTS = {
            this.getDefaultWeight(), 3, 6, 9, 12};

    private final StackPane adminStackPane;
    private final int x;
    private final int y;
    private final Rectangle rectangle;
    private final int defaultWeight = 1;
    private int weight;
    private Type type;
    private final double tileGap = 0;
    private final int size;


    public Tile(int x, int y, int size,StackPane adminStackPane) {
        this.adminStackPane = adminStackPane;
        // Type color Mapping
        typeMap = new HashMap<>();
        typeMap.put(Type.EXIT, Color.YELLOW);
        typeMap.put(Type.ENTRANCE, Color.PURPLE);
        typeMap.put(Type.EMPTY, Color.WHITE);
        typeMap.put(Type.WALL, Color.BLACK);
        typeMap.put(Type.PATH, Color.DEEPPINK);
        typeMap.put(Type.HIGHLIGHT, Color.RED);
        typeMap.put(Type.VISITED, Color.LIGHTGREEN);

        // Visited Color based on weight
        visitedMap = new HashMap<>();
        visitedMap.put(this.WEIGHTS[0], Color.PALEGREEN);
        visitedMap.put(this.WEIGHTS[1], Color.LIGHTGREEN);
        visitedMap.put(this.WEIGHTS[2], Color.SPRINGGREEN);
        visitedMap.put(this.WEIGHTS[3], Color.GREENYELLOW);
        visitedMap.put(this.WEIGHTS[4], Color.GREEN);

        // Empty Weight color
        weightMap = new HashMap<>();
        weightMap.put(this.WEIGHTS[0], Color.WHITE);
        weightMap.put(this.WEIGHTS[1], Color.LIGHTCYAN);
        weightMap.put(this.WEIGHTS[2], Color.AQUA);
        weightMap.put(this.WEIGHTS[3], Color.DEEPSKYBLUE);
        weightMap.put(this.WEIGHTS[4], Color.CORNFLOWERBLUE);

        // Coordinates
        this.x = x;
        this.y = y;

        // Attributes
        this.weight = defaultWeight;
        this.type = Type.EMPTY;
        this.size = size;
        size = 20;

        // Tile content
        this.rectangle = new Rectangle(size - tileGap, size - tileGap);
        this.rectangle.setFill(Color.WHITE);
        this.setTileStroke(true);

        // build this StackPane
        adminStackPane.getChildren().add(rectangle);
        adminStackPane.setTranslateX(x * size);
        adminStackPane.setTranslateY(y * size);

        setEvents();
    }

    public StackPane getStackPane() {
        return this.adminStackPane;
    }

    public void setTileStroke(boolean setStroke) {
        this.rectangle.setStroke((setStroke) ? Color.LIGHTGRAY : null);
    }

    public void setAttributes(Type type, int weight) {
        Color color;

        // Pick color based on type
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
    public int getWeight()
    {
        return this.weight;
    }
    public int getX()
    {
        return this.x;
    }
    public int getY()
    {
        return this.y;
    }
    public void clearTile()
    {
        this.setAttributes(Type.EMPTY, defaultWeight);
    }
    public int getDefaultWeight()
    {
        return this.defaultWeight;
    }
    public Type getType()
    {
        return this.type;
    }
    public boolean isWall()
    {
        return (this.type == Type.WALL);
    }
    private void setEvents()
    {
        adminStackPane.addEventFilter(MouseEvent.MOUSE_PRESSED, (MouseEvent me) ->
        {
            // Notifies the @Grid
            setChanged();
            notifyObservers();
        });
    }
    @Override
    public String toString()
    {
        return String.format("%s - (%d,%d) W:%d", this.type, this.x, this.y, this.weight);
    }
}


