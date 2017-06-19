package mwit;

/**
 * Created by Magnus on 2017-06-16.
 */
public class Led {
    public int brightness;
    public int r;
    public int g;
    public int b;
    public int x;
    public int y;

    public Led(int brightness, int r, int g, int b, int x, int y) {
        this.brightness = brightness;
        this.r = r;
        this.g = g;
        this.b = b;
        this.x = x;
        this.y = y;
    }
}
