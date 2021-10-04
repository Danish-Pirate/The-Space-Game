import java.util.Scanner;


public enum Direction {
    NORTH, SOUTH, WEST, EAST;

    public static void main(String[] args) {
        input();
    }
    public static void input () {
        try {

            Scanner input = new Scanner(System.in);
            String consoleInput = input.nextLine();
            System.out.println(getDirection(consoleInput));
        }
        catch(IllegalArgumentException unknownCommand) {}
    }

    public static Direction getDirection(String direction) {
        direction = direction.toLowerCase();
        if (direction.matches("go\s+[n]|go\s+north|north|[n]")) {
            return NORTH;
        } else if (direction.matches("go\s+[s]|go\s+south|south|[s]")) {
            return SOUTH;
        } else if (direction.matches("go\s+[w]|go\s+west|west|[w]")) {
            return WEST;
        } else if (direction.matches("go\s+[e]|go\s+east|east|[e]")) {
            return EAST;
        } else {
            System.out.println("Unknown Command");
            throw new IllegalArgumentException();
        }
    }
}