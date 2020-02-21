public class A2Q4V {
    //这个之所以您的程序比狐狸复杂，是因为您想得太碎了，您以后可以首先把程序的大的框架架出来，再往里面填东西：
    //比如具体怎么循环，循环的头尾怎么处理；
    //条件语句具体判断什么，里面具体怎么处理；
    //上来就顺着写的话，就容易写碎、写漏之类QwQ；
    //这个程序，哪怕说运算符的问题不能统一地解决，需要分类，也应该比您现在这个短并且结构清晰。因为运算符的问题是具体处理的问题。
    //嗯白白不要沮丧嗷~狐狐只是教您怎么写得更快更好啦~不管怎么说白白这些程序都没问题的嗷~只是还可以再改进嗷~
    //喜欢您嗷嗷呜~~
    public static void main(String[] args) {
        String s = "+" + args[0];
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
        //这个处理是没有必要的，因为如果ope1[3]存在，它一定是")"。
        //但是我们只需要判断前三个位置有没有括号就知道最后有没有了（前三个有1个就有，偶数个就没有）。
        //所以我们的分析不需要ope1[3]。
        //这里可以把ope删掉，把ope1改成ope。
        //如果真的要这样做，前三句相同的内容也改提出去[破涕为笑]

        double[] n = new double[num.length];
        //一定是三个数，所以可以把num.length改成4，避免调用其他类的函数。
        double t;
        double re;
        //t和re是没有必要的，我们可以把第一次运算的结果存在n[2]，第二次的存在n[1]（或n[0]），最后输出n[1]（或n[0]）就行。
        for (int i = 1; i < num.length; i++) {
            //可以把num.length改成4。
            n[i] = Double.parseDouble(num[i]);
        }
        String f = ope[0].substring(1);
        if (f.equals("(") && ope[3].equals(")")) {
            //之前说过ope[3]没有必要，但是在程序里我姑且跟着您，最后再讲怎么不用ope[3]。
            if (ope[1].equals("*") || ope[1].equals("/")) {
                if (ope[1].equals("*")) {
                    t = n[1] * n[2];
                    re = operation(t, n[3], ope[2]);
                } else {
                    t = n[1] / n[2];
                    re = operation(t, n[3], ope[2]);
                }
                //这个if用没有必要啊，方法不是一次性的~像这样就好了：
                //t = operation(n[1], n[2], ope[1]);
                //re = operation(t, n[3], ope[2]);
                //甚至还可以嵌套来避免t的声明：
                //re = operation(operation(n[1], n[2], ope[1]), n[3], ope[2])；
            } else if (ope[2].equals("*") || ope[2].equals("/")) {
                if (ope[2].equals("*")) {
                    t = n[2] * n[3];
                    re = operation(n[1], t, ope[1]);
                } else {
                    t = n[2] / n[3];
                    re = operation(n[1], t, ope[1]);
                }
                //if没有必要，仿上。
            } else {
                t = operation(n[1], n[2], ope[1]);
                re = operation(t, n[3], ope[2]);
                //可以嵌套。
            }
        } else if (f.equals("(")) {
            t = operation(n[1], n[2], ope[1]);
            re = operation(t, n[3], ope[2].substring(1, 2));
            //ope[2]最多2位，所以第二个参数可以省去：ope[2].substring(1)表示截取从索引为1的位置一直到末尾。
            //可以嵌套。
        } else if (ope[3].equals(")")) {
            t = operation(n[2], n[3], ope[2]);
            re = operation(n[1], t, ope[1].substring(0, 1));
            //可以嵌套。
        } else {
            //这个else内的东西和一开始那部分一样（那个是括号括了三个数，这个是没括号），所以就不说了。
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
        //之前在微信里说过，运算符的问题完全可以直接解决，不需要分类；
        //所以这个计算器要解决的核心的问题是：先算哪两个数，程序就是这样的结构：
        //if ([先算前两个]) {
        //n[2] = operation(n[1], n[2], ope[1].substring(0, 1));
        //【ope[1]的运算符一定是第一个。】【不管第一次算哪两个，n[2]都被用掉了。】
        //n[0] = operation(n[2], n[3], ope[2].substring(ope[1].length - 1));
        //【ope[2]的运算符一定是最后一个。】
        //【当然也可以写嵌套表达式：】
        //n[0] = operation(operation(n[1], n[2], ope[1].substring(0, 1)), n[3], ope[2].substring(ope[1].length - 1));
        //} else {
        //n[2] = operation(n[2], n[3], ope[2].substring(ope[1].length - 1));
        //n[0] = operation(n[1], n[2], ope[1].substring(0, 1));
        //【当然也可以写嵌套表达式：】
        //n[0] = operation(n[1], operation(n[2], n[3], ope[2].substring(ope[1].length - 1)), ope[1].substring(0, 1));
        //}
        //【最后输出n[0]】

        //那么，什么时候要先算前两个呢？
        //1.前两个被括住的；
        //2.没有有意义括号（就是指那个从头括到尾的），且第一个运算是乘除；
        //3.没有有意义括号（就是指那个从头括到尾的），且没有乘除；
        //对吧？
        //那么我们来想想这些要怎么判断。

        //情况1。
        //情况1有一个特征，第二个运算符上有括号（就只说两个运算符，头尾不管），而且这两件事是等价的：
        //因为没有单独括住一个数的无赖输入，所以如果第二个运算符上有括号，一定是右括号，一定括住前两个数。
        //然后因为ope[1]一定有一个四则运算符，所以有括号等价于它不止一个字符。所以情况1是：
        //ope[1].length > 1;

        //情况2。
        //同情况1，我们其实可以知道，后两个数被括住等价于
        //ope[2].length > 2;
        //所以没有有意义括号等价于
        //ope[1].length == 1 && ope[2].length == 1;
        //情况2还要求第一个运算是乘除。如果用equals函数判断，那么这个判断已经自带ope[1].length == 1了。
        //所以情况2是：
        //(ope[1].equals("*") || ope[1].equals("/")) && ope[2].length == 1;

        //情况3
        //就是两个都是加减对吧，那很简单：
        //(ope[1].equals("+") || ope[1].equals("-")) && (ope[2].equals("+") || ope[2].equals("-"));
        //同样这已经自带了括号判断了。

        //最后要把它们三个用 || 连接，得到：
        //ope[1].length > 1 || ((ope[1].equals("*") || ope[1].equals("/")) && ope[2].length == 1) || (ope[1].equals("+") || ope[1].equals("-")) && (ope[2].equals("+") || ope[2].equals("-"));
        //看上去是不是很恐怖？一次一次地判断就好了：
        //boolean p = ope[1].length > 1;
        //p = p || ((ope[1].equals("*") || ope[1].equals("/")) && ope[2].length == 1);
        //p = p || ((ope[1].equals("+") || ope[1].equals("-")) && (ope[2].equals("+") || ope[2].equals("-")));
        //然后把这个p放到上面if的条件里，就可以了。
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
        //可以避免声明a，把return放进去，如下：
        //if (op.equals("+")) {
        // return n1 + n2;
        //} else if (op.equals("-")) {
        //return n1 - n2;
        //} else if (op.equals("*")) {
        //return n1 * n2;
        //} else {
        //return n1 / n2;
        //}
        //当然还可以利用char同时也是整数的性质，写成三目以缩短行数，但是实际上意义不大（除了压缩）。
        //如果您想知道的话来问狐狸。
    }
}