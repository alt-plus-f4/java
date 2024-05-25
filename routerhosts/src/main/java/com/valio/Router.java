package com.valio;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Router implements Runnable {
    private String name;
    private ArrayList<Host> connectedHosts;
    private ArrayList<Router> connectedRouters;
    private ArrayList<ARP> arpTables;
    private Queue<Packet> packetQueue;

    public Router(String name) {
        this.name = name;
        this.connectedHosts = new ArrayList<>();
        this.connectedRouters = new ArrayList<>();
        this.arpTables = new ArrayList<>();
        this.packetQueue = new LinkedList<>();
    }

    @Override
    public void run() {
        while(true){
//            System.out.println("Router " + name + " is checking for packets");
            if (!packetQueue.isEmpty()) {
                Packet packet = packetQueue.poll();
                System.out.println("Router is sending packet to " + packet.getDestination() + " from " + packet.getSource() + " with message: " + packet.getData());
                try {
                    sendPacket(packet.getSource(), packet, packet.getDestination());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void addPacket(Packet packet) {
        packetQueue.add(packet);
    }

    public void addHost(Host host) {
        connectedHosts.add(host);
    }

    public void addRouter(Router router) {
        connectedRouters.add(router);
    }

    public void addArpTable(ARP arpTable) {
        arpTables.add(arpTable);
    }

    public void sendPacket(String source, Packet packet, String destination) throws InterruptedException {
        for (Host host : connectedHosts) {
            if (host.getName().equals(destination)) {
                System.out.println("Router " + name + " sent packet to host " + destination);
                return;
            }
        }

        for (Router router : connectedRouters) {
            router.sendPacket(name, packet, destination);
            System.out.println("Router " + name + " sent packet to router " + router.getName() + " with destination: " + destination);
            Thread.sleep(1000);
        }
    }

    public void receivePacket(String source, Packet packet) throws InterruptedException {
        if (packet.isAlive()) {
            packet.decreaseTtl();
            sendPacket(source, packet, packet.getDestination());
        }
    }

    public String getName() {
        return name;
    }
}
