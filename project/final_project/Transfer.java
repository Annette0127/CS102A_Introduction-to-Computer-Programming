package demo2;

import java.io.*;

import boxeGame.Strategy;

public class Transfer {
    public static void main(String[] args) {
        int existEdges = 0;
        int part = 0;
        for (int k = 0; k < 25; k++) {
            for (int j = 0; ; j++) {
                File file = new File("src/boxeGame/Data/strategy/4-" + 3 + "-" + 3 + "/" + existEdges
                        + "(" + part + ").stg");
                if (file.exists()) {
                    try {
                        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
                        boxeGame.Strategy.Status[] statuses = (boxeGame.Strategy.Status[]) ois.readObject();
                        ois.close();
                        demo2.Strategy.Status[] statusesN = new demo2.Strategy.Status[statuses.length];
                        for (int i = 0; i < statuses.length; i++) {
                            statusesN[i] = new demo2.Strategy.Status();
                            statusesN[i].setScore(statuses[i].getScore());
                            statusesN[i].setStrategy(statuses[i].getStrategy());
                            statusesN[i].setEvaluate(statuses[i].getEvaluate());
                        }
                        File fileN = new File("src/demo2/strategy/4-" + 4 + "-" + 4 + "/" + existEdges
                                + "(" + part + ").stg");
                        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileN));
                        oos.writeObject(statusesN);
                        oos.close();
                    } catch (IOException | ClassNotFoundException error) {
                    }
                } else {
                    break;
                }

                part++;
            }
            existEdges++;
            part = 0;
        }
    }
}
