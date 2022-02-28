package com.vandd.solutions.maze.algorithms.pathfind;
import com.vandd.solutions.maze.GridModel.Tile;
public class ManhattanStrategy extends HeuristicStrategy
{
    public ManhattanStrategy()
    {
        super();
    }
    @Override
    public double computeHeuristic(Tile root, Tile target)
    {
        double D = 1.0;
        double dx = Math.abs(root.getX() - target.getX());
        double dy = Math.abs(root.getY() - target.getY());
        return D * (dx + dy);
    }
}