package org.example;

public class ResourceSource extends GameEntity{
    private int resourceType;
    private int maxWorkers;

    public ResourceSource(int positionX, int positionY, int resourceType, int maxWorkers){
        super(positionX, positionY, "ResourceSource " + resourceType);
        this.resourceType = resourceType;
        this.maxWorkers = maxWorkers;
    }

    public int getResourceType() {
        return resourceType;
    }

    public int getMaxWorkers() {
        return maxWorkers;
    }

    public synchronized void setMaxWorkers(int maxWorkers) {
        this.maxWorkers = maxWorkers;
    }
}
