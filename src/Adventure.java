import java.util.Scanner;

public class Adventure {
    public static void main(String[] args) {

        System.out.println("Welcome to Space Game!\nType \"Start\" to begin.");
        Player player = new Player();
        GUI gui = new GUI();
        player.move();

    }
    // Checks if user typed "start"
    public static void start() {
        Scanner input = new Scanner(System.in);
        boolean typedStart = false;

        while (!typedStart) {
            String startMessage = input.nextLine();
            startMessage = startMessage.toLowerCase();
            startMessage = startMessage.replaceAll(" ", "");
            if (startMessage.equals("start")) {
                typedStart = true;
            } else {
                System.out.println("Didn't type \"Start\", try again.");
            }
        }
    }
}