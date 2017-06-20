package funky;


import java.io.IOException;


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
//        while (true){
//            //Do stuff
//
            try {
                //randomColorFade();

                int y = 0;
                boolean up = true;
                int r = 100;
                int b = 0;
                for (int x = 0; x < display.width; x++) {
                    if (up){
                        y++;
                        if (y >= display.height - 1){
                            up = false;
                            r = 0;
                            b = 100;
                        }
                    } else {
                        y--;
                        if (y <= 0){
                            up = true;
                            r = 100;
                            b = 0;
                        }
                    }

                    //System.out.println(x + " X " + y);

                    display.setPixel(x,y,r,0,b);
                    display.mapPixelsToLeds();
                    display.renderLeds();
                }

//                display.setPixel(10,10,255,0,0);
//                display.setPixel(20,20,0,255,0);
//                display.setPixel(30,30,0,0,255);

                //display.mapPixelsToLeds();
                //display.renderLeds();


            }
            catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
//        }


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
