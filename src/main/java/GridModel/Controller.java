
package GridModel;

import Algorithms.FindingExit;
import Algorithms.MazeGeneration;
import Factory.StrategyFactory;
import appandcontrollers.maze.AdminController;

public class Controller{
    private final Grid model;
    private final AdminController adminController;

    public Controller(Grid model, AdminController adminController ) {

this.model = model;
        this.adminController = adminController;
        this.adminController.setTriggers(this);
        this.adminController.createGrid();
        this.model.addObserver(adminController);
    }

    public void doChangeClickType(Tile.Type type) {
        this.model.changeClickType(type);
    }

    public void doGenerateMaze(MazeGeneration.MazeGen strategy) {
        MazeGeneration mazeGenerationStrategy = StrategyFactory.getMazeGenStrategy(strategy);
        this.model.generateRandomMaze(mazeGenerationStrategy);
    }

    public boolean doShortestPathAlgorithm(FindingExit.Algorithms algorithm) throws InterruptedException
    {
        FindingExit fExit = StrategyFactory.getFindingExit(algorithm);
        return this.model.executeFinding(fExit);
    }
}