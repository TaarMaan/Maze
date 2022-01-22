
package com.vandd.solutions.maze.algorithms.generation;


import com.vandd.solutions.maze.GridModel.Grid;
import com.vandd.solutions.maze.GridModel.Tile;
import com.vandd.solutions.maze.GridModel.Painter;
import java.util.List;
import java.util.Random;


public abstract class MazeGeneration {
    protected Random random;
    protected Painter painter;
    protected long painterWait;

    // Maze generation algorithms
    public static enum MazeGen{
        Prim,
        Kruskal
    }

    public MazeGeneration()
    {
        this.painter = Painter.getInstance();
        this.painterWait = 4; //4
    }



    public final void generate(Grid model)
    {
        // template
        model.clearGrid();
        this.setDefaultWalls(model.getGrid(), model.getXSize(), model.getYSize());

        // maze gen algorithm
        this.algorithm(model);
    }


    public abstract void algorithm(Grid model);

    protected void addNeighbors(Grid model, Tile tile, List<Tile> neighbors)
    {
        // Clear neighbors to make sure we work with an empty list
        neighbors.clear();
        // Temporary holder for each neighbor
        Tile temp;

        temp = model.getNorthTile(tile);
        if(temp != null)
            neighbors.add((temp.getY() % 2 != 0) ? model.getGrid()[temp.getX()][temp.getY() - 1] : temp);

        temp = model.getSouthTile(tile);
        if(temp != null)
            neighbors.add((temp.getY() % 2 != 0) ? model.getGrid()[temp.getX()][temp.getY() + 1] : temp);

        temp = model.getWestTile(tile);
        if(temp != null)
            neighbors.add((temp.getX() % 2 != 0) ? model.getGrid()[temp.getX() - 1][temp.getY()] : temp);

        temp = model.getEastTile(tile);
        if(temp != null)
            neighbors.add((temp.getX() % 2 != 0) ? model.getGrid()[temp.getX() + 1][temp.getY()] : temp);
    }

    protected void setDefaultWalls(Tile[][] grid, int x_size, int y_size)
    {
        for(int y = 0; y < y_size; y++)
        {
            for(int x = 0; x < x_size; x++)
            {
                grid[x][y].setAttributes(Tile.Type.WALL, grid[x][y].getDefaultWeight());
            }
        }
    }

    protected void removeWallBetween(Tile[][] grid, Tile a, Tile b)
    {
        int x = a.getX();
        int y = a.getY();

        // Remove wall between currentTile and randomNeighbor
        if     (a.getX() < b.getX()) x += 1;
        else if(a.getY() < b.getY()) y += 1;
        else if(a.getX() > b.getX()) x -= 1;
        else if(a.getY() > b.getY()) y -= 1;

        // This logic is for visualization
        this.painter.highlightTile(grid[x][y], this.painterWait);
    }

    protected int getRandomInt(int max, int min)
    {
        this.random = new Random();
        return ((int) (Math.random()*(max - min))) + min;
    }
}
