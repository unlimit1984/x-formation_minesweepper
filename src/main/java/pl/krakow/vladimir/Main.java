package pl.krakow.vladimir;

public class Main {


    public static int N;
    public static int M;
    public static char[][] mines;

    public static void main(String[] args) {

        MineSweeper mine = new MineImpl();

        mine.setMineField("*...\n..*.\n....");
        System.out.println(mine.getHintField());
        System.out.println();

        mine.setMineField("**..\n..**\n.**.");
        System.out.println(mine.getHintField());
        System.out.println();

        mine.setMineField("....\n....\n...*");
        System.out.println(mine.getHintField());

    }
}
