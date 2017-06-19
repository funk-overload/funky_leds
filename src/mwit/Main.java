package mwit;


import com.pi4j.io.spi.SpiChannel;
import com.pi4j.io.spi.SpiDevice;
import com.pi4j.io.spi.SpiFactory;
import com.pi4j.io.spi.SpiMode;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

    //A start frame of 32 zero bits (<0x00> <0x00> <0x00> <0x00>)
    //A 32 bit LED frame for each LED in the string (<0xE0+brightness> <blue> <green> <red>)
    //An end frame consisting of at least (n/2) bits of 1, where n is the number of LEDs in the string.

    public static SpiDevice spi = null;
    public static ArrayList<Led> leds = new ArrayList<Led>();
    public static Frames frames = new Frames();

    public static void main(String[] args) throws InterruptedException, IOException {

        // create SPI object instance for SPI for communication
        spi = SpiFactory.getInstance(SpiChannel.CS0,
                SpiDevice.DEFAULT_SPI_SPEED,    // 1 MHz
                SpiMode.MODE_0);                // spi mode 0




        for (int i=0; i < 50; i++) {
            leds.add(new Led(31, 0, 0, 0));
        }

        byte[] bytes = frames.ledsToFrameBytes(leds);

        System.out.println("Bytes: " + bytes.length);
        System.out.println("Speed: " + (1000f / ((float)SpiDevice.DEFAULT_SPI_SPEED / (float)(bytes.length * 8))) + "ms");

        spi.write(bytes);
        Thread.sleep(100);

        for (int i=0; i < 17; i++){
            leds.get(i).r = 100;
            leds.get(i).g = 0;
            leds.get(i).b = 0;
        }

        for (int i=17; i < 33; i++){
            leds.get(i).r = 0;
            leds.get(i).g = 100;
            leds.get(i).b = 0;
        }

        for (int i=33; i < 50; i++){
            leds.get(i).r = 0;
            leds.get(i).g = 0;
            leds.get(i).b = 100;
        }

        bytes = frames.ledsToFrameBytes(leds);
        spi.write(bytes);

//        int size = leds.size();
//        while (true) {
//            for (int i = 0; i < size; i++) {
//                if (leds.get(i).r < 255) {
//                    leds.get(i).r++;
//                } else {
//                    leds.get(i).r = 0;
//                }
//                if (leds.get(i).g < 255) {
//                    leds.get(i).g++;
//                } else {
//                    leds.get(i).g = 0;
//                }
//                if (leds.get(i).b < 255) {
//                    leds.get(i).b++;
//                } else {
//                    leds.get(i).b = 0;
//                }
//             }
//
//            bytes = frames.ledsToFrameBytes(leds);
//            spi.write(bytes);
//            Thread.sleep(1);
//
//        }

//        for (int i=0; i < 10; i++) {
//            for (int current = 0; current < size; current++) {
//                curve(current);
//            }
//            for (int current = size - 2; current > 0; current--) {
//                curve(current);
//            }
//        }


    }

    public static void curve(int current) throws InterruptedException, IOException {
        if (current+5 < 33){
            leds.get(current+5).r = 0;
            leds.get(current+5).g = 0;
            leds.get(current+5).b = 0;
        }
        if (current+4 < 33){
            leds.get(current+4).r = 2;
            leds.get(current+4).g = 2;
            leds.get(current+4).b = 2;
        }
        if (current+3 < 33){
            leds.get(current+3).r = 4;
            leds.get(current+3).g = 4;
            leds.get(current+3).b = 4;
        }
        if (current+2 < 33){
            leds.get(current+2).r = 6;
            leds.get(current+2).g = 6;
            leds.get(current+2).b = 6;
        }
        if (current+1 < 33){
            leds.get(current+1).r = 8;
            leds.get(current+1).g = 8;
            leds.get(current+1).b = 8;
        }

        leds.get(current).r = 10;
        leds.get(current).g = 10;
        leds.get(current).b = 10;

        if (current-1 >= 0){
            leds.get(current-1).r = 8;
            leds.get(current-1).g = 8;
            leds.get(current-1).g = 8;
        }
        if (current-2 >= 0){
            leds.get(current-2).r = 6;
            leds.get(current-2).g = 6;
            leds.get(current-2).b = 6;
        }
        if (current-3 >= 0){
            leds.get(current-3).r = 4;
            leds.get(current-3).g = 4;
            leds.get(current-3).b = 4;
        }
        if (current-4 >= 0){
            leds.get(current-4).r = 2;
            leds.get(current-4).g = 2;
            leds.get(current-4).b = 2;
        }
        if (current-5 >= 0){
            leds.get(current-5).r = 0;
            leds.get(current-5).g = 0;
            leds.get(current-5).b = 0;
        }

        byte[] bytes = frames.ledsToFrameBytes(leds);
        spi.write(bytes);
        Thread.sleep(15);
    }


    public static String bytesToString(byte[] bytes){

        StringBuilder result = new StringBuilder("");

        for (int i = 0; i < bytes.length; i++){
            result.append((bytes[i] & 0xFF));
            result.append(" ");
        }
        return result.toString();
    }


    private static Boolean isBitSet(byte b, int bit)
    {
        return (b & (1 << bit)) != 0;
    }


}
