
package Factory;

import Algorithms.MazeGeneration;
import Algorithms.Prim;
import Algorithms.FindingExit;
import Algorithms.WavePropagation;

public class StrategyFactory {
    // Pathfinding Factory
    public static FindingExit getFindingExit(FindingExit.Algorithms algorithmStrategy) {
        switch (algorithmStrategy) {

            case WavePropagation:
                return new WavePropagation();
            default:
                throw new IllegalArgumentException("Pathfinding algorithm not found!");
        }
    }

    // Maze generation Factory
    public static MazeGeneration getMazeGenStrategy(MazeGeneration.MazeGen strategy) {
        switch (strategy) {
            case Prim:
                return new Prim();

            default:
                throw new IllegalArgumentException("Maze generation algorithm not found!");
        }
    }
}

