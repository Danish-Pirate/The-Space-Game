import java.util.Scanner;

public class Player {
    //Attributes
    private Room currentRoom;
    Player player;

 // Handles player movement and commands
 public void move() {
     player = new Player();
     Map map = new Map();
     player.currentRoom = map.room1;
     Scanner input = new Scanner(System.in);

     System.out.println("You are in " + player.currentRoom.getName() + ". " + player.currentRoom.getRoomDescription());
     System.out.println("Type \"help\" to get help");

     //Commands
     boolean isGameRunning = true;
     while (isGameRunning) {
         String command = input.nextLine();
         command = command.toLowerCase();
         switch (command) {
             case "go north", "north", "go n", "n": goNorth(); break;
             case "go east", "east", "go e", "e": goEast(); break;
             case "go south", "south", "go s", "s": goSouth(); break;
             case "go west", "west", "go w", "w": goWest(); break;
             case "exit": isGameRunning = false; break;
             case "look":
                 System.out.println(player.currentRoom.getRoomDescription());
                 break;
             case "help":
                 System.out.println("""
                            "go (north, south, east, west)" to choose a direction to go.
                            "look" gives you a description of the room.
                            "exit" stops the game.""");
                 break;
             default:
                 System.out.println("Unknown command");
                 break;
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
}
