package com.valio;

public class Packet {
    private String source;
    private String destination;
    private String data;
    private String type;
    private int ttl;

    public Packet(String source, String destination, String data, String type) {
        this.source = source;
        this.destination = destination;
        this.data = data;
        this.type = type;
        this.ttl = 5;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public String getData() {
        return data;
    }

    public int getTtl() {
        return ttl;
    }

    public String getType() {
        return type;
    }

    public void decreaseTtl() {
        ttl--;
    }

    public boolean isAlive() {
        return ttl > 0;
    }
}
