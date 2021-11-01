package Game;

import java.util.Scanner;

public class Game {
    private boolean gameIsRunning = true;
    private Player player = new Player();
    private final Map map = new Map();
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
        System.out.println("""
                You awake inside the Medbay. Last thing you remember before passing out are the intruding enemies.
                To survive, you must make your way through the intruders and reach the cockpit where an escape pod is located\n""");
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
                    if (player.getCurrentRoom() == map.getEndRoom()) {
                        System.out.print("You are in " + player.getCurrentRoomName() + ". ");
                        player.look();
                        System.out.println("\nYou successfully reach the cockpit and escape from the spaceship using an escape pod.");
                        exit();
                    } else {
                        System.out.print("You are in " + player.getCurrentRoomName() + ". ");
                        player.look();
                        System.out.println();
                    }
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
                case ARMOUR_EQUIP_SUCCESS -> {
                    System.out.println("Armour equipped! Your current armour is " + player.getCurrentArmour());
                    System.out.println("It provides the following bonuses: Health +" + player.getCurrentArmour().getMaxHealthIncrease()
                            + ", Damage +" + player.getCurrentArmour().getDamage());
                }
                case WEAPON_EQUIP_SUCCESS -> System.out.println("Weapon equipped! " + player.getCurrentWeapon());
                case CANNOT_EQUIP -> System.out.println("You cannot equip that!");
                case ITEM_NOT_EXIST -> System.out.println("There is no such item in your inventory!");
            }
        } else if (command.startsWith("eat ")) {
            switch (player.eat(command)) {
                case EAT_SUCCESS -> System.out.println("You have eaten the food! Your health is now " + player.getHealth());
                case EAT_FAIL -> System.out.println("You cannot eat this!");
                case EAT_FAIL_FULLHP -> System.out.println("You are already at full health!");
                case ITEM_NOT_EXIST -> System.out.println("There is no such item in your inventory!");
            }
        } else if (command.startsWith("attack")) {
            // check for weapon
            switch (player.getEquippedWeaponStatus()) {
                case NO_WEAPON_EQUIPPED:
                    System.out.println("No weapon equipped!");
                    break;
                case OUT_OF_AMMO:
                    System.out.println("You're out of ammo!");
                    break;
                default:
                    // We now have usable weapon!
                    if (!player.hasEnemiesInRoom()) {
                        System.out.println("There are no enemies in this room, so you attack the air");
                        player.useAmmo();
                    } else {
                        // There are enemies here!

                        // Find the enemy to attack!
                        Enemy enemy;
                        if (command.equals("attack")) {
                            enemy = player.findNearestEnemy();
                        } else {
                            String enemyName = command.substring(7);
                            enemy = player.findEnemy(enemyName);
                        }
                        if (enemy != null) {
                            handleAttack(enemy);
                        } else {
                            System.out.println("That enemy does not exist!");
                        }
                    }
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

    public void handleAttack(Enemy enemy) {
        player.attack(enemy);
        System.out.println("You attacked the nearest enemy, " + enemy);
        if (!enemy.isAlive()) {
            System.out.println("The enemy died and dropped their weapon, " + enemy.getCurrentWeapon());
            enemy.dead();
        } else {
            System.out.println("Their health is now " + enemy.getHealth());
            enemy.attack(player);
            System.out.println("The enemy attacked you!");
            if (!player.isAlive()) {
                System.out.println("You've died.\nGame Over.");
                exit();
            } else {
                System.out.println("Your health is now " + player.getHealth());
            }
        }
    }
}