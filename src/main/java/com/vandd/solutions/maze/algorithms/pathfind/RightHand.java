package com.vandd.solutions.maze.algorithms.pathfind;

import com.vandd.solutions.maze.GridModel.Grid;
import com.vandd.solutions.maze.GridModel.Tile;

import java.util.*;

public class RightHand extends FindingExit {
    private int direction;
    private int step;
    private int x_size;
    private int x;
    private int y;
    private int y_size;
    private Mouse mouse;
    private Grid grid;
    private Tile[][] tile;
    public RightHand() {
        super();
    }
   @Override
    protected int runPathfinder(Grid grid, List<Tile> path) {

        HashMap<Tile, Integer> tileData = new HashMap<>();
        this.executeRightHand(grid, tileData);

        this.buildPath(path, grid, tileData);
        int cost = tileData.get(grid.getTarget()) - 1;

        this.painter.drawPath(path, grid);

        return cost;
    }

    private void executeRightHand(Grid grid, HashMap<Tile, Integer> tileData) {
        tileData.put(grid.getRoot(), step);

    }
    private void buildPath(List<Tile> path, Grid grid, HashMap<Tile, Integer> data){

        Tile currentTile = grid.getTarget();
        this.y = currentTile.getY();
        this.x = currentTile.getX();
        do {
            path.add(currentTile);
            //for()
        }while(currentTile != grid.getRoot());
        Collections.reverse(path);
    }
    //Изменяет направление мыши при появлении на старте

    public void toStep() {
        switch (direction) {
            case 0: --y; break;
            case 1: ++x; break;
            case 2: ++y; break;
            case 3: --x; break;
        }
    }
    public boolean isFrontWall(Tile[][] tile) {
        int x = mouse.x;
        int y = mouse.y;
        int p = mouse.direction;
        switch(p) {
            //ЗАЛУУУУУПА вместо grid какую-то хуйню надо
            case 0: {
                if (mouse.y == 0) return true;
              //  return tile[x][y-1].downWall;
            }
            case 1: {
                //return tile[x][y].rightWall;
            }
            case 2: {
               // return tile[x][y].downWall;
            }
            case 3: {
                if (mouse.x == 0) return true;
                //return tile[x-1][y].rightWall;
            }
        }
        return true;
    }

    public boolean isRightWall() {
        int x = mouse.x;
        int y = mouse.y;
        int p = mouse.direction;
        switch(p) {
            case 0: {
               // return tile[x][y].rightWall;
            }
            case 1: {
                //return tile[x][y].downWall;
            }
            case 2: {
                if (mouse.x == 0) return true;
               // return tile[x-1][y].rightWall;
            }
            case 3: {
                if (mouse.y == 0) return true;
               // return tile[x][y-1].downWall;
            }
        }
        return true;
    }

}