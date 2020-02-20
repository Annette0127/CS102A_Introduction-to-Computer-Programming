import java.awt.*;

public class Circle extends Shape implements Comparable<Circle>,ColorDrow{
    private double radius;
    private static final int DEFAULT_RADIUS = 5;


    public Circle(double radius, double x, double y) {
        super(x, y);
        this.radius = radius;

    }

    public Circle(double radius) {
        super(0, 0);
        this.radius = radius;
    }

    public Circle(double x, double y) {
        super(x, y);
        this.radius = DEFAULT_RADIUS;
    }


    public void checkColor() {
        if (isInBoundary()) {
            color = ShapeColor.GREEN;
        } else {
            color = ShapeColor.RED;
        }
    }

    public boolean isInBoundary() {
        if (-1 * Shape.getScreenSize() > x - this.radius
                || Shape.getScreenSize() < x + this.radius) {
            return false;
        }
        if (-1 * Shape.getScreenSize() > y - this.radius
                || Shape.getScreenSize() < y + this.radius) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Circle{" +
                "radius=" + radius + super.toString() +
                "}\n";
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }


    public void draw() {

        StdDraw.setPenColor(color.getColor());
        StdDraw.filledCircle(x, y, radius);


    }

    @Override
    public int compareTo(Circle o) {
        if (this.radius < o.radius) {
            return 1;
        } else if (this.radius > o.radius) {
            return -1;
        } else {
            return 0;
        }
    }

    public void customizedColor(ColorScheme colorScheme,int index){
        Color[] colorList = colorScheme.getColorScheme();
        if(index<0){index = 0;}
        if(index>=colorList.length){index = index%colorList.length;}
        StdDraw.setPenColor(colorList[index]);
        StdDraw.filledCircle(x,y,radius);
    }
}
