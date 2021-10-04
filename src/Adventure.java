
public class Adventure {

    public static void main(String[] args) throws InterruptedException {

            Player player = new Player();
            System.out.println("Welcome to Space Game!");
            Thread.sleep(3000);
            player.move();
    }
    // Exits the game
    public static void exitGame () {
        System.exit(0);
    }
}