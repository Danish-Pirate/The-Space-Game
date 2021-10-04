package Game;

public class Adventure {

    public static void main(String[] args) throws InterruptedException {

            System.out.println("Welcome to Space Game!");
            Thread.sleep(3000);
            Parser parser = new Parser();
            parser.gameStart();
    }
    // Exits the game
    public static void exitGame () {
        System.exit(0);
    }
}