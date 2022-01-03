/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Strategy.MazeGenerationStrategy.MazeGenerationStrategy;
import Strategy.PathfindingStrategy.PathfindingStrategy;
import Util.Painter;

import java.util.*;

public class Grid extends Observable implements Observer {
    // Dimensions
    private int x_size;
    private int y_size;

    // Grid
    private Tile[][] grid;
    Tile root, target;

    // What change will happen to the tile on click
    private Tile.Type clickType;

    // Attributes
    private final Painter painter;

    public Grid() {
        this.root = null;
        this.target = null;
        this.clickType = Tile.Type.ROOT;
        painter = Painter.getInstance();
    }

    public boolean executePathfinding(PathfindingStrategy pathfindingStrategy) throws InterruptedException {
        if (root == null || target == null) return false;
        this.painter.clearPath(this);

        List<Tile> path = new ArrayList<>();
        // непонял
        pathfindingStrategy.algorithm(this, path);

        return true;
    }

    public void gridInit(int x_tiles, int y_tiles, int tile_size) {
        this.x_size = x_tiles;
        this.y_size = y_tiles;
        this.grid = new Tile[x_tiles][y_tiles];

        // Initializes all tiles
        for (int y = 0; y < y_tiles; y++) {
            for (int x = 0; x < x_tiles; x++) {
                Tile tile = new Tile(x, y, tile_size);
                tile.addObserver(this);
                grid[x][y] = tile;
            }
        }
    }
//не понял
    public void toggleCoords(boolean toAdd) {
        for (int y = 0; y < this.y_size; y++) {
            for (int x = 0; x < this.x_size; x++) {
                grid[x][y].toggleCoords(toAdd);
            }
        }
    }

    public Tile[][] getGrid() {
        return this.grid;
    }
//не  паонял
    public boolean isReady() {
        return !(root == null || target == null);
    }

    public List<Tile> getTiles() {
        List<Tile> tiles = new ArrayList<>();

        for (int y = 0; y < this.y_size; y++) {
            for (int x = 0; x < this.x_size; x++) {
                tiles.add(grid[x][y]);
            }
        }

        return tiles;
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

    public int getWallsAmount() {
        int totalWalls = 0;

        for (int y = 0; y < this.y_size; y++) {
            for (int x = 0; x < this.x_size; x++) {
                if (grid[x][y].isWall()) totalWalls++;
            }
        }

        return totalWalls;
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
        return this.root;
    }

    public Tile getTarget() {
        return this.target;
    }


    public void addRandomWeights() {
        this.clearGrid();

        for (int y = 0; y < this.y_size; y++) {
            for (int x = 0; x < this.x_size; x++) {
                grid[x][y].randomizeWeight();
            }
        }
    }


    public void addRandomWalls() {
        Random random = new Random();
        Tile tile;

        for (int y = 0; y < this.y_size; y++) {
            for (int x = 0; x < this.x_size; x++) {
                tile = grid[x][y];
                if (tile.getType() == Tile.Type.WALL)
                    tile.setAttributes(Tile.Type.EMPTY, tile.getWeight());

                if ((random.nextInt(2 - 0 + 1) + 0) == 1)
                    tile.setAttributes(Tile.Type.WALL, tile.getWeight());
            }
        }
    }


    public void generateRandomMaze(MazeGenerationStrategy mazeGenerationStrategy) {
        mazeGenerationStrategy.generate(this);
    }
//не понял
    @Override
    public void update(Observable o, Object arg) {
        // If the update came from a Tile Object
        if (o instanceof Tile) {
            Tile tile = (Tile) o;

            // User can't override a wall, unless he's setting it as empty first.
            if (tile.isWall()) {
                if (this.clickType == Tile.Type.EMPTY)
                    tile.setAttributes(clickType, tile.getDefaultWeight());
                return;
            }

            // What happens when you click a tile with a specific clickType (I.e. root, target, wall...)
            switch (this.clickType) {
                // Tiles that  can only have one ocurrence throughout the grid
                case ROOT:
                case TARGET:

                    // Clear old tiles
                    if (clickType == Tile.Type.ROOT) {
                        if (this.root != null) root.clearTile();
                        this.root = tile;
                    } else if (clickType == Tile.Type.TARGET) {
                        if (this.target != null) target.clearTile();
                        this.target = tile;
                    }

                    tile.setAttributes(clickType, tile.getDefaultWeight());

                    break;
                // Tiles that allow multiple ocurrences of themselfs
                default:
                    tile.setAttributes(clickType, tile.getWeight());
                    break;
            }
        }
    }
}
