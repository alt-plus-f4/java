package com.valio;

public class Host {
    private String name;
    private Router defaultGateway;

    public Host(String name, Router defaultGateway) {
        this.name = name;
        this.defaultGateway = defaultGateway;
    }

    public void sendPacket(String source, String message, String destination) {
        this.defaultGateway.addPacket(new Packet(source, destination, message, "msg"));
    }

    public String getName() {
        return name;
    }
}
