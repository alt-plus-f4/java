package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();

        game.addBuilding(new LumberMill(0, 0));
        game.addBuilding(new GoldStorage(1, 5));
        game.addResourceSource(new ResourceSource(3, 3, 1, 5));
        game.addResourceSource(new ResourceSource(5, 2, 2, 5));
        game.addWorker(new Worker(game, 0, 0, 1, 1, game.getResourceSources(), game.getBuildings()));
        game.addWorker(new Worker(game, 0, 1, 1, 1, game.getResourceSources(), game.getBuildings()));
        game.addWorker(new Worker(game, 0, 0, 1, 2, game.getResourceSources(), game.getBuildings()));

        Thread gameThread = new Thread(game);
        gameThread.start();
    }
}