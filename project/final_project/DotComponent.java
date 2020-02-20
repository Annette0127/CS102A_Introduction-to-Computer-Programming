package demo2;

import javax.swing.*;
import java.awt.*;

public class DotComponent extends JComponent {
    private int row, col;
    private Color color = Color.BLACK;

    public DotComponent(int x, int y, int size, int row, int col) {
        this.row = row;
        this.col = col;
        setLocation(x, y);
        setSize(size, size);
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(color);
        g2d.fillOval(0, 0, getWidth(), getHeight());
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
