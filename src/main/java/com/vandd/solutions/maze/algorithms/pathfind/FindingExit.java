package com.vandd.solutions.maze.algorithms.pathfind;

import com.vandd.solutions.maze.GridModel.Grid;
import com.vandd.solutions.maze.GridModel.Tile;
import com.vandd.solutions.maze.GridModel.Painter;
import java.util.List;
public abstract class FindingExit {
    protected Painter painter;

    public static enum Algorithms{
        WavePropagation,
        RightHand
    }
    public FindingExit(){
        this.painter = Painter.getInstance();
    }
    public final int algorithm(Grid model, List<Tile> path)
    {
        int cost = this.runPathfinder(model, path);
        return cost;
    }
    protected abstract int runPathfinder(Grid model, List<Tile> path);
}
