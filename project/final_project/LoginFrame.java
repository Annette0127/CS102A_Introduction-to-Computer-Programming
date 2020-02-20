package demo2;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Arrays;
import java.util.Enumeration;

public class LoginFrame extends JFrame {
    private int model = 0;
    private int levelModel = 0;
    private String name, password, anotherName, name1, name2, gameOwnerName;
    private boolean isMachine1, isMachine2;
    private int widthG = 2, heightG = 2;
    private Integer[] comboBox = {2, 3, 4, 5, 6, 7, 8, 9};
    private Component[] components1 = {new JLabel("Dots and Boxes", SwingConstants.CENTER),
            new JButton("Register"), new JButton("Log in")};
    private Component[] registerComponent = {new JLabel("Name :"), new JLabel("Password :"),
            new JLabel("Confirm Password :"), new JTextField(), new JPasswordField(), new JPasswordField(),
            new JButton("Register!"), new JButton("Back"),
            new JLabel("The two passwords are different!!!"),
            new JLabel("<html>User name: Use only letters and digits, at most 12 chars.<br>" +
                    "Password: Use only letters, digits and ! ? . % + = ^ $ & # - @, between 3 and 18 chars.</html>"),
            components1[0]};
    private Component[] logInsComponent = {new JLabel("Name :"), new JLabel("Password :"),
            new JTextField(), new JPasswordField(), new JButton("Log in!"), new JButton("Back"), components1[0]};
    private Component[] chooseComponent = {new JButton("Machine  vs  Machine"), new JButton("Machine  vs  Human"),
            new JButton("Human  vs  Human"), components1[0]};
    private Component[] sizeComponent = {new JButton("Back"), new JButton("New Game"), new JComboBox<>(comboBox),
            new JComboBox<>(comboBox), new JLabel("Width :                    Height :"),
            new JRadioButton("Play first?"),
            new JLabel("Name of another player :"), new JTextField(), new JLabel("Who plays first?"),
            new JRadioButton("You"), new JRadioButton("Another player"),
            components1[0], new JRadioButton("Easy"), new JRadioButton("Hard")};
    private JLabel dialogContent = new JLabel("", SwingConstants.CENTER);
    private Dialog dialog = new Dialog(this, "Warning");

    private Action action = new Action();
    private int window = 0;

    class Action implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String cmd = e.getActionCommand();
            switch (cmd) {
                case "Register":
                    if (window == 1) {
                        Arrays.stream(components1).peek(a -> a.setVisible(false)).forEach(a -> a.repaint());
                        initialRegister();
                    }
                    break;
                case "Log in":
                    if (window == 1) {
                        Arrays.stream(components1).peek(b -> b.setVisible(false)).forEach(Component::repaint);
                        initialLogIn();
                    }
                    break;
                case "Back":
                    if (model != 0) {
                        Arrays.stream(sizeComponent).peek(a -> a.setVisible(false)).forEach(Component::repaint);
                        model = 0;
                        chooseGame();
                    } else {
                        if (window == 2) {
                            Arrays.stream(registerComponent).peek(a -> a.setVisible(false)).forEach(Component::repaint);
                        } else {
                            Arrays.stream(logInsComponent).peek(a -> a.setVisible(false)).forEach(Component::repaint);
                        }
                        window = 1;
                        initialize();
                    }
                    break;
                case "Log in!":
                    name = ((JTextField) logInsComponent[2]).getText();
                    password = charToString(((JPasswordField) logInsComponent[3]).getPassword());
                    if (!Users.login(name, password)) {
                        dialogContent.setText("Wrong name or password");
                        dialog.setVisible(true);
                    } else {
                        Arrays.stream(logInsComponent).peek(a -> a.setVisible(false)).forEach(Component::repaint);
                        gameOwnerName = name;
                        chooseGame();
                    }
                    break;
                case "Register!":
                    name = ((JTextField) registerComponent[3]).getText();
                    password = charToString(((JPasswordField) registerComponent[4]).getPassword());
                    String password1 = charToString(((JPasswordField) registerComponent[5]).getPassword());
                    if (password.equals(password1)) {
                        if (Users.checkName(name) && Users.checkPassword(password)) {
                            if (Users.register(name, password)) {
                                Arrays.stream(registerComponent).peek(a -> a.setVisible(false)).forEach(Component::repaint);
                                gameOwnerName = name;
                                chooseGame();
                            } else {
                                showDialog("This name has been occupied");
                            }
                        } else {
                            if (!Users.checkName(name)) {
                                dialogContent.setText("Invalid name");
                            } else {
                                dialogContent.setText("Invalid password");
                            }
                            dialog.setVisible(true);
                        }
                    } else {
                        showDialog("The two Password are different");
                    }
                    break;
                case "Machine  vs  Machine":
                    model = 1;
                    Arrays.stream(chooseComponent).peek(a -> a.setVisible(false)).forEach(Component::repaint);
                    chooseSize();
                    name1 = "Dot";
                    name2 = "Box";
                    isMachine2 = isMachine1 = true;
                    break;
                case "Machine  vs  Human":
                    model = 2;
                    Arrays.stream(chooseComponent).peek(a -> a.setVisible(false)).forEach(Component::repaint);
                    chooseSize();
                    name1 = anotherName = "Machine";
                    name2 = name;
                    isMachine2 = false;
                    isMachine1 = true;
                    break;
                case "Human  vs  Human":
                    model = 3;
                    isMachine2 = isMachine1 = false;
                    Arrays.stream(chooseComponent).peek(a -> a.setVisible(false)).forEach(Component::repaint);
                    chooseSize();
                    break;
                case "New Game":
                    if (model == 3) {
                        if (((JRadioButton) sizeComponent[9]).isSelected() || ((JRadioButton) sizeComponent[10]).isSelected()) {
                            anotherName = ((JTextField) sizeComponent[7]).getText();
                            if (Users.checkName(anotherName) && !anotherName.equals(name)) {
                                if (((JRadioButton) sizeComponent[9]).isSelected()) {
                                    name2 = anotherName;
                                } else {
                                    name1 = anotherName;
                                }
                                MainFrame.startGame(widthG, heightG, gameOwnerName, name1, name2, isMachine1, isMachine2, levelModel);
                            } else {
                                showDialog("Wrong form of name");
                            }
                        } else {
                            showDialog("Please select the first player");
                        }
                    } else {
                        if (!new File("src/demo2/strategy/4-" + widthG + "-" + heightG).exists() && levelModel == 2) {
                            showDialog("This Model is not supported");
                        } else {
                            if (model == 1) {
                                levelModel = 1;
                            }
                            if (model == 2 && levelModel == 0) {
                                showDialog("Please select the difficult mode");
                            } else {
                                MainFrame.startGame(widthG, heightG, gameOwnerName, name1, name2, isMachine1, isMachine2, levelModel);

                            }
                        }
                    }
                    break;
                case "You":
                    name1 = name;
                    name2 = anotherName;
                    break;
                case "Another player":
                    name1 = anotherName;
                    name2 = name;
                    break;
                case "Play first?":
                    if (((JRadioButton) sizeComponent[5]).isSelected()) {
                        name1 = name;
                        name2 = anotherName;
                        isMachine1 = false;
                        isMachine2 = true;
                    } else {
                        name1 = anotherName;
                        name2 = name;
                        isMachine1 = true;
                        isMachine2 = false;
                    }
                    break;
                case "Easy":
                    levelModel = 1;
                    break;
                case "Hard":
                    levelModel = 2;
                    break;
            }
        }
    }

    class Item implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent e) {
            if (((JComboBox) e.getSource()).getX() < 600) widthG = (int) e.getItem();
            else heightG = (int) e.getItem();
        }
    }

    class Key extends KeyAdapter {
        public void keyReleased(KeyEvent event) {
            if (window == 2) {
                char[] password0 = ((JPasswordField) registerComponent[4]).getPassword();
                char[] password1 = ((JPasswordField) registerComponent[5]).getPassword();
                registerComponent[8].setVisible(!Arrays.equals(password0, password1));
                Arrays.fill(password0, (char) 0);
                Arrays.fill(password1, (char) 0);
            }
        }

    }


    LoginFrame() {
        initialization();
        initialize();
    }

    public static void main(String[] args) {
        setGlobalFont();
        //LoginFrame loginFrame = new LoginFrame();
      MainFrame.startGame(4,4,"G","A","B",true,false,2);//for test
    }

    void initialization() {
        components1[0].setBounds(10, 20, 1080, 100);
        components1[0].setFont(new Font("Old English Text MT", Font.BOLD, 100));
        components1[1].setBounds(280, 500, 150, 50);
        components1[2].setBounds(620, 500, 150, 50);
        ((JButton) components1[1]).addActionListener(action);
        ((JButton) components1[2]).addActionListener(action);

        registerComponent[0].setBounds(230, 230, 200, 50);
        registerComponent[1].setBounds(200, 330, 200, 50);
        registerComponent[2].setBounds(160, 430, 400, 50);

        registerComponent[3].setBounds(430, 230, 300, 40);
        registerComponent[4].setBounds(430, 330, 300, 40);
        registerComponent[5].setBounds(430, 430, 300, 40);

        registerComponent[6].setBounds(700, 560, 150, 50);
        registerComponent[7].setBounds(300, 560, 150, 50);

        registerComponent[8].setBounds(430, 370, 500, 50);
        registerComponent[9].setBounds(75, 470, 980, 100);
        registerComponent[9].setFont(registerComponent[9].getFont().deriveFont(20f));
        ((JButton) registerComponent[6]).addActionListener(action);
        ((JButton) registerComponent[7]).addActionListener(action);

        logInsComponent[0].setBounds(230, 280, 200, 50);
        logInsComponent[1].setBounds(200, 380, 200, 50);

        logInsComponent[2].setBounds(430, 280, 300, 40);
        logInsComponent[3].setBounds(430, 380, 300, 40);
        logInsComponent[4].setBounds(700, 540, 150, 50);
        logInsComponent[5].setBounds(300, 540, 150, 50);
        ((JButton) logInsComponent[4]).addActionListener(action);
        ((JButton) logInsComponent[5]).addActionListener(action);

        chooseComponent[0].setBounds(300, 300, 450, 50);
        chooseComponent[1].setBounds(300, 400, 450, 50);
        chooseComponent[2].setBounds(300, 500, 450, 50);
        ((JButton) chooseComponent[0]).addActionListener(action);
        ((JButton) chooseComponent[1]).addActionListener(action);
        ((JButton) chooseComponent[2]).addActionListener(action);

        ButtonGroup group = new ButtonGroup();
        group.add((JRadioButton) sizeComponent[9]);
        group.add((JRadioButton) sizeComponent[10]);
        ButtonGroup modelgroup = new ButtonGroup();
        modelgroup.add((JRadioButton) sizeComponent[12]);
        modelgroup.add((JRadioButton) sizeComponent[13]);
        sizeComponent[0].setBounds(630, 540, 250, 50);
        sizeComponent[1].setBounds(200, 540, 250, 50);

        sizeComponent[2].setBounds(320, 300, 100, 50);
        sizeComponent[3].setBounds(620, 300, 100, 50);

        sizeComponent[4].setBounds(200, 300, 500, 50);
//        M-H
        sizeComponent[5].setBounds(430, 400, 200, 50);
//        H-H
        sizeComponent[6].setBounds(200, 400, 450, 50);
        sizeComponent[7].setBounds(550, 400, 250, 40);

        sizeComponent[8].setBounds(100, 450, 300, 50);
        sizeComponent[9].setBounds(400, 450, 100, 50);
        sizeComponent[10].setBounds(600, 450, 300, 50);
        sizeComponent[12].setBounds(300, 450, 100, 50);
        sizeComponent[13].setBounds(700, 450, 100, 50);
        ((JButton) sizeComponent[0]).addActionListener(action);
        ((JButton) sizeComponent[1]).addActionListener(action);

        ((JPasswordField) registerComponent[4]).setEchoChar('*');
        ((JPasswordField) registerComponent[5]).setEchoChar('*');
        ((JPasswordField) logInsComponent[3]).setEchoChar('*');
        Key key = new Key();
        registerComponent[4].addKeyListener(key);
        registerComponent[5].addKeyListener(key);
        Item item = new Item();
        ((JComboBox) sizeComponent[2]).addItemListener(item);
        ((JComboBox) sizeComponent[3]).addItemListener(item);

        ((JRadioButton) sizeComponent[5]).addActionListener(action);
        ((JRadioButton) sizeComponent[9]).addActionListener(action);
        ((JRadioButton) sizeComponent[10]).addActionListener(action);
        ((JRadioButton) sizeComponent[12]).addActionListener(action);
        ((JRadioButton) sizeComponent[13]).addActionListener(action);

        Arrays.stream(components1).peek(e -> e.setVisible(true)).forEach(e -> getContentPane().add(e));
        Arrays.stream(registerComponent).peek(e -> e.setVisible(false)).forEach(e -> getContentPane().add(e));
        Arrays.stream(logInsComponent).peek(e -> e.setVisible(false)).forEach(e -> getContentPane().add(e));
        Arrays.stream(chooseComponent).peek(e -> e.setVisible(false)).forEach(e -> getContentPane().add(e));
        Arrays.stream(sizeComponent).peek(e -> e.setVisible(false)).forEach(e -> getContentPane().add(e));
        components1[0].setVisible(true);

        setDialog(dialog, dialogContent);
    }

    void initialize() {
        setTitle("Dot and boxes");
        setSize(1080, 720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);
        window = 1;
        Arrays.stream(components1).forEach(e -> e.setVisible(true));
        repaint();
    }

    void initialRegister() {
        Arrays.stream(registerComponent).forEach(e -> e.setVisible(true));
        registerComponent[8].setVisible(false);
        repaint();
        window = 2;
    }

    void initialLogIn() {
        Arrays.stream(logInsComponent).forEach(e -> e.setVisible(true));
        repaint();
        window = 3;
    }

    void chooseGame() {
        Arrays.stream(chooseComponent).forEach(e -> e.setVisible(true));
        window = 4;
    }

    void chooseSize() {
        Arrays.stream(sizeComponent).forEach(e -> e.setVisible(false));
        for (int i = 0; i < 5; i++) {
            sizeComponent[i].setVisible(true);
        }
        if (model == 2) {
            sizeComponent[12].setVisible(true);
            sizeComponent[13].setVisible(true);
            sizeComponent[5].setVisible(true);
        } else if (model == 3) {
            for (int i = 6; i < 11; i++) {
                sizeComponent[i].setVisible(true);
            }
        }
        components1[0].setVisible(true);
        repaint();
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

    private void showDialog(String content) {
        dialogContent.setText(content);
        dialog.setVisible(true);
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

    public String charToString(char[] a) {
        return String.valueOf(a);
    }
}