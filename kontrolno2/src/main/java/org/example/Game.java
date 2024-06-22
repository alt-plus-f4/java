package org.example;

import java.util.ArrayList;
import java.util.List;

public class Game implements Runnable {
    private int gold;
    private int wood;
    private List<Building> buildings = new ArrayList<>();
    private List<ResourceSource> resourceSources = new ArrayList<>();
    private List<Worker> workers = new ArrayList<>();
    private List<Thread> workerThreads = new ArrayList<>();

    @Override
    public void run() {
        startGame();
        while(true){
            try {
                Thread.sleep(500);
                System.out.println("Gold: " + gold);
                System.out.println("Wood: " + wood);
//                if(gold >= 100 || wood >= 100){
//                    System.out.println("You win!");
//                    endGame();
//                    break;
//                }
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    public void startGame(){
        for (Worker worker : workers) {
            Thread workerThread = new Thread(worker);
            workerThreads.add(workerThread);
            workerThread.start();
        }
    }

    public void endGame(){
        for (Thread workerThread : workerThreads) {
            workerThread.interrupt();
        }
    }

    public void addBuilding(Building building) {
        buildings.add(building);
    }
    public void addResourceSource(ResourceSource resourceSource) {
        resourceSources.add(resourceSource);
    }
    public void addWorker(Worker worker) {
        workers.add(worker);
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getWood() {
        return wood;
    }

    public void setWood(int wood) {
        this.wood = wood;
    }

    public List<Building> getBuildings() {
        return buildings;
    }

    public List<ResourceSource> getResourceSources() {
        return resourceSources;
    }

    public List<Worker> getWorkers() {
        return workers;
    }


}
