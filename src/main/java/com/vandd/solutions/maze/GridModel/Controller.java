
package com.vandd.solutions.maze.GridModel;

import com.vandd.solutions.maze.algorithms.pathfind.FindingExit;
import com.vandd.solutions.maze.algorithms.generation.MazeGeneration;
import com.vandd.solutions.maze.algorithms.AlgoFactory;
import com.vandd.solutions.maze.AdminController;

public class Controller {
    private final Grid model;
    private final AdminController adminController;

    public Controller(Grid model, AdminController adminController) {

        this.model = model;
        this.adminController = adminController;
    }

    public void doChangeClickType(Tile.Type type) {
        this.model.changeClickType(type);
    }

    public void doGenerateMaze(MazeGeneration.MazeGen strategy) {
        MazeGeneration mazeGenerationStrategy = AlgoFactory.getMazeGenStrategy(strategy);
        this.model.generateRandomMaze(mazeGenerationStrategy);
    }

    public boolean doShortestPathAlgorithm(FindingExit.Algorithms algorithm) throws InterruptedException {
        FindingExit fExit = AlgoFactory.getFindingExit(algorithm);
        return this.model.executeFinding(fExit);
    }
}