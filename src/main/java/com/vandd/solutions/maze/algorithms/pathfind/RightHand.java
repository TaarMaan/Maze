
package com.vandd.solutions.maze.algorithms.pathfind;

import com.vandd.solutions.maze.GridModel.Grid;
import com.vandd.solutions.maze.GridModel.Tile;

import java.util.*;

public class RightHand extends FindingExit {
    private final int DEFAULT_DISTANCE = 1;
    public RightHand() {
        super();
    }

    @Override
    protected int runPathfinder(int sleepDuration, Grid model, List<Tile> path) {
        HashMap<Tile, Integer> tileData = new HashMap<>();
        this.executeRightHand(sleepDuration, model, tileData);
        this.buildPath(path, model, tileData);
        int cost = tileData.get(model.getTarget()) - 1;
        this.painter.drawPath(sleepDuration, path, model);
        return cost;
    }

    private void executeRightHand(int sleepDuration, Grid model, HashMap<Tile, Integer> tileData) {
        Set<Tile> visited = new HashSet<>();
        List<Tile> toProcess = new LinkedList<>();
        List<Tile> unprocessed = new LinkedList<>();
        int iteration = 0;

        unprocessed.add(model.getRoot());
        visited.add(model.getRoot());
        tileData.put(model.getRoot(), this.DEFAULT_DISTANCE);
        Tile currentTile = model.getRoot();
        while (!unprocessed.isEmpty()) {
            while (!(currentTile == model.getTarget()))
                break;
            /*  if (isRightWall) {
            //                if (isFrontWall) {
            //                    mouse.leftRotate();
            //                }
            //                else {
            //                    mouse.toStep();
            //                }
            //            }
            //            else {
            //                mouse.rightRotate();
            //                mouse.toStep();
            //            }
            //        }*/
            iteration++;
            toProcess.addAll(unprocessed);
            unprocessed.clear();

            for (Tile tile : toProcess) {
                List<Tile> neighbors = model.getTileNeighbors(tile);

                for (Tile elem : neighbors) {
                    if (elem == null) continue;
                    if (elem.isWall()) continue;
                    if (visited.contains(elem)) continue;

                    unprocessed.add(elem);
                    visited.add(elem);
                    tileData.put(elem, this.DEFAULT_DISTANCE + iteration);
                    this.painter.drawTile(elem, model.getTarget(), model.getRoot(), Tile.Type.HIGHLIGHT, sleepDuration);
                    this.painter.drawTile(elem, model.getTarget(), model.getRoot(), Tile.Type.VISITED, sleepDuration);

                }
            }
        }
    }

    private void buildPath(List<Tile> path, Grid model, HashMap<Tile, Integer> data) {
        Tile currentTile = model.getTarget();

        do {
            path.add(currentTile);
            Tile lowestCost = null;

            for (Tile tile : model.getTileNeighbors(currentTile)) {
                if (tile == null) continue;
                if (tile.isWall()) continue;

                if (lowestCost == null) {
                    lowestCost = tile;
                    continue;
                }

                if (data.get(tile) < data.get(lowestCost))
                    lowestCost = tile;
            }
            currentTile = lowestCost;
        } while (currentTile != model.getRoot());

        Collections.reverse(path);
    }


}
