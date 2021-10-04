import java.util.Scanner;

public class Player {
    //Attributes
    private Room currentRoom;
    Player player;

 // Handles player movement and commands
 public void move() {
     Map map = new Map();
     player = new Player();
     Scanner input = new Scanner(System.in);
     player.currentRoom = map.room1;
     System.out.println("You are in " + player.currentRoom.getName() + ". " + player.currentRoom.getRoomDescription());
     System.out.println("Type \"help\" for a list of commands");

     while(true) {
         String consoleInput = input.nextLine();
         Direction commandDirection = Direction.getDirection(consoleInput);
         if (commandDirection == Direction.NORTH) {
             goNorth();
         } else if (commandDirection == Direction.SOUTH) {
             goSouth();
         } else if (commandDirection == Direction.EAST) {
             goEast();
         } else if (commandDirection == Direction.WEST) {
             goWest();
         } else {
             Command command = Command.getCommand(consoleInput);
             if (command == Command.HELP) {
                 help();
             } else if (command == Command.LOOK) {
                 look();
             } else if (command == Command.EXIT) {
                 exit();
             } else {
                 System.out.println("Invalid command, try again.");
             }
         }
     }

 }
    public void goNorth() {
        if (player.currentRoom.getNorthRoom() != null) {
            player.currentRoom = player.currentRoom.getNorthRoom();
            System.out.println("You are in " + player.currentRoom.getName() + ". " + player.currentRoom.getRoomDescription());
        } else {
            System.out.println("You cannot go that way");
        }
    }
    public void goEast() {
        if (player.currentRoom.getEastRoom() != null) {
            player.currentRoom = player.currentRoom.getEastRoom();
            System.out.println("You are in " + player.currentRoom.getName() + ". " + player.currentRoom.getRoomDescription());
        } else {
            System.out.println("You cannot go that way");
        }
    }
    public void goSouth() {
        if (player.currentRoom.getSouthRoom() != null) {
            player.currentRoom = player.currentRoom.getSouthRoom();
            System.out.println("You are in " + player.currentRoom.getName() + ". " + player.currentRoom.getRoomDescription());
        } else {
            System.out.println("You cannot go that way");
        }
    }
    public void goWest() {
        if (player.currentRoom.getWestRoom() != null) {
            player.currentRoom = player.currentRoom.getWestRoom();
            System.out.println("You are in " + player.currentRoom.getName() + ". " + player.currentRoom.getRoomDescription());
        } else {
            System.out.println("You cannot go that way");
        }
    }
    public void help() {
        System.out.println("\"go (north, south, east, west)\", \"look\", \"exit\".");
    }
    public void look() {
        System.out.println(player.currentRoom.getRoomDescription());
    }
    public void exit() {
     Adventure.exitGame();
    }
}
