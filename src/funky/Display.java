package funky;

import com.pi4j.io.spi.SpiChannel;
import com.pi4j.io.spi.SpiDevice;
import com.pi4j.io.spi.SpiFactory;
import com.pi4j.io.spi.SpiMode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Magnus on 2017-06-19.
 */
public class Display {

    public ArrayList<Led> leds = new ArrayList<Led>();
    public int width;
    public int height;
    public int gradientDistance;

    public SpiDevice spi = null;
    public Frames frames = new Frames();

    private ArrayList<Pixel> pixels = null;

    public Display(int width, int height, int gradientDistance) throws IOException {
        this.width = width;
        this.height = height;
        this.gradientDistance = gradientDistance;

        // create SPI object instance for SPI for communication
        // DEFAULT_SPI_SPEED = 1 MHz
        this.spi = SpiFactory.getInstance(SpiChannel.CS0, 4000000, SpiMode.MODE_0);

        initializeLeds();
        initializeCoordinates();

//        for (int x = 0; x < width; x++) {
//            for (int y = 0; y < height; y++) {
//                Pixel p = pixels.get(x + (y*width));
//                System.out.println("(" + x + " x " + y + "):" + p.ledMap.size());
//
//                for (HashMap.Entry<Led, Float> led : p.ledMap.entrySet()){
//                    System.out.println(led.getValue() + " (" + led.getKey().x + " X " + led.getKey().y + ")");
//                }
//            }
//        }
    }

    private void initializeCoordinates(){
        pixels = new ArrayList<Pixel>(width * height);
        int nofLeds = leds.size();
        float max = gradientDistance * gradientDistance;

        for (int x = 0; x < width; x++){
            for (int y = 0; y < height; y++){
                Pixel p = new Pixel();

                for (int i = 0; i < nofLeds; i++){
                    float d = squaredDistance(x, y, leds.get(i).x, leds.get(i).y);
                    float grad = (d / max);
                    if (grad < 1){
                        grad = (float)Math.sqrt(grad);
                        p.ledMap.put(leds.get(i), (grad * -1) + 1);
                    }
                }

                pixels.add(p);
            }
        }
    }

    private int squaredDistance(int x1, int y1, int x2, int y2) {
        return (x1 - x2)*(x1 - x2) + (y1 - y2)*(y1 - y2);
    }

    private void initializeLeds(){
        //TODO read from config
        //int nofLeds = 50;
        for (int i = 0; i < 17; i++){
            leds.add(new Led(31, 0, 0, 0, (10 * i) + 10, 10));
        }
        for (int i = 0; i < 16; i++){
            leds.add(new Led(31, 0, 0, 0, (10 * i) + 15, 20));
        }
        for (int i = 0; i < 17; i++){
            leds.add(new Led(31, 0, 0, 0, (10 * i) + 10, 30));
        }
    }

    public void updateLeds() throws IOException {
        byte[] bytes = frames.ledsToFrameBytes(leds);
        spi.write(bytes);
    }

    public void setPixel(int x, int y, int r, int g, int b){
        int i = x + (y*width);
        Pixel p = pixels.get(i);

    }

}
