package com.vandd.solutions.maze.algorithms.pathfind;

import com.vandd.solutions.maze.GridModel.Grid;
import com.vandd.solutions.maze.GridModel.Tile;

import java.util.List;

public class RightHand extends FindingExit {
    public RightHand()
    {
        super();
    }

    @Override
    protected int runPathfinder(Grid model, List<Tile> path) {
        return 0;
    }
}
