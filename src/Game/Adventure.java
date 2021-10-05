package Game;

public class Adventure {

    public static void main(String[] args) {

            System.out.println("Welcome to Space Game!");
      try {
        Thread.sleep(3000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      Parser parser = new Parser();
            parser.gameStart();
    }
    // Exits the game
    public static void exitGame () {
        System.exit(0);
    }
}