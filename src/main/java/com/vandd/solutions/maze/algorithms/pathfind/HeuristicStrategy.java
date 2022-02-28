package com.vandd.solutions.maze.algorithms.pathfind;

import com.vandd.solutions.maze.GridModel.Tile;

public abstract class HeuristicStrategy
{
    public HeuristicStrategy()
    {
    }
    public abstract double computeHeuristic(Tile root, Tile target);
}
