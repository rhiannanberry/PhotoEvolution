/**
 * Created by STARR on 4/9/2016.
 */
import java.awt.Color;
import java.util.Random;

public class Pixel {
    private int red;
    private int green;
    private int blue;
    private int alpha;
    private Color color;
    private int bound;

    Pixel(Color pix) {
        red = pix.getRed();
        green = pix.getGreen();
        blue = pix.getBlue();
        alpha = pix.getAlpha();
        color = pix;
    }

    public void randomizer(int bound) {
        this.bound = bound;
        red = randomGen(red);
        green = randomGen(green);
        blue = randomGen(blue);
        color = new Color(red, green, blue, alpha);
    }


    private int randomGen(int val) {
        Random r = new Random();
        int newVal;
        int delta = r.nextInt(bound + 1);
        if (r.nextBoolean()) {
            delta = 0 - delta;
        }
        if ((delta + val) > 255) {
            newVal = 255;
        } else if ((delta + val) < 0) {
            newVal = 0;
        } else {
            newVal = delta + val;
        }
        return newVal;
    }

    public void setColor(int r, int  g, int b) {
        color = new Color(r, g, b, 255);
        red = r;
        green = g;
        blue = b;
        alpha = 255;
    }

    public void setColor(int r, int  g, int b, int a) {
        color = new Color(r, g, b, a);
        red = r;
        green = g;
        blue = b;
        alpha = a;
    }

    public void setColor(Color col) {
        color = col;
        red = col.getRed();
        green = col.getRed();
        blue = col.getBlue();
        alpha = col.getAlpha();
    }

    public Color getColor() {
        return color;
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    public int getAlpha() {
        return alpha;
    }

    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }
}
