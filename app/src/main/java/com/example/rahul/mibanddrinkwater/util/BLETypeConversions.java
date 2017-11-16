
package com.example.rahul.mibanddrinkwater.util;

import java.nio.charset.StandardCharsets;

/**
 * Provides methods to convert standard BLE units to byte sequences and vice versa.
 */
public class BLETypeConversions {

    public static int toUint16(byte... bytes) {
        return (bytes[0] & 0xff) | ((bytes[1] & 0xff) << 8);
    }

    public static byte[] fromUint16(int value) {
        return new byte[] {
                (byte) (value & 0xff),
                (byte) ((value >> 8) & 0xff),
        };
    }

    public static byte[] fromUint24(int value) {
        return new byte[] {
                (byte) (value & 0xff),
                (byte) ((value >> 8) & 0xff),
                (byte) ((value >> 16) & 0xff),
        };
    }

    public static byte[] fromUint32(int value) {
        return new byte[] {
                (byte) (value & 0xff),
                (byte) ((value >> 8) & 0xff),
                (byte) ((value >> 16) & 0xff),
                (byte) ((value >> 24) & 0xff),
        };
    }

    public static byte fromUint8(int value) {
        return (byte) (value & 0xff);
    }

    public static byte[] toUtf8s(String message) {
        return message.getBytes(StandardCharsets.UTF_8);
    }

}
