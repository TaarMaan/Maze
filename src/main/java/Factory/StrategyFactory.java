
package Factory;

import Strategy.MazeGenerationStrategy.MazeGenerationStrategy;
import Strategy.MazeGenerationStrategy.PrimStrategy;
import Strategy.PathfindingStrategy.PathfindingStrategy;
import Strategy.PathfindingStrategy.WavePropagationStrategy;
//е понял

public class StrategyFactory
{
    // Pathfinding Factory
    public static PathfindingStrategy getPathfindingStrategy(PathfindingStrategy.Algorithms algorithmStrategy, HeuristicStrategy heuristicStrategy)
    {
        switch(algorithmStrategy)
        {

            case WavePropagation:
                return new WavePropagationStrategy();
            default:
                throw new IllegalArgumentException("Pathfinding algorithm not found!");
        }
    }

    // Heuristic for A* Factory
    public static HeuristicStrategy getHeuristicStrategy(AStarStrategy.Heuristic strategy)
    {
        switch(strategy)
        {
            case Pythagoras:
                return new PythagorasStrategy();
            case Manhattan:
                return new ManhattanStrategy();
            case Diagonal:
                return new DiagonalStrategy();
            case Eudclidean:
                return new EuclideanStrategy();
            default:
                throw new IllegalArgumentException("Heuristic strategy not found!");
        }
    }

    // Maze generation Factory
    public static MazeGenerationStrategy getMazeGenStrategy(MazeGenerationStrategy.MazeGen strategy)
    {
        switch(strategy)
        {
            case Prim:
                return new PrimStrategy();

            default:
                throw new IllegalArgumentException("Maze generation algorithm not found!");
        }
    }
}
