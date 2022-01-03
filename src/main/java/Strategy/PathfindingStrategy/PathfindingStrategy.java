package Strategy.PathfindingStrategy;

import Model.Grid;
import Model.Tile;
import Util.Painter;

import java.util.List;

public abstract class PathfindingStrategy 
{
    protected Painter painter;
    
    // Pathfinding algorithms
    public static enum Algorithms{
        WavePropagation
    }
    
    public PathfindingStrategy()
    {
        this.painter = Painter.getInstance();
    }

    public final int algorithm(Grid model, List<Tile> path)
    {
        // Runs pathfinding algorithm
        int cost = this.runPathfinder(model, path);
        this.painter.drawPath(path, model);
        return cost;
    }

    protected abstract int runPathfinder(Grid model, List<Tile> path);
}