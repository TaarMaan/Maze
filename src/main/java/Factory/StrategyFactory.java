
package Factory;

import Algorithms.*;

public class StrategyFactory {
    // Pathfinding Factory
    public static FindingExit getFindingExit(FindingExit.Algorithms algorithmStrategy) {
        switch (algorithmStrategy) {

            case WavePropagation:
                return new WavePropagation();
            case RightHand:
                return new RightHand();
            default:
                throw new IllegalArgumentException("Pathfinding algorithm not found!");
        }
    }

    // Maze generation Factory
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

