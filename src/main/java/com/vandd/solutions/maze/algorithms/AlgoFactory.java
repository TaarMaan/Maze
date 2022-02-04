package com.vandd.solutions.maze.algorithms;

import com.vandd.solutions.maze.algorithms.generation.Kruskal;
import com.vandd.solutions.maze.algorithms.generation.MazeGeneration;
import com.vandd.solutions.maze.algorithms.generation.Prim;
import com.vandd.solutions.maze.algorithms.pathfind.FindingExit;
import com.vandd.solutions.maze.algorithms.pathfind.RightHand;
import com.vandd.solutions.maze.algorithms.pathfind.WavePropagation;

public class AlgoFactory {
    // Pathfinding com.vandd.solutions.maze.Factory
    public static FindingExit getFindingExit(FindingExit.Algorithms algorithmStrategy) {
        return switch (algorithmStrategy) {
            case WavePropagation -> new WavePropagation();
            case RightHand -> new RightHand();
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
}

