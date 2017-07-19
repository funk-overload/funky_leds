package funky;


import java.io.IOException;
import java.util.ArrayList;


public class Main {

    //A start frame of 32 zero bits (<0x00> <0x00> <0x00> <0x00>)
    //A 32 bit LED frame for each LED in the string (<0xE0+brightness> <blue> <green> <red>)
    //An end frame consisting of at least (n/2) bits of 1, where n is the number of LEDs in the string.

    //public static SpiDevice spi = null;
    //public static ArrayList<Led> leds = new ArrayList<Led>();
    //public static Frames frames = new Frames();
    public Display display = null;

    public Main() throws IOException {
        this.display = new Display(180, 40, 10);
    }

    public static void main(String[] args) throws InterruptedException, IOException {

        Main main = new Main();
        //main.startApi();
        main.loop();
    }

    private void loop(){
        while (true){
//            //Do stuff
//
            try {
                //randomColorFade();
                //megaRandom();
                //megaFade();
                rainbow();

//                int y = 0;
//                boolean up = true;
//                int r = 100;
//                int b = 0;
//                for (int x = 0; x < display.width; x++) {
//                    if (up){
//                        y++;
//                        if (y >= display.height - 1){
//                            up = false;
//                            r = 0;
//                            b = 100;
//                        }
//                    } else {
//                        y--;
//                        if (y <= 0){
//                            up = true;
//                            r = 100;
//                            b = 0;
//                        }
//                    }
//
//                    //System.out.println(x + " X " + y);
//
//                    display.setPixel(x,y,r,0,b);
//                    display.mapPixelsToLeds();
//                    display.renderLeds();
//                }

//                display.setPixel(10,10,255,0,0);
//                display.setPixel(20,20,0,255,0);
//                display.setPixel(30,30,0,0,255);

                //display.mapPixelsToLeds();

//               for (int c=0; c < 255; c++) {
//                    for (int i=0; i < 50; i++){
//                        display.leds.get(i).r = c;
//                        display.leds.get(i).g = c;
//                        display.leds.get(i).b = c;
//                    }
//                   display.renderLeds();
//                }
//                for (int c=255; c >= 0; c--) {
//                    for (int i=0; i < 50; i++){
//                        display.leds.get(i).r = c;
//                        display.leds.get(i).g = c;
//                        display.leds.get(i).b = c;
//                    }
//                    display.renderLeds();
//                }

                display.renderLeds();


            }
            catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }


    }

    private void rainbow() throws IOException {

        int[] r = {100,   0,   0,   0, 100, 100 };
        int[] g = {  0,   0, 100, 100, 100,   0 };
        int[] b = {100, 100, 100,   0,   0,   0 };





        for (int c = 0; c < r.length; c++) {;
            for (int f = 0; f < 100; f++) {
                for (int row = 0; row < 11; row++) {
                    int pos = c + row;
                    if (pos >= r.length) {
                        pos -= r.length;
                    }
                    if (pos >= r.length) {
                        pos -= r.length;
                    }
                    if (pos >= r.length) {
                        pos -= r.length;
                    }
                    for (int col = 0; col < 25; col++) {
                        int i = (row * 25) + col;
                        if (r[pos] < display.leds.get(i).r) {
                            display.leds.get(i).r--;
                        }
                        if (r[pos] > display.leds.get(i).r) {
                            display.leds.get(i).r++;
                        }
                        if (g[pos] < display.leds.get(i).g) {
                            display.leds.get(i).g--;
                        }
                        if (g[pos] > display.leds.get(i).g) {
                            display.leds.get(i).g++;
                        }
                        if (b[pos] < display.leds.get(i).b) {
                            display.leds.get(i).b--;
                        }
                        if (b[pos] > display.leds.get(i).b) {
                            display.leds.get(i).b++;
                        }


                    }
                }
                display.renderLeds();
            }
        }




    }
    private void megaFade() throws IOException {

        for (Led l : display.leds) {

            for (int c = 0; c < 100; c += 20) {
                l.r = c;
                display.renderLeds();
            }

        }

        for (Led l : display.leds) {

            for (int c = 0; c < 100; c += 20) {
                l.g = c;
                display.renderLeds();
            }

        }

        for (Led l : display.leds) {

            for (int c = 0; c < 100; c += 20) {
                l.b = c;
                display.renderLeds();
            }
        }

        for (Led l : display.leds) {

            for (int c = 100; c >= 0; c -= 20) {
                l.r = c;
                display.renderLeds();
            }
        }

        for (Led l : display.leds) {

            for (int c = 100; c >= 0; c -= 20) {
                l.g = c;
                display.renderLeds();
            }
        }

        for (Led l : display.leds) {

            for (int c = 100; c >= 0; c -= 20) {
                l.b = c;
                display.renderLeds();
            }
        }

    }

    private void megaRandom(){

        int r = (int)(Math.random() * 255);
        int g = (int)(Math.random() * 255);
        int b = (int)(Math.random() * 255);
        int l = (int)(Math.random() * 50);

        display.leds.get(l).r = r;
        display.leds.get(l).g = g;
        display.leds.get(l).b = b;

    }

    private void randomColorFade() throws IOException {
        int random = (int)(Math.random() * 50);
        int color = (int)(Math.random() * 3);
        if (color == 0){
            int r = display.leds.get(random).r;
            if (r == 0){
                for (int i = 0; i < 256; i++){
                    display.leds.get(random).r = i;
                    display.renderLeds();
                }
            } else {
                for (int i = 255; i >= 0 ; i--){
                    display.leds.get(random).r = i;
                    display.renderLeds();
                }
            }
        }
        if (color == 1){
            int r = display.leds.get(random).g;
            if (r == 0){
                for (int i = 0; i < 256; i++){
                    display.leds.get(random).g = i;
                    display.renderLeds();
                }
            } else {
                for (int i = 255; i >= 0 ; i--){
                    display.leds.get(random).g = i;
                    display.renderLeds();
                }
            }
        }
        if (color == 2){
            int r = display.leds.get(random).b;
            if (r == 0){
                for (int i = 0; i < 256; i++){
                    display.leds.get(random).b = i;
                    display.renderLeds();
                }
            } else {
                for (int i = 255; i >= 0 ; i--){
                    display.leds.get(random).b = i;
                    display.renderLeds();
                }
            }
        }
    }

//    private void startApi(){
//        try {
//            HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
//            server.createContext("/api", new Api(display));
//            server.setExecutor(null); // creates a default executor
//            server.start();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

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
