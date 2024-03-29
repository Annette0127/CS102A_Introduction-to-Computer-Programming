import java.awt.*;

public class Shape {
    protected double x;
    protected double y;
    protected Color color = Color.GRAY;
    protected static int screenSize = 10;

    public Shape(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public static int getScreenSize() {
        return screenSize;
    }

    public static void setScreenSize(int screenSize) {
        Shape.screenSize = screenSize;
    }

    @Override
    public String toString() {
        return "x = " + x + ", y = " + y + ", color = " + color;
    }

    public void draw() {}

    public void checkColor() {}
}
