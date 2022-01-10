package ViewController;

import Algorithms.FindingExit;
import Algorithms.MazeGeneration;
import Factory.StrategyFactory;
import GridModel.Grid;
import GridModel.Tile;

public class Controller {
    private final Grid model;
    private final View view;

    public Controller(Grid model, View view) {
        this.model = model;
        this.view = view;
        this.view.setTriggers(this);
        this.view.createGrid();

        this.model.addObserver(view);
    }

    public void doClearGrid() {
        this.model.clearGrid();
    }

    public void doChangeClickType(Tile.Type type) {
        this.model.changeClickType(type);
    }

    public void doAddRandomWeights() {
        this.model.addRandomWeights();
    }

    public void doAddRandomWalls() {
        this.model.addRandomWalls();
    }

    public void doToggleTileBorder(boolean setBorder) {
        this.model.addTileBorders(setBorder);
    }

    public void doGenerateMaze(MazeGeneration.MazeGen strategy) {
        MazeGeneration mazeGenerationStrategy = StrategyFactory.getMazeGenStrategy(strategy);
        this.model.generateRandomMaze(mazeGenerationStrategy);
    }

    public void doToggleTileCoords(boolean toAdd) {
        this.model.toggleCoords(toAdd);
    }
    public boolean doShortestPathAlgorithm(FindingExit.Algorithms algorithm) throws InterruptedException
    {
        FindingExit fExit = StrategyFactory.getFindingExit(algorithm);
        return this.model.executeFinding(fExit);
    }
}

