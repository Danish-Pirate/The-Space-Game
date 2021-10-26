package Game;

import java.util.Scanner;

public class Game {
  private boolean gameIsRunning = true;
  private Player player = new Player();
  private Map map = new Map();
  private Music music = new Music();
  public void start() {

    System.out.println("Welcome to Space Game!");
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    Scanner input = new Scanner(System.in);
    player.setCurrentRoom(map.getStarterRoom());
    System.out.print("You are in " + player.getCurrentRoomName() + ". ");
    player.look();
    System.out.println("\nType \"help\" for a list of commands");

    while (gameIsRunning) {
      String consoleInput = input.nextLine();
      Directions commandDirection = getDirection(consoleInput);
      if (commandDirection == Directions.NORTH | commandDirection == Directions.SOUTH |
          commandDirection == Directions.EAST | commandDirection == Directions.WEST) {
        if (player.go(commandDirection)) {
          System.out.print("You are in " + player.getCurrentRoomName() + ". ");
          player.look();
        } else {
          System.out.println("You cannot go that way");
        }
      } else {
        getCommand(consoleInput);
      }
    }
  }

  public void getCommand(String command) {
    command = command.toLowerCase();
    if (command.startsWith("take ")) {
       if (player.pickupItem(command) == Directions.PICKUPSUCCESS) {
         music.playItemPickUpSound();
         System.out.println("Item taken!");
       }
       else if (player.pickupItem(command) == Directions.MAXWEIGHT) {
         System.out.println("You are carrying too much weight! Drop something and try again.");
       }
       else {
         System.out.println("No such item in the room!");
       }
    } else if (command.startsWith("drop ")) {
      if (player.dropItem(command)) {
        music.playItemDropSound();
        System.out.println("Item dropped!");
      } else {
        System.out.println("No such item in your inventory!");
      }
    }
    else if (command.matches("inv|invent|inventory")) {
      if (player.checkInventory().size() != 0) {
        System.out.println("There following items are in your inventory:");
        for (int i = 0; i < player.checkInventory().size(); i++) {
          System.out.print(player.checkInventory().get(i) + ", ");
        }
      } else {
        System.out.println("There are no items in your inventory!");
      }
    } else if (command.matches("help|[h]")) {
      help();
    } else if (command.matches("look|l")) {
      player.look();
    } else if (command.matches("exit")) {
      exit();
    } else if (command.matches("stop\s+music|stop\s+[m]")) {
      music.stopMusic();
      System.out.println("Music stopped!");
    } else if (command.matches("start\s+music|start\s+[m]")) {
      music.playMusic();
      System.out.println("Music started!");
    } else {
      System.out.println("Invalid command, try again");
    }
  }

  public static Directions getDirection(String direction) {

    direction = direction.toLowerCase();
    if (direction.matches("go\s+[n]|go\s+north|north|[n]")) {
      return Directions.NORTH;
    } else if (direction.matches("go\s+[s]|go\s+south|south|[s]")) {
      return Directions.SOUTH;
    } else if (direction.matches("go\s+[w]|go\s+west|west|[w]")) {
      return Directions.WEST;
    } else if (direction.matches("go\s+[e]|go\s+east|east|[e]")) {
      return Directions.EAST;
    } else {
      return Directions.UNKNOWN;
    }
  }

  public void help() {
    System.out.println("\"go (north, south, east, west)\" - Makes you go to a certain direction\n\"look\"" +
            " - Gives you a description of the room\n\"exit\"" +
            " - Exits the game\n\"stop music\" - Stops the game music\n\"start music\"" +
            " - Starts the game music\n\"take/drop (folowed by item name)\" - picks up and drops things.\n\"inventory\" - checks inventory");
  }

  public void exit() {
    gameIsRunning = false;
  }
}