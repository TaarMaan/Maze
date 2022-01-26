
package com.vandd.solutions.maze.GridModel;

import com.vandd.solutions.maze.algorithms.pathfind.FindingExit;
import com.vandd.solutions.maze.algorithms.generation.MazeGeneration;
import com.vandd.solutions.maze.template.serialization.LightweightGrid;
import com.vandd.solutions.maze.template.serialization.LightweightTile;
import com.vandd.solutions.maze.algorithms.pathfind.Mouse;

import java.util.*;
import java.util.stream.Collectors;

public class Grid extends Observable implements Observer {
    private String theme = "";
    private int x_size;
    private int y_size;
    private Mouse mouse;
    private Tile[][] grid;
    Tile exit, entrance;
    private Tile.Type clickType;
    private final Painter painter;

    public Tile getExit() {
        return exit;
    }

    public Tile getEntrance() {
        return entrance;
    }

    public Grid() {
        this.exit = null;
        this.entrance = null;
        this.clickType = Tile.Type.ENTRANCE;
        painter = Painter.getInstance();
    }

    public Grid fromLightweight(LightweightGrid lightweightGrid, String theme) {
        this.x_size = lightweightGrid.xSize;
        this.y_size = lightweightGrid.ySize;
        this.grid = new Tile[x_size][y_size];
        lightweightGrid.grid.forEach(t -> {
            Tile tile = new Tile(t.x, t.y, t.size, theme);
            tile.setAttributes(t.type, t.weight);
            tile.addObserver(this);
            this.grid[tile.getX()][tile.getY()] = tile;
        });
        if (lightweightGrid.entrance != null)
            this.entrance = this.grid[lightweightGrid.entrance.getX()][lightweightGrid.entrance.getY()];
        if (lightweightGrid.exit != null)
            this.exit = this.grid[lightweightGrid.exit.getX()][lightweightGrid.exit.getY()];
        return this;
    }

    public boolean executeFinding(FindingExit findingExit) {
        if (entrance == null || exit == null) return false;
        this.painter.clearPath(this);

        List<Tile> path = new ArrayList<>();
        findingExit.algorithm(this, path);
        return true;
    }

    public void generateRandomMaze(MazeGeneration mazeGenerationStrategy) {
        mazeGenerationStrategy.generate(this);
    }

    public void gridInit(int x_tiles, int y_tiles, int tile_size) {
        this.x_size = x_tiles;
        this.y_size = y_tiles;
        this.grid = new Tile[x_tiles][y_tiles];


        for (int y = 0; y < y_tiles; y++) {
            for (int x = 0; x < x_tiles; x++) {
                Tile tile = new Tile(x, y, tile_size, theme);
                tile.addObserver(this);
                grid[x][y] = tile;
            }

        }
    }

    public void setTheme(String theme) {
        this.theme = theme;
        if (grid != null)
            for (int y = 0; y < y_size; y++) {
                for (int x = 0; x < x_size; x++) {
                    grid[x][y].setTheme(theme);
                }
            }
    }

    public void addRandomEntranceExit() {

        Random random = new Random(System.currentTimeMillis());
        List<Tile> temp = new ArrayList<>();

        for (int y = 0; y < y_size; y++) {
            for (int x = 0; x < x_size; x++) {
                if ((y == 0 || y == y_size - 1) && (x > 0 && x < x_size - 1) || (x == 0 || x == x_size - 1) && (y > 0 && y < y_size - 1))
                    temp.add(grid[x][y]);
            }
        }
        System.out.println(temp);

        temp = temp.stream().filter(tile -> !tile.isWall()).collect(Collectors.toList());
        var tile = temp.remove(random.nextInt(temp.size()));
        if (this.entrance != null) entrance.clearTile();
        this.entrance = tile;
        tile.setAttributes(Tile.Type.ENTRANCE, tile.getDefaultWeight());
        tile = temp.get(random.nextInt(temp.size()));
        if (this.exit != null) exit.clearTile();
        this.exit = tile;
        tile.setAttributes(Tile.Type.EXIT, tile.getDefaultWeight());
    }

    public Tile[][] getGrid() {
        return this.grid;
    }

    public boolean isReady() {
        return !(entrance == null || exit == null);
    }

    public void clearGrid() {
        for (int y = 0; y < this.y_size; y++) {
            for (int x = 0; x < this.x_size; x++) {
                grid[x][y].clearTile();
            }
        }
    }

    public void addTileBorders(boolean setBorder) {
        for (int y = 0; y < this.y_size; y++) {
            for (int x = 0; x < this.x_size; x++) {
                grid[x][y].setTileStroke(setBorder);
            }
        }
    }

    public List<Tile> getTileNeighbors(Tile tile) {
        List<Tile> neighbors = new ArrayList<>();
        neighbors.add(this.getNorthTile(tile));
        neighbors.add(this.getSouthTile(tile));
        neighbors.add(this.getEastTile(tile));
        neighbors.add(this.getWestTile(tile));

        return neighbors;
    }


    public Tile getNorthTile(Tile tile) {
        return (tile.getY() - 1 >= 0) ? grid[tile.getX()][tile.getY() - 1] : null;
    }

    public Tile getSouthTile(Tile tile) {
        return (tile.getY() + 1 <= y_size - 1) ? grid[tile.getX()][tile.getY() + 1] : null;
    }

    public Tile getWestTile(Tile tile) {
        return (tile.getX() - 1 >= 0) ? grid[tile.getX() - 1][tile.getY()] : null;
    }

    public Tile getEastTile(Tile tile) {
        return (tile.getX() + 1 <= x_size - 1) ? grid[tile.getX() + 1][tile.getY()] : null;
    }


    public boolean isOnNorth(Tile tile, Tile compare) {
        if (tile.getX() != compare.getX())
            return false;

        return tile.getY() < compare.getY();
    }

    public boolean isOnSouth(Tile tile, Tile compare) {
        if (tile.getX() != compare.getX())
            return false;

        return tile.getY() > compare.getY();
    }

    public boolean isOnWest(Tile tile, Tile compare) {
        if (tile.getY() != compare.getY())
            return false;

        return tile.getX() < compare.getX();
    }

    public boolean isOnEast(Tile tile, Tile compare) {
        if (tile.getY() != compare.getY())
            return false;

        return tile.getX() > compare.getX();
    }


    public int getYSize() {
        return this.y_size;
    }

    public int getXSize() {
        return this.x_size;
    }

    public void changeClickType(Tile.Type type) {
        this.clickType = type;
    }

    public Tile getRoot() {
        return this.entrance;
    }

    public Tile getTarget() {
        return this.exit;
    }

    @Override
    public void update(Observable o, Object arg) {

        if (o instanceof Tile) {
            Tile tile = (Tile) o;


            if (tile.isWall()) {
                if (this.clickType == Tile.Type.EMPTY)
                    tile.setAttributes(clickType, tile.getDefaultWeight());
                return;
            }


            switch (this.clickType) {

                case ENTRANCE:
                case EXIT:

                    if (clickType == Tile.Type.ENTRANCE) {
                        if (this.entrance != null) entrance.clearTile();
                        this.entrance = tile;
                    } else if (clickType == Tile.Type.EXIT) {
                        if (this.exit != null) exit.clearTile();
                        this.exit = tile;
                    }

                    tile.setAttributes(clickType, tile.getDefaultWeight());

                    break;
                default:
                    tile.setAttributes(clickType, tile.getWeight());
                    break;
            }
        }


    }

    public LightweightGrid toLightweight() {

        List<LightweightTile> lightweightTiles = new ArrayList<>();
        for (int y = 0; y < y_size; y++) {
            for (int x = 0; x < x_size; x++) {
                lightweightTiles.add(grid[x][y].toLightweight());
            }
        }
        return new LightweightGrid(x_size, y_size, lightweightTiles, exit != null ? exit.toLightweight() : null, entrance != null ? entrance.toLightweight() : null);
    }

    public void setMouse(Mouse mouse) {
        this.mouse = mouse;
    }
}

