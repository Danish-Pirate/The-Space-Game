import java.util.Scanner;

public class Adventure {
    int currentRoom;

    public static void main(String[] args) {
        Adventure adventure = new Adventure();
        System.out.println("Welcome to Cave Game!\nType \"Start\" to begin.");
        startCheck();
    }
    public static void startCheck() {
        Scanner input = new Scanner(System.in);
        boolean typedStart = false;
        while (!typedStart) {
            String startMessage = input.nextLine();
            startMessage = startMessage.toLowerCase();
            startMessage = startMessage.replaceAll(" ", "");
            if (startMessage.equals("start")) {
                System.out.println("Typed \"Start\"");
                typedStart = true;
            } else {
                System.out.println("Didn't type \"Start\", try again.");
            }
        }
    }
}