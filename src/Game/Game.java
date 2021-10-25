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
    if (command.matches("take(\\s\\w+){1,3}$")) {
       if (player.pickupItem(command)) {
         System.out.println("Item taken!");
       }
       else {
         System.out.println("No such item in the room!");
       }
    } else if (command.matches("drop(\\s\\w+){1,4}$")) {
      if (player.dropItem(command)) {
        System.out.println("Item dropped!");
      } else {
        System.out.println("No such item in your inventory!");
      }
    }
    else if (command.matches("inv|invent|inventory")) {
      if (player.checkInventory() != null) {
        System.out.println("There following items are in your inventory:");
        for (int i = 0; i < player.checkInventory().size(); i++) {
          System.out.print(player.checkInventory().get(i) + ", ");
        }
      } else {
        System.out.println("No items in your inventory!");
      }
    } else if (command.matches("help|[h]")) {
      help();
    } else if (command.matches("look|l")) {
      look();
    } else if (command.matches("exit")) {
      exit();
    } else if (command.matches("stop\s+music|stop\s+[m]")) {
      stopMusic();
    } else if (command.matches("start\s+music|start\s+[m]")) {
      playMusic();
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

  public void look() {
    player.look();
  }

  public void exit() {
    gameIsRunning = false;
  }

  public void stopMusic() {
    music.stopMusic();
  }

  public void playMusic() {
    music.playMusic();
  }
}
