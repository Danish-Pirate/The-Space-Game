import java.util.Scanner;

public class Adventure {
    public static void main(String[] args) {

        System.out.println("Welcome to Space Game!\nType \"Start\" to begin.");
        Player player = new Player();
        startCheck();
        System.out.println("You are in " + Map.currentRoom.getName() + ". " + Map.currentRoom.getRoomDescription());
        System.out.println("Type \"help\" to get help");
        player.move();
    }
    // Checks if user typed "start"
    public static void startCheck() {
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