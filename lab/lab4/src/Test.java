public class Test {
    public static void main(String[] args){
        for (int i=1; i<10; i++){
            for (int j=1; j<10; j++)
            {
                // pos1

                System.out.print("("+i+","+j+") ");
                //pos2

            }
            //pos3
            if(i>5) return;
            System.out.println();
            //pos4

            //statement1: if(i>5) continue;
            //statement2: if(i>5) break;
            //statement3: if(i>5) return;
        }

    }
}
