package mwit;

import com.pi4j.io.spi.SpiChannel;
import com.pi4j.io.spi.SpiDevice;
import com.pi4j.io.spi.SpiFactory;
import com.pi4j.io.spi.SpiMode;

import java.io.IOException;
import java.util.ArrayList;

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

    public Display(int width, int height, int gradientDistance) throws IOException {
        this.width = width;
        this.height = height;
        this.gradientDistance = gradientDistance;

        // create SPI object instance for SPI for communication
        // DEFAULT_SPI_SPEED = 1 MHz
        this.spi = SpiFactory.getInstance(SpiChannel.CS0, SpiDevice.DEFAULT_SPI_SPEED, SpiMode.MODE_0);

        initializeLeds();
    }

    private void initializeLeds(){
        //TODO read from config
        leds.add(new Led(31, 0, 0, 0, 0, 0));
    }

    public void updateLeds() throws IOException {
        byte[] bytes = frames.ledsToFrameBytes(leds);
        spi.write(bytes);
    }

}
