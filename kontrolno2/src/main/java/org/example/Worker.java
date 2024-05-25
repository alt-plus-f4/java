package org.example;

import java.util.List;

public class Worker extends GameEntity implements Runnable{
    private int speed;
    private int typeOfResource;
    private List<ResourceSource> resourceSources;
    private List<Building> buildings;
    private static int count = 0;
    private final Game currentGame;

    public Worker(Game currentGame, int positionX, int positionY, int speed, int typeOfResource, List<ResourceSource> resourceSources, List<Building> buildings){
        super(positionX, positionY, "Worker " + count++);
        this.speed = speed;
        this.currentGame = currentGame;
        this.typeOfResource = typeOfResource;
        this.resourceSources = resourceSources;
        this.buildings = buildings;
    }

    private ResourceSource findNearestResource(){
        ResourceSource nearest = null;
        double minDistance = 10000;
        for(ResourceSource resource : resourceSources){
            if(resource.getResourceType() == this.typeOfResource){
                double distance = Math.sqrt(Math.pow(this.getPositionX() - resource.getPositionX(), 2) + Math.pow(this.getPositionY() - resource.getPositionY(), 2));
                if(distance < minDistance){
                    minDistance = distance;
                    nearest = resource;
                }
            }
        }
        return nearest;
    }
    private Building findNearestBuilding(){
        Building nearest = null;
        double minDistance = 10000;
        for(Building building : buildings){
            if(this.typeOfResource == 1 && building instanceof GoldStorage){
                double distance = Math.sqrt(Math.pow(this.getPositionX() - building.getPositionX(), 2) + Math.pow(this.getPositionY() - building.getPositionY(), 2));
                if(distance < minDistance){
                    minDistance = distance;
                    nearest = (Building) building;
                }
            }
            else if(this.typeOfResource == 2 && building instanceof LumberMill){
                double distance = Math.sqrt(Math.pow(this.getPositionX() - building.getPositionX(), 2) + Math.pow(this.getPositionY() - building.getPositionY(), 2));
                if(distance < minDistance){
                    minDistance = distance;
                    nearest = (Building) building;
                }
            }
        }
        return nearest;
    }

    private void moveToPosition(int positionX, int positionY){
        while(getPositionX() != positionX || getPositionY() != positionY){
            System.out.println("Worker " + this.getName() + " is moving to position (" + positionX + ", " + positionY + ")");

            if(getPositionX() < positionX)
                setPositionX(getPositionX() + 1);
            else if(getPositionX() > positionX)
                setPositionX(getPositionX() - 1);
            if(getPositionY() < positionY)
                setPositionY(getPositionY() + 1);
            else if(getPositionY() > positionY)
                setPositionY(getPositionY() - 1);
            else break;
            // IDK how to include speed here
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    @Override
    public void run() {
        System.out.println("Worker " + this.getName() + " is running");
        System.out.println("Thread Name: " + Thread.currentThread().getName());
        while(true){
            ResourceSource nearest = findNearestResource();

            if(nearest == null){
                System.out.println("Worker " + this.getName() + " could not find any resource source");
                break;
            }

            moveToPosition(nearest.getPositionX(), nearest.getPositionY());

            if(nearest.getMaxWorkers() > 0){
                nearest.setMaxWorkers(nearest.getMaxWorkers() - 1);

                while(true){
                    try{
                        Thread.sleep(1000);
                    } catch (InterruptedException e){
                        break;
                    }

                    Building nearestBuilding = findNearestBuilding();
                    moveToPosition(nearestBuilding.getPositionX(), nearestBuilding.getPositionY());

                    synchronized (this){
                        if(this.typeOfResource == 1){
                            currentGame.setGold(currentGame.getGold() + 10);
                            System.out.println("Worker " + this.getName() + " deposited 10 gold to " + nearestBuilding.getName());
                        }
                        else if(this.typeOfResource == 2){
                            currentGame.setWood(currentGame.getWood() + 10);
                            System.out.println("Worker " + this.getName() + " deposited 10 wood to " + nearestBuilding.getName());
                        }
                    }

                    moveToPosition(nearest.getPositionX(), nearest.getPositionY());
                    nearest.setMaxWorkers(nearest.getMaxWorkers() + 1);
                }



            }

        }
    }
}
