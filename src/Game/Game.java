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

      /* Checks to see if the player has inputted a direction to go.
      If not, getCommand method will be called to check the input against other conditions. */
            Direction commandDirection = getDirection(consoleInput);
            if (commandDirection == Direction.NORTH | commandDirection == Direction.SOUTH |
                    commandDirection == Direction.EAST | commandDirection == Direction.WEST) {
                if (player.go(commandDirection)) {
                    System.out.print("You are in " + player.getCurrentRoomName() + ". ");
                    player.look();
                    System.out.println();
                } else {
                    System.out.println("You cannot go that way");
                }
            } else {
                getCommand(consoleInput);
            }
        }
    }

    // checks the player input against conditions for commands not related to changing rooms.
    public void getCommand(String command) {
        command = command.toLowerCase();
        if (command.startsWith("take ")) {
            if (player.pickupItem(command) == Status.PICKUP_SUCCESS) {
                music.playItemPickUpSound();
                System.out.println("Item taken!");
            } else if (player.pickupItem(command) == Status.MAXWEIGHT) {
                System.out.println("You are carrying too much weight! Drop something and try again.");
            } else {
                System.out.println("No such item in the room!");
            }
        } else if (command.startsWith("drop ")) {
            if (player.dropItem(command)) {
                music.playItemDropSound();
                System.out.println("Item dropped!");
            } else {
                System.out.println("No such item in your inventory!");
            }
        } else if (command.matches("inv|invent|inventory")) {
            if (player.getInventory().size() != 0) {
                System.out.println("There following items are in your inventory:");
                for (int i = 0; i < player.getInventory().size(); i++) {
                    if (i < player.getInventory().size() - 2) {
                        System.out.print(player.getInventory().get(i) + ", ");
                    } else if (i < player.getInventory().size() - 1) {
                        System.out.print(player.getInventory().get(i) + " and ");
                    } else {
                        System.out.println(player.getInventory().get(i));
                    }
                }
            } else {
                System.out.println("There are no items in your inventory!");
            }
        } else if (command.matches("help|[h]")) {
            help();
        } else if (command.startsWith("equip ")) {
            switch (player.equip(command)) {
                case ARMOUR_EQUIP_SUCCESS:
                    System.out.println("Armour equipped! Your current armour is " + player.getCurrentArmour());
                    break;
                case WEAPON_EQUIP_SUCCESS:
                    System.out.println("Weapon equipped! " + player.getCurrentWeapon());
                    break;
                case CANNOT_EQUIP:
                    System.out.println("You cannot equip that!");
                    break;
                case ITEM_NOT_EXIST:
                    System.out.println("There is no such item in your inventory!");
                    break;
            }
            ;
        } else if (command.startsWith("eat ")) {
            switch (player.eat(command)) {
                case EAT_SUCCESS:
                    System.out.println("You have eaten the food! Your health is now " + player.getHealth());
                    break;
                case EAT_FAIL:
                    System.out.println("You cannot eat this!");
                    break;
                case EAT_FAIL_FULLHP:
                    System.out.println("You are already at full health!");
                    break;
                case ITEM_NOT_EXIST:
                    System.out.println("There is no such item in your inventory!");
                    break;
            }
        } else if (command.startsWith("attack")) {
            if (player.doesCurrentRoomHaveEnemy()) {
                if (command.length() > "attack".length()) {
                    String enemyName = command.substring(7);
                    Enemy enemy = player.findEnemy(enemyName);
                    System.out.println(handleAttack(enemy));
                } else if (command.equals("attack")) {
                    Enemy enemy = player.attack();
                    if (enemy != null) {
                        System.out.println("You attack the enemy. Enemy health is now " + enemy.getHealth());
                    } else {
                        System.out.println("You don't have a weapon equipped!");
                    }
                }
            } else {
                System.out.println("There are no enemies in the room, so you attack the air.");
            }
        } else if (command.matches("status")) {
            player.status();
        } else if (command.matches("look|l")) {
            player.look();
            System.out.println();
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

    /* Checks to see which direction the player wants to go and returns the appropriate Direction Enum.
     If UNKNOWN is returned, then the input is not a go-command. */
    public static Direction getDirection(String direction) {
        direction = direction.toLowerCase();
        if (direction.matches("go\s+[n]|go\s+north|north|[n]")) {
            return Direction.NORTH;
        } else if (direction.matches("go\s+[s]|go\s+south|south|[s]")) {
            return Direction.SOUTH;
        } else if (direction.matches("go\s+[w]|go\s+west|west|[w]")) {
            return Direction.WEST;
        } else if (direction.matches("go\s+[e]|go\s+east|east|[e]")) {
            return Direction.EAST;
        } else {
            return Direction.UNKNOWN;
        }
    }

    public void help() {
        System.out.println("""
                "go (north, south, east, west)" - Makes you go to a certain direction
                "look" - Gives you a description of the room
                "eat (item name)" - eats a food item
                "equip (weapon name)" - equips a weapon or armour
                "exit" - Exits the game
                "stop music" - Stops the game music
                "start music" - Starts the game music
                "take/drop (folowed by item name)" - picks up and drops things
                "inventory" - checks inventory
                "status" - displays health, weight and current weapon stats""");
    }

    public void exit() {
        gameIsRunning = false;
    }

    public String handleAttack(Enemy enemy) {
        if (enemy != null) {
            if (player.attack(enemy) == Status.PLAYER_ATTACK_SUCCESS) {
                return "You attack the enemy. Enemy health is now " + enemy.getHealth();
            }
            return "You don't have a weapon equipped!";
        }

        return "Enemy doesn't exist!";
    }
}