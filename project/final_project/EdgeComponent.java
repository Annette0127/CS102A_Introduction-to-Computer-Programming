package demo2;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class EdgeComponent extends JComponent {
    private Color color = Color.WHITE;
    private boolean free = true;
    private int row;//where
    private int col;//where
    boolean horizon;//direction


    EdgeComponent(int x, int y, int width, int height, int row, int col, boolean horizon) {
        setLocation(x, y);
        setSize(width, height);
        this.row = row;
        this.col = col;
        this.horizon = horizon;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean isHorizon() {
        return horizon;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }


    public int howManyC(ArrayList<SquareComponent> squares) {
        return (int) squares.stream().filter(s -> (horizon && (col == s.getCol())
                && (row == s.getRow() || row == s.getRow() + 1))
                || (!horizon && (row == s.getRow())
                && (col == s.getCol() || col == s.getCol() + 1))).filter(s -> s.getEdge() == 2).count();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), free ? 127 : 255));
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }

}
