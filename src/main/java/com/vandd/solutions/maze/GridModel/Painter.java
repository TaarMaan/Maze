package com.vandd.solutions.maze.GridModel;

import com.vandd.solutions.maze.algorithms.pathfind.FindingExit;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class Painter {
    private static final Painter INSTANCE = new Painter();
    private final Executor executor;

    private Painter() {
        executor = Executors.newSingleThreadExecutor();
    }

    public static Painter getInstance() {
        return INSTANCE;
    }

    public void drawPath(int sleepDuration, List<Tile> path, Grid model) {
        this.executor.execute(
                () ->
                {
                    path.stream().filter((tile) -> !(tile == model.getTarget() || tile == model.getRoot())).map((tile) ->
                    {
                        tile.setAttributes(Tile.Type.PATH, tile.getWeight());
                        return tile;
                    }).forEachOrdered((_item) ->
                    {
                        try {
                            if (sleepDuration > 0)
                                Thread.sleep(sleepDuration);

                        } catch (InterruptedException ex) {
                            Logger.getLogger(Grid.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });
                });
    }

    public void drawTile(Tile tile, Tile exit, Tile entrance, Tile.Type type, long sleep) {
        this.executor.execute(() ->
        {
            if (tile != exit && tile != entrance)
                tile.setAttributes(type, tile.getWeight());
            try {
                if (sleep > 0)
                    Thread.sleep(sleep);

            } catch (InterruptedException ex) {
                Logger.getLogger(FindingExit.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    public void highlightTile(Tile tile, long waitTime) {
        this.drawTile(tile, null, null, Tile.Type.HIGHLIGHT, waitTime);
        this.drawTile(tile, null, null, Tile.Type.EMPTY, waitTime);
    }

    public void clearPath(Grid model) {
        this.executor.execute(() ->
        {
            Tile tile;
            for (int y = 0; y < model.getYSize(); y++) {
                for (int x = 0; x < model.getXSize(); x++) {
                    tile = model.getGrid()[x][y];
                    if (tile.getType() == Tile.Type.PATH || tile.getType() == Tile.Type.VISITED) {
                        tile.setAttributes(Tile.Type.EMPTY, tile.getWeight());
                    }
                }
            }
        });
    }
}