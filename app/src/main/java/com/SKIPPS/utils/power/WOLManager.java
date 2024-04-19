package com.SKIPPS.utils.power;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class WOLManager {
    public static void send(MagicPacket magicPacket, String destination) throws Exception {

        byte packet[] = magicPacket.getMagicPacketBytes();
        DatagramPacket datagramPacket = new DatagramPacket(packet, packet.length, InetAddress.getByName(destination), 9);
        DatagramSocket datagramSocket = new DatagramSocket();
        datagramSocket.send(datagramPacket);
        datagramSocket.close();

    }
}