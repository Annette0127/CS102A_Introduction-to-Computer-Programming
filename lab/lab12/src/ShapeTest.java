import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class ShapeTest {
    public static void main(String[] args) {
        ArrayList<Circle> circles = new ArrayList<>();
        Shape.setScreenSize(9);
        StdDraw.setScale(-Shape.getScreenSize(), Shape.getScreenSize());

        for (int i = 1; i <=Shape.getScreenSize(); i += 2) {
            circles.add(new Circle(i, 0, i));
        }
        Collections.sort(circles);
        for (int i = 0; i < circles.size(); i++) {
           circles.get(i).customizedColor(ColorScheme.SKY,i);
        }
//		Circle c1=new Circle(0.1,1,1);
//		Circle c2=new Circle(0.1,0.5,2);
//		Circle.setScreenSize(2);
//		System.out.print(c1);
//		c1.checkColor();
//		c2.checkColor();
//		System.out.print(c1);
//		System.out.print(c2);
//
//
//		Rectangle r1=new Rectangle(0,0,0.5,0.5);
//		Rectangle r2=new Rectangle(2,1,0.5,0.5);
//		Rectangle.setScreenSize(2);
//		System.out.print(r1);
//		r1.checkColor();
//		r2.checkColor();
//		System.out.print(r1);
//		System.out.print(r2);
//
//		StdDraw.setXscale(-Circle.getScreenSize(), Circle.getScreenSize());
//		StdDraw.setYscale(-Circle.getScreenSize(), Circle.getScreenSize());
//		c1.draw();
//		c2.draw();
//		r1.draw();
//		r2.draw();
//		Circle c3=new Circle(0.1,0.5,-2);
//		Rectangle r3=new Rectangle(-2,1,0.5,0.5);
//		c3.draw();
//		r3.draw();
    }

}
