package com.SKIPPS.utils.power;

public class MagicPacket {
    private String macAddress;
    private byte[] magicPacketBytes;

    public MagicPacket(String mac) {
        macAddress = mac;
        magicPacketBytes = generateMagicPacket(macAddress);
    }

    private byte[] generateMagicPacket(String mac) {
        byte[] macBytes = getMacBytes(mac);
        byte[] magicPacket = new byte[102];
        for (int i = 0; i < 6; i++) {
            magicPacket[i] = (byte) 0xFF;
        }
        for (int i = 6; i < magicPacket.length; i += macBytes.length) {
            System.arraycopy(macBytes, 0, magicPacket, i, macBytes.length);
        }

        return magicPacket;
    }

    private byte[] getMacBytes(String mac) {
        String[] macParts = mac.split(":");
        byte[] macBytes = new byte[6];
        for (int i = 0; i < macParts.length; i++) {
            macBytes[i] = (byte) Integer.parseInt(macParts[i], 16);
        }
        return macBytes;
    }

    public byte[] getMagicPacketBytes() {
        return magicPacketBytes;
    }
}