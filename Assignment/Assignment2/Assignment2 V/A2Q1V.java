public class A2Q1V {
    public static void main(String[] args) {
        String tianGan[] = {"geng", "xin", "ren", "gui", "jia", "yi", "bing", "ding", "wu", "ji"};
        String diZhi[] = {"shen", "you", "xu", "hai", "zi", "chou", "yin", "mao", "chen", "si", "wu", "wei"};
        String shuXiang[] = {"Monkey", "Rooster", "Dog", "Pig", "Rat", "Ox", "Tiger", "Rabbit", "Dragon", "Snake", "Horse", "Sheep"};
        int year = Integer.parseInt(args[0]);
        int t = year % 10;
        int d = year % 12;
        System.out.printf("%d is the year of %s-%s. Also %s year.", year, tianGan[t], diZhi[d], shuXiang[d]);
    }
}