package Game;

import java.util.Scanner;

public class Parser {
  Player player;
  Map map;
  Music music;

  public void gameStart() {
    map = new Map();
    player = new Player();
    music = new Music();

    Scanner input = new Scanner(System.in);
    player.setCurrentRoom(map.getStarterRoom());
    System.out.println("You are in " + player.getCurrentRoom().getName() + ". " + player.getCurrentRoom().getRoomDescription());
    System.out.println("Type \"help\" for a list of commands");

    while(true) {
      String consoleInput = input.nextLine();
      Direction commandDirection = Direction.getDirection(consoleInput);
      if (commandDirection == Direction.NORTH|commandDirection == Direction.SOUTH|commandDirection == Direction.EAST|commandDirection == Direction.WEST) {
       if(player.go(commandDirection)) {
         System.out.println("You are in " + player.getCurrentRoom().getName() + ". " + player.getCurrentRoom().getRoomDescription());
       } else {
         System.out.println("You cannot go that way");
       }
      } else {
        Command command = Command.getCommand(consoleInput);
        switch (command) {
          case HELP -> help();
          case LOOK -> look();
          case EXIT -> exit();
          case STOP -> stopMusic();
          default -> System.out.println("Invalid command, try again.");
        }
      }
    }
  }

  public enum Command {
    HELP, EXIT, LOOK, STOP, UNKNOWN;

    public static Command getCommand (String command) {
      command = command.toLowerCase();
      if(command.matches("help|[h]")) {
        return HELP;
      }
      else if(command.matches("look|l")) {
        return LOOK;
      }
      else if(command.matches("exit")) {
        return EXIT;
      }
      else if(command.matches("stop\s+music|stop\s+[m]")) {
        return STOP;
      }
      else {
        return UNKNOWN;
      }
    }
  }
  public enum Direction {
    NORTH, SOUTH, WEST, EAST, UNKNOWN;

    public static Direction getDirection(String direction) {
      try {
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
          throw new IllegalArgumentException();
        }
      } catch (IllegalArgumentException unknownCommand) {
        return UNKNOWN;
      }
    }
  }
  public void help() {
    System.out.println("\"go (north, south, east, west)\", \"look\", \"exit\", \"stop music\".");
  }
  public void look() {
    System.out.println(player.getCurrentRoom().getRoomDescription());
  }
  public void exit() {
    Adventure.exitGame();
  }
  public void stopMusic() {
    music.stopMusic();
  }
}
