package com.vandd.solutions.maze.algorithms.pathfind;

import com.vandd.solutions.maze.GridModel.Grid;
import com.vandd.solutions.maze.GridModel.Tile;
import com.vandd.solutions.maze.GridModel.Painter;

import java.util.List;

public abstract class FindingExit {
    protected Painter painter;

    public static enum Algorithms {
        WavePropagation,
        AStar
    }
    public FindingExit() {
        this.painter = Painter.getInstance();
    }

    public final int algorithm(int sleepDuration, Grid model, List<Tile> path) {
        int cost = this.runPathfinder(sleepDuration, model, path);
        return cost;
    }

    protected abstract int runPathfinder(int sleepDuration, Grid model, List<Tile> path);
}
