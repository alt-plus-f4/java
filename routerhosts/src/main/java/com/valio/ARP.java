package com.valio;

import java.util.ArrayList;
import java.util.List;

public class ARP {
    private List<String> ipAddresses;

    public ARP() {
        this.ipAddresses = new ArrayList<>();
    }

    public void add(String ipAddress) {
        this.ipAddresses.add(ipAddress);
    }

    public boolean contains(String ipAddress) {
        return this.ipAddresses.contains(ipAddress);
    }

    public void remove(String ipAddress) {
        this.ipAddresses.remove(ipAddress);
    }

    public List<String> getArpTable() {
        return ipAddresses;
    }

    public List<String> getPath(String source, String destination) {
        List<String> path = new ArrayList<>();
        path.add(source);
        path.add(destination);
        return path;
    }
}
