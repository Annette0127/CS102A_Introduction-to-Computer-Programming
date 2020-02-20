package demo2;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.stream.Stream;

final class SquareComponent extends JLabel {
    private int edge = 0;
    private int row;
    private int col;

    public SquareComponent(int x, int y, int size, int row, int col) {
        setLocation(x, y);
        setSize(size, size);
        setHorizontalAlignment(SwingConstants.CENTER);
        setFont(getFont().deriveFont(35f));
        setVisible(true);
        this.row = row;
        this.col = col;
    }

    public static String[] getPrintName(String name1, String name2) {
        String[] name = new String[2];
        name[0] = name1.substring(0, 1);
        name[1] = name2.substring(0, 1);
        if (name1.charAt(0) == name2.charAt(0)) {
            for (int i = 1; i < Math.min(name1.length(), name2.length()); i++) {
                if (name1.charAt(i) != name2.charAt(i)) {
                    name[0] = name[0].concat(name1.substring(i, i + 1));
                    name[1] = name[1].concat(name2.substring(i, i + 1));
                    return name;
                }
            }
            if (name1.length() > name2.length()) {
                name[0] = name1.substring(name2.length(), name2.length() + 1);
            } else {
                name[1] = name2.substring(name1.length(), name1.length() + 1);
            }
        }
        return name;
    }

    public void printOwner(String currentPlayer, Color color) {
        setText(currentPlayer);
        setForeground(color);
        edge = -1;
    }

    public void deleteOwner() {
        setText("");
        edge = 3;
    }

    public static void scoreBoardInitial(JLabel[] scoreBoard, int[] score) {
        scoreBoard[0].setText("Score: " + score[0]);
        scoreBoard[1].setText("Score: " + score[1]);
        scoreBoard[0].setForeground(Color.RED);
        scoreBoard[1].setForeground(Color.BLUE);
        scoreBoard[0].setFont(scoreBoard[0].getFont().deriveFont(50f));
        scoreBoard[1].setFont(scoreBoard[1].getFont().deriveFont(50f));
        scoreBoard[0].setBounds(1500, 200, 300, 50);
        scoreBoard[1].setBounds(1500, 250, 300, 50);
    }

    public static void scoreBoardUpdate(JLabel[] scoreBoard, int[] score) {
        scoreBoard[0].setText("Score: " + score[0]);
        scoreBoard[1].setText("Score: " + score[1]);
    }

    public Stream<EdgeComponent> getAroundEdges(ArrayList<EdgeComponent> edges) {
        return edges.stream().filter(e -> (e.isHorizon() && (e.getCol() == col)
                && (e.getRow() == row || e.getRow() == row + 1))
                || (!e.isHorizon() && (e.getRow() == row)
                && (e.getCol() == col || e.getCol() == col + 1)));
    }


    public int plusEdge() {//x
        return ++edge;
    }

    public int minusEdge() {
        return --edge;
    }


    public int getEdge() {
        return edge;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setEdge(int edge) {
        this.edge = edge;
    }
//    @Override
//    public void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        Graphics2D g2d = (Graphics2D) g;
//        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//        g2d.setColor(Color.WHITE);
//        g2d.fillOval(0, 0, getWidth(), getHeight());
//    }
}


