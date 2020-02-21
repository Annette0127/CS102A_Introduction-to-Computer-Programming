public class A2Q3_2V {
    //这个程序之所以狐狸的比您短很多，有两个方面，一个是狐狸没有死守着数组（当然我猜您是觉得题目要求数组了~而且这个不是主要原因~）；
    //另一个就是狐狸用运算处理均匀的分级，而您用了大量的if-else[破涕为笑]。
    //这是技巧性的问题。
    public static void main(String[] args) {
        int n = args.length;
        if (0 == n || 0 != n % 2) {
            System.out.println("Please input the right format of score and credit hour in pair,eg.95 2 88 3");
            return;
        }
        Double[] gpa = {4.00, 3.94, 3.85, 3.73, 3.55, 3.32, 3.09, 2.78, 2.42, 2.08, 1.63, 1.15, 0.00};
        double[] score = new double[n / 2];
        double[] credit = new double[n / 2];
        //题目里没有要求要用数组，我个人认为数组对于这个问题完全是累赘，因为输入的内容都是一次性使用的。用之前的写法，反复使用score和credit反而会简洁。
        double sum = 0;
        double g;
        double cr = 0;

        for (int i = 0; i < n; i += 2) {
            score[(i + 1) / 2] = Double.parseDouble(args[i]);
            credit[(i + 1) / 2] = Integer.parseInt(args[i + 1]);
            if (score[(i + 1) / 2] >= 90) {
                if (score[(i + 1) / 2] > 96) {
                    g = gpa[0];
                } else if (score[(i + 1) / 2] > 92) {
                    g = gpa[1];
                } else {
                    g = gpa[2];
                }
            } else if (score[(i + 1) / 2] >= 80) {
                if (score[(i + 1) / 2] > 86) {
                    g = gpa[3];
                } else if (score[(i + 1) / 2] > 82) {
                    g = gpa[4];
                } else {
                    g = gpa[5];
                }
            } else if (score[(i + 1) / 2] >= 70) {
                if (score[(i + 1) / 2] > 76) {
                    g = gpa[6];
                } else if (score[(i + 1) / 2] > 72) {
                    g = gpa[7];
                } else {
                    g = gpa[8];
                }
            } else if (score[(i + 1) / 2] >= 60) {
                if (score[(i + 1) / 2] > 66) {
                    g = gpa[9];
                } else if (score[(i + 1) / 2] > 62) {
                    g = gpa[10];
                } else {
                    g = gpa[11];
                }
            } else {
                g = gpa[12];
            }
            //要注意到，每个大级（字母）中，小级（+-）的分法是一样的。所以可以先确定大级，再一并调整小级。但是满分和不及格是特殊的边界，要另行处理。
            //大级基本是由十位决定的，所以可以用Math.floor(score[(i + 1) / 2] / 10)计算十位。可以这样写：
            //int e;
            //【用e确定gpa[e]。】
            //if (score[(i + 1) / 2] < 100) {
            //【排除满分边界。】
                //switch (Math.floor(score[(i + 1) / 2] / 10)) {
                //【用十位判断大级，floor是下取整函数，输入double返回double。】
                //【if-else更短一点，但是之后可以更更短。】
                    //case 9:
                        //e = 1;
                        //break;
                    //case 8:
                        //e = 4;
                        //break;
                    //case 7:
                        //e = 7;
                        //break;
                    //case 6:
                        //e = 10;
                        //break;
                    //default:
                    //处理不及格边界。
                        //e = 12;
                //}
                //【如果再注意到case和e的变化有相当的线性关系，上面的switch语句都可以用下面一行三目代替：】
                //e = (score[(i + 1) / 2] < 60) ? 12 : (int) 3 * 9 - Math.floor(score[(i + 1) / 2] / 10) + 1;
                //【接着调整小级。小级由十位以下（实际上只需要个位）决定。但是不是很均匀地分布，所以考虑if-else：】
                //if (score[(i + 1) / 2] >= 60){
                //【排除不及格边界。】
                    //int revise = (int) score[(i + 1) / 2] % 10;
                    //计算个位。
                    //if (revise >= 7){
                        //e--;
                    //} else if (revise <= 2){
                        //e++;
                    //}
                    //【这里有没有使用三目的取巧算法呢？实际上还是有的。】
                    //【仔细想想，我们要做的事是，把0~2变成+1，把3~6变成0，把7~9变成-1。】
                    //【如果用三目的话，取整必不可少，并且这里的取整是要下端点不变化，上端点变化，也就是要求下取整。】
                    //【刚好revise是整数，取整函数也就免了。】
                    //【但是首先就得考虑把0~2变成-1，3~6变成0，7~9变成1。】
                    //【看样子这十分不均匀，但是要注意到，0之下和9之上的值是不会出现的，所以这些值被算成什么一点关系都没有。】
                    //【于是需要控制的就只有3~6这个区间了，它的长度是4，所以要除以4.】
                    //【再平移对齐两个端点，也就是先把3变成0，把6变成3，再除以4取整就可以了。于是得到如下的三目：】
                    //e -= (revise - 3) / 4;
                //}
            //} else {
                //e = 0;
                //【处理满分边界；】
            //}
            //这样就完成分数的处理了。
            sum += g * credit[(i + 1) / 2];
            //g的设立也是没有必要的，直接用gpa[e]运算就行了。
            //到这里，我们还可以再优化一次上面的程序。为什么呢？
            //注意到，不及格的gpa是0，也就是sum+=这句等于没加。所以我们可以在这里处理不及格边界，而在之前只处理满分边界。
            // 即（从int e之后）：
            //if ([及格]) {
                //if ([不是满分]) {
                    //[处理一般情况]
                //} else {
                    //e = 0;
                //}
                //sum += gpa[e] * credit[(i + 1) / 2];
            //}
            //【不及格么sum也不用加了。所以数组gpa里0那项也可以删掉。】

            //能不能再改呢？还可以。我们要在声明语句上动手脚。
            //直接在声明e的同时赋值为0，那么满分那个if就没有else了。
            //最后的代码是这样：
            //int e = 0;
            //if (score[(i + 1) / 2] >= 60) {
                // if(score[(i + 1) / 2] < 100) {
                    //e = (score[(i + 1) / 2] < 60) ? 12 : (int) 3 * 9 - Math.floor(score[(i + 1) / 2] / 10) + 1;
                    //int revise = (int) score[(i + 1) / 2] % 10;
                    //e -= (revise - 3) / 4;
                    //【如果还想省一行（和一个声明），可以丧病一点，把一次性的revise代进下面的表达式：
                    //e -= ((int) score[(i + 1) / 2] % 10 - 3) / 4;
                //}
                //sum += gpa[e] * credit[(i + 1) / 2];
            //}
            cr += credit[(i + 1) / 2];
        }
        System.out.printf("GPA = %.2f\n", sum / cr);
    }
}