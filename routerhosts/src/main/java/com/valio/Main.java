package com.valio;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Router r1 = new Router("R1");
        Router r2 = new Router("R2");
        Router r3 = new Router("R3");
        Router r4 = new Router("R4");
        Host h1 = new Host("H1", r1);
        Host h2 = new Host("H2", r2);
        Host h3 = new Host("H3", r3);

        r1.addHost(h1);
        r1.addRouter(r2);
        r1.addRouter(r4);

        r2.addHost(h2);
        r2.addRouter(r1);
        r2.addRouter(r3);

        r3.addHost(h3);
        r3.addRouter(r2);

        r4.addRouter(r1);

        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        Thread t3 = new Thread(r3);
        Thread t4 = new Thread(r4);

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        h1.sendPacket("H1", "Hello from H1", "H2");

        t1.join();
        t2.join();
        t3.join();
        t4.join();
    }
}