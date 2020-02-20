package demo2;


import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class MainFrame extends JFrame implements Serializable {
    private static final long serialVersionUID = 0;
    private final int WIDTH_OF_FRAME = 1920, HEIGHT_OF_FRAME = 1080, LENGTH_OF_EDGE = 100;
    private int width, height;
    private int currentPlayer;
    private int model, status;
    private String gameOwnerName;
    private ArrayList<EdgeComponent> edges = new ArrayList<>();
    private LinkedList<EdgeComponent> printEdges = new LinkedList<>();
    private ArrayList<SquareComponent> squares = new ArrayList<>();
    private ArrayList<DotComponent> dots = new ArrayList<>();
    private String[] player = new String[2], ownerName;
    private int[] score = new int[2];
    private boolean[] isMachine = new boolean[2];
    private JLabel[] scoreBoard = {new JLabel(), new JLabel()},
            fox = {new JLabel(new ImageIcon("src/demo2/sign1.png")),
                    new JLabel(new ImageIcon("src/demo2/sign2.png"))};
    private LinkedList<Integer> node = new LinkedList<>();
    private Color currentColor;
    private Data data;
    private JLabel dialogContent = new JLabel("", SwingConstants.CENTER);
    private Dialog dialog = new Dialog(this, "Warning");
    private JLabel keyInput = new JLabel("(,)--(,)", SwingConstants.CENTER);
    private JLabel timer = new JLabel("00:00:00", SwingConstants.CENTER);
    private JTextPane information = new JTextPane();
    private StyledDocument styledDocument = information.getStyledDocument();
    private SimpleAttributeSet attributeSet = new SimpleAttributeSet();

    class Time extends Thread {
        @Override
        public void run() {
            timer.setBounds(100, 50, 300, 100);
            timer.setBackground(new Color(191, 211, 255));
            timer.setFont(timer.getFont().deriveFont(40f));
            timer.setOpaque(true);
            repaint();
            int second = 0, minute, hour;
            long a, b;
            try {
                while (true) {
                    Thread.sleep(1000);
                    if (score[0] + score[1] < (width - 1) * (height - 1)) {
                        second = ++MainFrame.this.data.second % 60;
                    }
                    minute = MainFrame.this.data.second / 60;
                    hour = minute / 60;
                    minute %= 60;
                    timer.setText(String.format("%02d:%02d:%02d", hour, minute, second));
                    timer.repaint();
                }
            } catch (InterruptedException error) {
                error.printStackTrace();
            }
        }
    }

    class Machine extends Thread {
        public void run() {
            long start = System.currentTimeMillis();
            EdgeComponent edge = null;
            Stream<EdgeComponent> currentEdges = edges.stream().filter(e -> e.isFree());
            if (model == 1) {
                SquareComponent s = squares.stream().filter(e -> e.getEdge() == 3).findFirst().orElse(null);
                if (s != null) {
                    addThings(s.getAroundEdges(edges).filter(EdgeComponent::isFree).findFirst().get(), false);
                } else {
                    double a = Math.random(), b = Math.random(), c = Math.random();
                    edge = currentEdges.sorted(Comparator.comparingInt(e -> ((int)
                            (100 * (a * (e.getRow() + 1) + b * (e.getCol() + 1) + (e.isHorizon() ? c : 0)))) % 100))
                            .min(Comparator.comparingInt(e -> e.howManyC(squares))).orElse(null);
                }
            } else {
                int count = 0;
                for (int i = 0, d = 0; i < status; i++, d = 0) {
                    for (int j = i; j > 0; j &= j - 1, d++) ;
                    if (d == printEdges.size()) count++;
                }
                Strategy.Status[] statuses = Strategy.loadStrategy(width, height, printEdges.size(), count >> 17);
                edge = edges.get(statuses[count % (1 << 17)].getStrategy());
            }
            long finish = System.currentTimeMillis();
            if (finish - start < 300) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException error) {
                    error.printStackTrace();
                }
            }
            if (edge != null) addThings(edge, false);
        }
    }

    class GameKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            char keyC = e.getKeyChar();
            switch (keyC) {
                case '\n':
                    if (e.getSource() instanceof Dialog) {
                        ((Dialog) e.getSource()).dispose();
                    } else {
                        if (isMachine[currentPlayer]) return;
                        if (keyInput.getText().length() == 12) {
                            int[] co = Arrays.stream(keyInput.getText().split("\\D+")).skip(1)
                                    .mapToInt(a -> Integer.parseInt(a) - 1).toArray();
                            if ((co[0] == co[2] && (co[1] - co[3] == 1 || co[3] - co[1] == 1)
                                    || (co[1] == co[3] && (co[0] - co[2] == 1 || co[2] - co[0] == 1)))) {
                                int row = Math.min(co[0], co[2]), column = Math.min(co[1], co[3]);
                                boolean isHorizon = co[0] == co[2];
                                EdgeComponent edge = edges.stream().filter(a -> a.getRow() == row)
                                        .filter(a -> a.getCol() == column).filter(a -> a.isHorizon() == isHorizon)
                                        .findFirst().get();
                                if (edge.isFree()) {
                                    addThings(edge, false);
                                } else {
                                    dialogContent.setText("The edge exists");
                                    dialog.setVisible(true);
                                }
                            } else {
                                dialogContent.setText("The edge do not exists");
                                dialog.setVisible(true);
                            }
                            keyInput.setText("(,)--(,)");
                            setHighLight();
                        }
                        repaint();
                    }
                    break;
                case 27:
                    if (e.getSource() instanceof Dialog) {
                        ((Dialog) e.getSource()).dispose();
                    } else {
                        keyInput.setText("(,)--(,)");
                        setHighLight();
                        repaint();
                        break;
                    }
            }
        }

        @Override
        public void keyTyped(KeyEvent e) {
            int keyC = e.getKeyChar();
            StringBuilder text = new StringBuilder(keyInput.getText());
            if (keyC > '0' && keyC <= '9') {
                if (text.length() % 2 == 0 && text.length() < 12) {
                    if (keyC - '0' <= height) {
                        keyInput.setText(text.insert(text.length() == 8 ? 1 : 8, keyC - '0').toString());
                        setHighLight();
                    }
                } else if (keyC - '0' <= width && text.length() < 12) {
                    keyInput.setText(text.insert(text.length() == 9 ? 3 : 10, keyC - '0').toString());
                    setHighLight();
                }
            } else if (keyC == '\b') {
                if (text.length() % 2 != 0 && text.length() > 8) {
                    keyInput.setText(text.deleteCharAt(text.length() == 9 ? 1 : 8).toString());
                    setHighLight();
                } else if (text.length() > 8) {
                    keyInput.setText(text.deleteCharAt(text.length() == 10 ? 3 : 10).toString());
                    setHighLight();
                }

            }
            repaint();
        }
    }

    public class Data implements Serializable {
        private static final long serialVersionUID = 4;
        ArrayList<Integer> history = new ArrayList<>();
        String user;
        int model, width, height, second;
        String[] name;
        boolean[] isMachine;

        public Data(int model, int width, int height, String[] name, boolean[] isMachine, String user, int second) {
            this.model = model;
            this.height = height;
            this.width = width;
            this.isMachine = isMachine;
            this.name = name;
            this.user = user;
            this.second = second;
        }
    }

    private class GameActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (isMachine[currentPlayer] && score[0] + score[1] < (width - 1) * (height - 1)) return;
            String str = e.getActionCommand();
            switch (str) {
                case "Undo  ":
                    do undo(); while (printEdges.size() != 0 && isMachine[currentPlayer]);
                    if (isMachine[currentPlayer]) machinePlay();
                    break;
                case "Save  ":
                    FileDialog chooser = new FileDialog(MainFrame.this, "Use a .qwq extension", FileDialog.SAVE);
                    chooser.setVisible(true);
                    String filename = chooser.getFile();
                    if (filename != null) {
                        MainFrame.this.saveGame(chooser.getDirectory() + chooser.getFile());
                    }
                    break;
                case "Load  ":
                    FileDialog chooser1 = new FileDialog(MainFrame.this, "Use a .qwq extension", FileDialog.LOAD);
                    chooser1.setVisible(true);
                    String filename1 = chooser1.getFile();
                    if (filename1 != null) {
                        MainFrame.this.loadGame(chooser1.getDirectory() + chooser1.getFile());
                    }
                    repaint();
                    break;
            }
        }
    }

    private class GameMouseListener extends MouseInputAdapter {
        @Override
        public void mouseClicked(MouseEvent event) {
            if (isMachine[currentPlayer]) return;
            event = SwingUtilities.convertMouseEvent(MainFrame.this, event, getContentPane());
            Component component = getContentPane().getComponentAt(event.getPoint());
            if (component instanceof EdgeComponent) {
                EdgeComponent edge = (EdgeComponent) component;
                if (edge.isFree()) {
                    addThings(edge, false);
                }
            }
        }

        @Override
        public void mouseMoved(MouseEvent event) {
            event = SwingUtilities.convertMouseEvent(MainFrame.this, event, getContentPane());
            Component component = getContentPane().getComponentAt(event.getPoint());
            for (EdgeComponent e : edges) {
                if (e.isFree()) {
                    if (e == component) {
                        e.setColor(currentColor);
                        e.setVisible(true);
                    } else {
                        e.setVisible(false);
                    }
                }
            }
        }

    }


    public MainFrame(int width, int height, String gameOwnerName, String name1, String name2,
                     boolean isMachine1, boolean isMachine2, int model) {
        this.width = width;
        this.height = height;
        player[0] = name1;
        player[1] = name2;
        isMachine[0] = isMachine1;
        this.model = model;
        isMachine[1] = isMachine2;
        this.gameOwnerName = gameOwnerName;
        this.ownerName = SquareComponent.getPrintName(player[0], player[1]);
        data = new Data(model, width, height, player, isMachine, gameOwnerName, 0);
        initialize();
        GameMouseListener mouseListener = new GameMouseListener();
        addMouseListener(mouseListener);
        addMouseMotionListener(mouseListener);
        GameKeyListener keyListener = new GameKeyListener();
        addKeyListener(keyListener);
        information.addKeyListener(keyListener);
        information.setEditable(false);
        setDialog(dialog, dialogContent);
        dialog.addKeyListener(keyListener);
        Time time = new Time();
        time.start();
    }

    static void startGame(int width, int height, String gameOwnerName, String name1, String name2,
                          boolean isMachine1, boolean isMachine2, int model) {
        setGlobalFont();
        MainFrame mainFrame = new MainFrame(width, height, gameOwnerName, name1, name2, isMachine1, isMachine2, model);
        mainFrame.setVisible(true);
        if (mainFrame.isMachine[0]) {
            mainFrame.machinePlay();
        }
    }

    private static void setGlobalFont() {
        Font globalFont = new Font("Segoe Script", Font.BOLD, 25);
        FontUIResource fontRes = new FontUIResource(globalFont);
        for (Enumeration<Object> keys = UIManager.getDefaults().keys(); keys.hasMoreElements(); ) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource) UIManager.put(key, fontRes);
        }
        UIManager.put("SquareComponent" + ".font", globalFont);
    }

    private JMenuBar menu() {
        JMenuBar jMenuBar = new JMenuBar();
        JMenu edit = new JMenu("Edit");
        JMenuItem undo = new JMenuItem("Undo  ");
        JMenu game = new JMenu("File");
        JMenuItem save = new JMenuItem("Save  ");
        JMenuItem load = new JMenuItem("Load  ");
        jMenuBar.add(game);
        jMenuBar.add(edit);
        edit.add(undo);
        game.add(save);
        game.add(load);
        GameActionListener gameActionListener = new GameActionListener();
        undo.addActionListener(gameActionListener);
        save.addActionListener(gameActionListener);
        load.addActionListener(gameActionListener);
        edit.setMnemonic(KeyEvent.VK_E);
        game.setMnemonic(KeyEvent.VK_F);
        undo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK));
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
        load.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
        return jMenuBar;
    }

    public void initialize() {
        setTitle("Dot Box");
        setSize(WIDTH_OF_FRAME, HEIGHT_OF_FRAME);
        setLocationRelativeTo(null); // Center the window.
        setLayout(null);
        currentPlayer = 0;
        currentColor = Color.RED;
        int startX = (WIDTH_OF_FRAME - LENGTH_OF_EDGE * (width - 1) - 30) / 2;
        int startY = (HEIGHT_OF_FRAME - LENGTH_OF_EDGE * (height - 1) - 30) / 2 - 60;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                dots.add(new DotComponent(startX + LENGTH_OF_EDGE * i, startY + LENGTH_OF_EDGE * j, 30, j, i));
            }
        }
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width - 1; i++) {
                edges.add(new EdgeComponent(startX + 15 + LENGTH_OF_EDGE * i, startY + 7 + LENGTH_OF_EDGE * j, LENGTH_OF_EDGE, 16, j, i, true));
            }
        }
        for (int j = 0; j < height - 1; j++) {
            for (int i = 0; i < width; i++) {
                edges.add(new EdgeComponent(startX + 7 + LENGTH_OF_EDGE * i, startY + 15 + LENGTH_OF_EDGE * j, 16, 100, j, i, false));
            }
        }
        for (int i = 0; i < width - 1; i++) {
            for (int j = 0; j < height - 1; j++) {
                squares.add(new SquareComponent(startX + LENGTH_OF_EDGE / 2 - 15 + LENGTH_OF_EDGE * i, startY + LENGTH_OF_EDGE / 2 - 15 + LENGTH_OF_EDGE * j, 60, j, i));
            }
        }
        setJMenuBar(menu());
        SquareComponent.scoreBoardInitial(scoreBoard, score);
        dots.stream().forEach(e -> getContentPane().add(e));
        Arrays.stream(scoreBoard).forEach(e -> getContentPane().add(e));
        edges.stream().peek(e -> e.setVisible(false)).forEach(e -> getContentPane().add(e));
        squares.stream().forEach(e -> getContentPane().add(e));
        fox[0].setBounds(1500, 50, 150, 150);
        fox[1].setBounds(1500, 300, 150, 150);
        getContentPane().add(fox[0]);
        getContentPane().add(fox[1]);
        fox[0].setVisible(true);
        fox[1].setVisible(false);
        JScrollPane jScrollPane = new JScrollPane(information);
        jScrollPane.setBounds(100, 200, 300, 600);
        getContentPane().add(jScrollPane);
        getContentPane().add(timer);
        keyInput.setBounds(1500, 600, 250, 70);
        keyInput.setOpaque(true);
        keyInput.setBackground(new Color(245, 255, 183));
        keyInput.setForeground(new Color(68, 105, 21));
        keyInput.setVisible(true);
        keyInput.setFont(keyInput.getFont().deriveFont(30f));
        getContentPane().add(keyInput);
    }

    private void update(String str) {
        try {
            styledDocument.insertString(styledDocument.getLength(), str, attributeSet);
        } catch (BadLocationException error) {
            error.printStackTrace();
        }
    }

    private void addThings(EdgeComponent edge, boolean isHistory) {
        data.history.add(Strategy.getNo(edge, width, height));
        status += 1 << edges.indexOf(edge);
        node.add(0, styledDocument.getLength());
        edge.setColor(currentColor);
        edge.setFree(false);
        edge.setVisible(true);
        printEdges.add(0, edge);
        int a = edge.getRow() + 1, b = edge.getCol() + 1, c, d;
        if (edge.isHorizon()) {
            c = a;
            d = b + 1;
        } else {
            c = a + 1;
            d = b;
        }
        StyleConstants.setForeground(attributeSet, currentColor);
        try {
            styledDocument.insertString(styledDocument.getLength(),
                    player[currentPlayer] + ": \n(" + a + ", " + b + ")——(" + c + ", " + d + ")\n", attributeSet);
        } catch (BadLocationException error) {
            error.printStackTrace();
        }
        edge.repaint();
        int stepScore = 0;
        if (edge.isHorizon()) {
            if (edge.getRow() > 0) {
                if (squares.get(edge.getCol() * (height - 1) + edge.getRow() - 1).plusEdge() == 4) {
                    stepScore++;
                }
            }
            if (edge.getRow() < height - 1) {
                if (squares.get(edge.getCol() * (height - 1) + edge.getRow()).plusEdge() == 4) {
                    stepScore++;
                }
            }
        } else {
            if (edge.getCol() > 0) {
                if (squares.get((edge.getCol() - 1) * (height - 1) + edge.getRow()).plusEdge() == 4) {
                    stepScore++;
                }
            }
            if (edge.getCol() < width - 1) {
                if (squares.get(edge.getCol() * (height - 1) + edge.getRow()).plusEdge() == 4) {
                    stepScore++;
                }
            }
        }
        if (stepScore == 0) {
            currentColor = currentColor == Color.RED ? Color.BLUE : Color.RED;
            currentPlayer = currentPlayer == 1 ? 0 : 1;
        } else {
            try {
                styledDocument.insertString(styledDocument.getLength(), player[currentPlayer] + " get " + stepScore + (stepScore == 1 ? " score!\n" : " scores!\n"), attributeSet);
            } catch (BadLocationException error) {
                error.printStackTrace();
            }
            squares.stream().filter(squareComponent -> squareComponent.getEdge() == 4).
                    forEach(squareComponent -> squareComponent.printOwner(ownerName[currentPlayer], currentColor));
            score[currentPlayer] += stepScore;
            SquareComponent.scoreBoardUpdate(scoreBoard, score);
            if (score[0] + score[1] == (width - 1) * (height - 1)) {
                if (score[0] == score[1]) {
                    fox[currentPlayer ^ 1].setVisible(true);
                    StyleConstants.setForeground(attributeSet, new Color(127, 0, 127));
                    update("Congratulations!\nIt is a draw.\n");
                } else {
                    fox[0].setVisible(score[0] > score[1]);
                    fox[1].setVisible(score[1] > score[0]);
                    StyleConstants.setForeground(attributeSet, score[0] > score[1] ? Color.RED : Color.BLUE);
                    update("Congratulations!\n" + (score[0] > score[1] ? player[0] : player[1]) + " is the winner!\n");
                }
                repaint();
                return;
            }
        }
        fox[currentPlayer].setVisible(true);
        fox[currentPlayer ^ 1].setVisible(false);
        information.setCaretPosition(styledDocument.getLength());
        repaint();
        if (isMachine[currentPlayer] && !isHistory && score[0] + score[1] < (width - 1) * (height - 1)) {
            machinePlay();
        }
    }

    private void machinePlay() {
        Machine machine = new Machine();
        machine.start();
    }


    public void saveGame(String filename) {
        if (filename == null) throw new IllegalArgumentException();
        File file = new File(filename);
        int a = filename.lastIndexOf('.') + 1;
        String suffix = a == 0 ? "" : filename.substring(a);
        if ("qwq".equalsIgnoreCase(suffix)) {
            try {
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
                oos.writeObject(data);
                oos.close();
            } catch (IOException error) {
                error.printStackTrace();
            }
        } else {
            dialogContent.setText("Invalid type: " + suffix);
            dialog.setVisible(true);
        }
    }


    private void loadGame(String filename) {
        if (filename == null) throw new IllegalArgumentException();
        File file = new File(filename);
        String suffix = filename.substring(filename.lastIndexOf('.') + 1);
        if ("qwq".equalsIgnoreCase(suffix)) {
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
                Data loadData = (Data) ois.readObject();
                ois.close();
                if (loadData.user.equals(gameOwnerName)) {
                    getContentPane().removeAll();
                    dots.clear();
                    edges.clear();
                    squares.clear();
                    node.clear();
                    try {
                        styledDocument.remove(0, styledDocument.getLength());
                    } catch (BadLocationException error) {
                        error.printStackTrace();
                    }
                    Arrays.fill(score, 0);
                    scoreBoard[0].setText("0");
                    scoreBoard[1].setText("0");
                    status = currentPlayer = 0;
                    data.second = loadData.second;
                    repaint();
                    data.history.clear();
                    width = data.width = loadData.width;
                    height = data.height = loadData.height;
                    model = data.model = loadData.model;
                    isMachine = data.isMachine = loadData.isMachine;
                    printEdges.clear();
                    player = data.name = loadData.name;
                    ownerName = SquareComponent.getPrintName(player[0], player[1]);
                    initialize();
                    for (int i = 0; i < loadData.history.size(); i++) {
                        if (loadData.history.get(i) >= 0) addThings(edges.get(loadData.history.get(i)), true);
                        else undo();
                    }
                } else {
                    dialogContent.setText("It's not your game");
                    dialog.setVisible(true);
                }
            } catch (IOException | ClassNotFoundException error) {
                error.printStackTrace();
            }
        }
    }

    private void undo() {
        if (printEdges.size() != 0) {
            data.history.add(-1);
            StyleConstants.setForeground(attributeSet, Color.LIGHT_GRAY);
            styledDocument.setCharacterAttributes(node.get(0), styledDocument.getLength() - node.get(0), attributeSet, false);
            EdgeComponent deleteEdge = printEdges.get(0);
            deleteEdge.setVisible(false);
            deleteEdge.setFree(true);
            currentColor = deleteEdge.getColor();
            status -= 1 << edges.indexOf(deleteEdge);
            int stepScore = 0;
            if (deleteEdge.isHorizon()) {
                if (deleteEdge.getRow() > 0) {
                    if (squares.get(deleteEdge.getCol() * (height - 1) + deleteEdge.getRow() - 1).minusEdge() == -2) {
                        stepScore--;
                    }
                }
                if (deleteEdge.getRow() < height - 1) {
                    if (squares.get(deleteEdge.getCol() * (height - 1) + deleteEdge.getRow()).minusEdge() == -2) {
                        stepScore--;
                    }
                }
            } else {
                if (deleteEdge.getCol() > 0) {
                    if (squares.get((deleteEdge.getCol() - 1) * (height - 1) + deleteEdge.getRow()).minusEdge() == -2) {
                        stepScore--;
                    }
                }
                if (deleteEdge.getCol() < width - 1) {
                    if (squares.get(deleteEdge.getCol() * (height - 1) + deleteEdge.getRow()).minusEdge() == -2) {
                        stepScore--;
                    }
                }
            }
            if (currentColor == Color.RED) {
                currentPlayer = 0;
            } else {
                currentPlayer = 1;
            }
            fox[currentPlayer].setVisible(true);
            fox[currentPlayer ^ 1].setVisible(false);
            if (stepScore != 0) {
//                            try {
//                                styledDocument.insertString(styledDocument.getLength(), player[currentPlayer] + " get " + stepScore + (stepScore == 1 ? " score!\n" : " scores!\n"), attributeSet);
//                            } catch (BadLocationException error) {
//                                error.printStackTrace();
//                            }
                squares.stream().filter(squareComponent -> squareComponent.getEdge() == -2)
                        .forEach(squareComponent -> squareComponent.deleteOwner());
                score[currentPlayer] += stepScore;
                SquareComponent.scoreBoardUpdate(scoreBoard, score);
            }
            printEdges.remove(0);
            node.remove(0);
        }


    }


    static void setDialog(Dialog dialog, JLabel dialogContent) {
        dialogContent.setSize(450, 200);
        dialogContent.setFont(dialogContent.getFont().deriveFont(25f));
        dialog.setSize(450, 200);
        dialog.setLocationRelativeTo(null);
        dialog.add(dialogContent);
        dialog.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dialog.dispose();
            }
        });
    }

    public void setHighLight() {
        Color color = new Color(245, 255, 183);
        int[] co = Arrays.stream(keyInput.getText().split("\\D+")).skip(1)
                .mapToInt(a -> Integer.parseInt(a) - 1).toArray();
        switch (co.length) {
            case 0:
                dots.forEach(e -> e.setColor(Color.BLACK));
                break;
            case 1:
                dots.stream().filter(e -> e.getRow() == co[0]).forEach(e -> e.setColor(color));
                break;
            case 2:
                dots.forEach(d -> {
                    if (d.getRow() == co[0] && d.getCol() == co[1]) d.setColor(color);
                    else d.setColor(Color.BLACK);
                });
                break;
            case 3:
                dots.stream().filter(e -> e.getRow() == co[2]).forEach(e -> e.setColor(color));
                break;
            case 4:
                dots.forEach(d -> {
                    if ((d.getRow() == co[2]) && (d.getCol() == co[3]) || (d.getRow() == co[0] && d.getCol() == co[1]))
                        d.setColor(color);
                    else d.setColor(Color.BLACK);
                });
                break;
        }
        repaint();
    }


}