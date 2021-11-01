package Game;

import java.util.ArrayList;

public class Room {

    // Attributes
    final private String name;
    final private String roomDescription;
    private ArrayList<AbstractItem> inventory = new ArrayList<>();
    private ArrayList<Enemy> listOfEnemies = new ArrayList<>();
    private Room northRoom;
    private Room eastRoom;
    private Room southRoom;
    private Room westRoom;

    // Constructor
    public Room(String name, String roomDescription) {
        this.name = name;
        this.roomDescription = roomDescription;
    }

    // Getters & Setters
    public String getName() {
        return name;
    }

    public Room getNorthRoom() {
        return northRoom;
    }

    public Room getEastRoom() {
        return eastRoom;
    }

    public Room getSouthRoom() {
        return southRoom;
    }

    public Room getWestRoom() {
        return westRoom;
    }

    public void setNorthRoom(Room northRoom) {
        this.northRoom = northRoom;
    }

    public void setEastRoom(Room eastRoom) {
        this.eastRoom = eastRoom;
    }

    public void setSouthRoom(Room southRoom) {
        this.southRoom = southRoom;
    }

    public void setWestRoom(Room westRoom) {
        this.westRoom = westRoom;
    }

    // Connects the rooms
    public void connectNorthRoom(Room northRoom) {
        setNorthRoom(northRoom);
        if (northRoom.southRoom == null)
            northRoom.southRoom = this;
    }

    public void connectEastRoom(Room eastRoom) {
        setEastRoom(eastRoom);
        if (eastRoom.westRoom == null)
            eastRoom.westRoom = this;
    }

    public void connectSouthRoom(Room southRoom) {
        setSouthRoom(southRoom);
        if (southRoom.northRoom == null)
            southRoom.northRoom = this;
    }

    public void connectWestRoom(Room westRoom) {
        setWestRoom(westRoom);
        if (westRoom.eastRoom == null)
            westRoom.eastRoom = this;
    }

    // Returns an item from the room's inventory arraylist by comparing itemName String to names of the item objects inside the room.
    public AbstractItem getItem(String itemName) {
        for (AbstractItem abstractItem : inventory) {
            if (itemName.equals(abstractItem.getName().toLowerCase()) || itemName.equals(abstractItem.getShortName().toLowerCase())) {
                return abstractItem;
            }
        }
        return null;
    }

    // Adds an already existing item to a room
    public void addItem(AbstractItem item) {
        inventory.add(item);
    }

    // Creates an item and adds it to a room.
    public void createAndAddItem(String name, int weight) {
        Item item = new Item(name, weight);
        inventory.add(item);
    }

    // Creates a food item and adds it to a room
    public void createAndAddFoodItem(String name, int weight, int healthPoints) {
        Food food = new Food(name, weight, healthPoints);
        inventory.add(food);
    }

    // Creates a melee weapon item and adds it to a room
    public void createAndAddMeleeWeaponItem(String name, int weight, int damage) {
        MeleeWeapon item = new MeleeWeapon(name, weight, damage);
        inventory.add(item);
    }

    // Creates a range weapon item and adds it to a room
    public void createAndAddRangeWeaponItem(String name, int weight, int damage, int ammo) {
        RangeWeapon item = new RangeWeapon(name, weight, damage, ammo);
        inventory.add(item);
    }

    public void createAndAddArmourItem(String name, int weight, int damage, int maxHealthIncrease, int maxCarryWeightIncrease) {
        Armour item = new Armour(name, weight, damage, maxHealthIncrease, maxCarryWeightIncrease);
        inventory.add(item);
    }

    public void createAndAddEnemy(String name, int health, Weapon currentWeapon, Room currentRoom) {
        Enemy enemy = new Enemy(name, health, currentWeapon, currentRoom);
        listOfEnemies.add(enemy);
    }

    // Removes an item from a room
    public void removeItem(AbstractItem item) {
        inventory.remove(item);
    }

    // Prints a description of the room along with items inside of it.
    public void printRoomAndItemsAndEnemies() {
        System.out.println(roomDescription);
        if (!inventory.isEmpty()) {
            System.out.println("There are the following items in the room:");
            for (int i = 0; i < inventory.size(); i++) {
                if (i < inventory.size() - 2) {
                    System.out.print(inventory.get(i) + ", ");
                } else if (i < inventory.size() - 1) {
                    System.out.print(inventory.get(i) + " and ");
                } else {
                    System.out.print(inventory.get(i));
                }
            }
        } else {
            System.out.print("There are no items in this room");
        }
        if (!listOfEnemies.isEmpty()) {
            System.out.println("\n\nThere are the following enemies in the room: ");
            for (int i = 0; i < listOfEnemies.size(); i++) {
                if (i < listOfEnemies.size() - 2) {
                    System.out.print(listOfEnemies.get(i) + ", ");
                } else if (i < listOfEnemies.size() - 1) {
                    System.out.print(listOfEnemies.get(i) + " and ");
                } else {
                    System.out.print(listOfEnemies.get(i));
                }
            }
        } else {
            System.out.print("\n\nThere are no enemies in this room");
        }
    }

    public ArrayList<Enemy> getListOfEnemies() {
        return listOfEnemies;
    }

    public Enemy findEnemy(String enemyName) {
        for (Enemy listOfEnemy : listOfEnemies) {
            if (enemyName.equalsIgnoreCase(listOfEnemy.getName())) {
                return listOfEnemy;
            }
        }
        return null;
    }
}



