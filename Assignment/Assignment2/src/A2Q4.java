public class A2Q4 {
    public static void main(String[] args) {
        String s="+"+args[0];
        String[] num = s.split("[-+*/()]+");
        String[] ope1 = s.split("[0-9]+[.]?[0-9]?");
        String[] ope = new String[4];
        if (ope1.length != 4) {
            ope[0] = ope1[0];
            ope[1] = ope1[1];
            ope[2] = ope1[2];
            ope[3] = "a";
        } else {
            ope[0] = ope1[0];
            ope[1] = ope1[1];
            ope[2] = ope1[2];
            ope[3] = ope1[3];
        }

        double[] n = new double[num.length];
        double t;
        double re;
        for (int i = 1; i < num.length; i++) {
            n[i] = Double.parseDouble(num[i]);
        }
        String f = ope[0].substring(1);
        if (f.equals("(") && ope[3].equals(")")) {
            if (ope[1].equals("*") || ope[1].equals("/")) {
                if (ope[1].equals("*")) {
                    t = n[1] * n[2];
                    re = operation(t, n[3], ope[2]);
                } else {
                    t = n[1] / n[2];
                    re = operation(t, n[3], ope[2]);
                }
            } else if (ope[2].equals("*") || ope[2].equals("/")) {
                if (ope[2].equals("*")) {
                    t = n[2] * n[3];
                    re = operation(n[1], t, ope[1]);
                } else {
                    t = n[2] / n[3];
                    re = operation(n[1], t, ope[1]);
                }
            } else {
                t = operation(n[1], n[2], ope[1]);
                re = operation(t, n[3], ope[2]);
            }
        } else if (f.equals("(")) {
            t = operation(n[1], n[2], ope[1]);
            re = operation(t, n[3], ope[2].substring(1, 2));
        } else if (ope[3].equals(")")) {
            t = operation(n[2], n[3], ope[2]);
            re = operation(n[1], t, ope[1].substring(0, 1));
        } else {
            if (ope[1].equals("*") || ope[1].equals("/")) {
                if (ope[1].equals("*")) {
                    t = n[1] * n[2];
                    re = operation(t, n[3], ope[2]);
                } else {
                    t = n[1] / n[2];
                    re = operation(t, n[3], ope[2]);
                }
            } else if (ope[2].equals("*") || ope[2].equals("/")) {
                if (ope[2].equals("*")) {
                    t = n[2] * n[3];
                    re = operation(n[1], t, ope[1]);
                } else {
                    t = n[2] / n[3];
                    re = operation(n[1], t, ope[1]);
                }
            } else {
                t = operation(n[1], n[2], ope[1]);
                re = operation(t, n[3], ope[2]);
            }
        }
        System.out.printf("%s=%.2f", args[0], re);

    }

    public static double operation(double n1, double n2, String op) {
        double a;
        if (op.equals("+")) {
            a = n1 + n2;
        } else if (op.equals("-")) {
            a = n1 - n2;
        } else if (op.equals("*")) {
            a = n1 * n2;
        } else {
            a = n1 / n2;
        }
        return a;
    }


}

