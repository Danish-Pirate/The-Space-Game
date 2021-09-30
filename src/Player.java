import java.util.Scanner;

public class Player {

 public void move() {
     Map mapCreator = new Map();
     Map.currentRoom = Map.room1;
     Scanner input = new Scanner(System.in);

     System.out.println("You are in " + Map.currentRoom.getName() + ". " + Map.currentRoom.getRoomDescription());
     System.out.println("Type \"help\" to get help");
     boolean isGameRunning = true;
     while (isGameRunning) {
         String goMessage = input.nextLine();
         switch (goMessage) {
             case "go north": goNorth();
                 break;
             case "go east": goEast();
                 break;
             case "go south": goSouth();
                 break;
             case "go west": goWest();
                 break;
             case "exit": isGameRunning = false;
                 break;
             case "look":
                 System.out.println(Map.currentRoom.getRoomDescription());
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
    public static void goNorth() {
        if (Map.currentRoom.getNorthRoom() != null) {
            Map.currentRoom = Map.currentRoom.getNorthRoom();
            System.out.println("You are in " + Map.currentRoom.getName() + ". " + Map.currentRoom.getRoomDescription());
        } else {
            System.out.println("You cannot go that way");
        }
    }
    public static void goEast() {
        if (Map.currentRoom.getEastRoom() != null) {
            Map.currentRoom = Map.currentRoom.getEastRoom();
            System.out.println("You are in " + Map.currentRoom.getName() + ". " + Map.currentRoom.getRoomDescription());
        } else {
            System.out.println("You cannot go that way");
        }
    }
    public static void goSouth() {
        if (Map.currentRoom.getSouthRoom() != null) {
            Map.currentRoom = Map.currentRoom.getSouthRoom();
            System.out.println("You are in " + Map.currentRoom.getName() + ". " + Map.currentRoom.getRoomDescription());
        } else {
            System.out.println("You cannot go that way");
        }
    }
    public static void goWest() {
        if (Map.currentRoom.getWestRoom() != null) {
            Map.currentRoom = Map.currentRoom.getWestRoom();
            System.out.println("You are in " + Map.currentRoom.getName() + ". " + Map.currentRoom.getRoomDescription());
        } else {
            System.out.println("You cannot go that way");
        }
    }
}
