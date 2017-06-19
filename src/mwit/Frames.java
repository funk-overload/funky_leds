package mwit;

import java.nio.ByteBuffer;
import java.util.ArrayList;

/**
 * Created by Magnus on 2017-06-16.
 */
public class Frames {

    public byte[] ledsToFrameBytes(ArrayList<Led> leds){

        int size = (1 + leds.size()) * 4;
        int endSize = (int)Math.ceil(leds.size() / 2f);
        size += endSize;

        ByteBuffer bytes = ByteBuffer.allocate(size);
        bytes.put(startFrame());
        for (int i = 0; i < leds.size(); i++){
            bytes.put(ledFrame(leds.get(i).brightness, leds.get(i).r, leds.get(i).g, leds.get(i).b));
        }
        bytes.put(endFrame(endSize));

        return bytes.array();
    }


    // brightness 0-31
    // r, g, b    0-255
    private byte[] ledFrame(int brightness, int r, int g, int b ){

        byte[] frame = ByteBuffer
                .allocate(4)
                .put((byte)(0xE0 + (brightness & 0xFF)))
                .put((byte)(b & 0xFF))
                .put((byte)(g & 0xFF))
                .put((byte)(r & 0xFF))
                .array();
        return frame;
    }

    private byte[] startFrame(){

        byte[] frame = ByteBuffer
                .allocate(4)
                .put((byte)(0x00))
                .put((byte)(0x00))
                .put((byte)(0x00))
                .put((byte)(0x00))
                .array();
        return frame;
    }

    private byte[] endFrame(int size){

        ByteBuffer bb = ByteBuffer.allocate(size);
        for (int i = 0; i < size; i++){
            bb.put((byte)(0x00));
        }

        return bb.array();
    }
}
