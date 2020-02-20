package demo2;


import java.io.*;
import java.util.*;

final class Strategy implements Serializable {
    private static final long serialVersionUID = 2;
    private static int n, s;//n:卷数；s：

    static class Status implements Serializable {
        private static final long serialVersionUID = 3;
        private byte score, evaluate, strategy;
        private int num;//棋盘状态

        public byte getScore() {
            return score;
        }

        private Status(int i) {
            num = i;
        }

        public Status() {}

        public void setScore(byte score) {
            this.score = score;
        }

        public byte getEvaluate() {
            return evaluate;
        }

        public void setEvaluate(byte evaluate) {
            this.evaluate = evaluate;
        }

        public void setStrategy(byte strategy) {
            this.strategy = strategy;
        }

        public void setNum(int num) {
            this.num = num;
        }

        int getNum() {
            return num;
        }

        byte getStrategy() {
            return strategy;
        }

        private boolean isCloseAt(SquareComponent square, EdgeComponent[] edges, int shape) {
            return Arrays.stream(edges).filter(edge -> isAdjacent(square, edge)).count() == shape;
        }

        private void setStrategy(Status[][] succStatus, SquareComponent[] squares, EdgeComponent[] edges, int shape, int width, int height) {
            Status best = null, localBest;
            for (int i = 0; i < succStatus.length; i++) {
                localBest = Arrays.stream(succStatus[i]).filter(s -> (s.num & num) == num)
                        .max(Comparator.comparingInt(s -> {
                            if (score > 0) return s.calculate(this);
                            else return s.calculate(this, squares, edges, shape, width, height);
                        })).orElse(null);
                if (localBest != null)
                    if (best == null || best.calculate(this) < localBest.calculate(this)) best = localBest;
            }
            evaluate = (byte) best.calculate(this);
            for (int j = best.num - num, c = 0; j > 0; j >>= 1, c++) {
                if (j == 1) strategy = (byte) c;
            }
        }

        private int calculate(Status status, SquareComponent[] squares, EdgeComponent[] edges, int shape, int width, int height) {
            int j = num - status.num, i = 0;
            for (; j > 1; j >>= 1, i++) ;
            EdgeComponent edge = edges[i];//ed:后继状态all edges
            EdgeComponent[] ed = Arrays.stream(edges).filter(e -> (1 << getNo(e, width, height) & num) > 0).toArray(EdgeComponent[]::new);
            status.score = (byte) (score + Arrays.stream(squares).filter(square -> isAdjacent(square, edge))
                    .filter(square -> isCloseAt(square, ed, shape)).count());
            if (score < status.score) return evaluate + status.score - score;
            else return score - evaluate;
        }

        private int calculate(Status status) {
            if (score < status.score) return evaluate + status.score - score;
            else return score - evaluate;
        }

        public String toString() {
            return num + "-" + strategy + "-" + evaluate;
        }
    }


    public static void main(String[] args) {
        int shape = 4, width = 8, height = 2;
        ArrayList<EdgeComponent> preEdges = new ArrayList<>();
        ArrayList<SquareComponent> preSquares = new ArrayList<>();
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width - 1; i++) {
                preEdges.add(new EdgeComponent(0, 0, 0, 0, j, i, true));
            }
        }
        for (int j = 0; j < height - 1; j++) {
            for (int i = 0; i < width; i++) {
                preEdges.add(new EdgeComponent(0, 0, 0, 0, j, i, false));
            }
        }
        EdgeComponent[] edges = new EdgeComponent[preEdges.size()];
        edges = preEdges.toArray(edges);
        for (int i = 0; i < width - 1; i++) {
            for (int j = 0; j < height - 1; j++) {
                preSquares.add(new SquareComponent(0, 0, 0, j, i));
            }
        }
        SquareComponent[] squares = new SquareComponent[preSquares.size()];
        squares = preSquares.toArray(squares);
        int pointer = edges.length;//present edges
        String URL = "src/demo2/strategy/" + shape + "-" + width + "-" + height;
        File folder = new File(URL);
        if (!folder.exists()) folder.mkdir();
        Status[] statuses;
        Status[][] succStatus = new Status[0][0];
        for (Strategy strategy = new Strategy(); pointer >= 0; ) {
            System.out.print("strategy\t" + shape + "-" + width + "-" + height + "\t" + pointer + "(" + n + ")\t");
            statuses = strategy.calculate((byte) shape, pointer, edges, squares, succStatus, width, height);
            saveStrategy(new File(URL + "/" + pointer + "(" + (n - 1) + ").stg"), statuses);
            //finished?
            if ((statuses.length & 31) != 0) {
                succStatus = new Status[n][];
                for (int i = 0; i < n; i++)
                    succStatus[i] = loadStrategy(new File(URL + "/" + pointer + "(" + i + ").stg"));
                pointer--;
                n = s = 0;
            }
        }
    }

    private Status[] calculate(byte shape, int pointer, EdgeComponent[] edges, SquareComponent[] squares, Status[][] succStatus, int width, int height) {
        int step = edges.length;
        int node = 1 << edges.length;
        if (pointer == step) {
            Status[] status = new Status[1];
            status[0] = new Status(node - 1);
            n++;
            System.out.print("-Prepared.\t");
            status[0].evaluate = status[0].score = 0;
            status[0].strategy = -1;
            System.out.print("-Set.\t");
            return status;
        } else {
            ArrayList<Status> preStatus = new ArrayList<>(1 << 17);
            for (int d = 0, c = 0; s < node && c < 1 << 17; s++, d = 0) {
                for (int j = s; j > 0; j &= j - 1, d++) ;
                if (d == pointer) {
                    preStatus.add(new Status(s));
                    c++;
                }
            }
            n++;
            System.out.print("-Prepared.\t");
            Status[] status = preStatus.parallelStream().peek(s -> s.setStrategy(succStatus, squares, edges, shape, width, height)).toArray(Status[]::new);
            System.out.print("-Set.\t");
            return status;
        }
    }

    static void saveStrategy(File file, Status[] statuses) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(statuses);
            System.out.println("-Finished.\t" + s);
            oos.close();
        } catch (IOException error) {
            error.printStackTrace();
        }
    }

    static void saveStrategy(int width, int height, int pointer, int part, Status[] statuses) {
        File folder = new File("src/demo2/strategy/4-" + width + "-" + height + "/");
        if (!folder.exists()) folder.mkdir();
        try {
            File file = new File("src/demo2/strategy/4-" + width + "-" + height + "/" + pointer + "(" + part + ").stg");
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(statuses);
        } catch (IOException e) {
        }
    }


    static Status[] loadStrategy(File file) {
        try {
            if (file.exists()) {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
                Status[] statuses = (Status[]) ois.readObject();
                ois.close();
                return statuses;
            }
        } catch (IOException | ClassNotFoundException error) {
            error.printStackTrace();
        }
        return new Status[0];
    }


    static Status[] loadStrategy(int width, int height, int existEdges, int part) {
        File file = new File("src/demo2/strategy/4-" + width + "-" + height + "/" + existEdges
                + "(" + part + ").stg");
        try {
            if (file.exists()) {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
                Status[] statuses = (Status[]) ois.readObject();
                ois.close();
                return statuses;
            }
        } catch (IOException | ClassNotFoundException error) {
        }
        return new Status[0];
    }


    public static boolean isAdjacent(SquareComponent square, EdgeComponent edge) {
        return edge.getCol() == square.getCol() | edge.getCol() == square.getCol() + 1 |
                edge.getRow() == square.getRow() | edge.getRow() == square.getRow() + 1;
    }

    static int getNo(EdgeComponent edge, int width, int height) {
        if (edge.isHorizon()) return edge.getRow() * (width - 1) + edge.getCol();
        else return height * (width - 1) + edge.getRow() * width + edge.getCol();
    }


}