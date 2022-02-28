
package com.vandd.solutions.maze.algorithms;

import com.vandd.solutions.maze.algorithms.generation.Kruskal;
import com.vandd.solutions.maze.algorithms.generation.MazeGeneration;
import com.vandd.solutions.maze.algorithms.generation.Prim;
import com.vandd.solutions.maze.algorithms.pathfind.*;

public class AlgoFactory {
    public static FindingExit getFindingExit(FindingExit.Algorithms algorithmStrategy,HeuristicStrategy heuristicStrategy) {
        return switch (algorithmStrategy) {
            case WavePropagation -> new WavePropagation();
            case AStar -> new AStar(heuristicStrategy);
            default -> throw new IllegalArgumentException("Pathfinding algorithm not found!");
        };
    }

    // Maze generation com.vandd.solutions.maze.Factory
    public static MazeGeneration getMazeGenStrategy(MazeGeneration.MazeGen strategy) {
        switch (strategy) {
            case Prim:
                return new Prim();
            case Kruskal:
                return new Kruskal();
            default:
                throw new IllegalArgumentException("Maze generation algorithm not found!");
        }
    }
   public static HeuristicStrategy getHeuristicStrategy(AStar.Heuristic strategy)
    {
        switch(strategy)
        {
            case Manhattan:
                return new ManhattanStrategy();
            default:
                throw new IllegalArgumentException("Heuristic strategy not found!");
        }
    }
}


