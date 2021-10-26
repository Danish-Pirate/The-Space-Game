package Game;

import java.util.ArrayList;

public class Player {
  //Attributes
  private int health = 100;
  private Room currentRoom;
  private int maxCarryWeight = 10;
  private int currentWeight;
  private ArrayList<Item> inventory = new ArrayList<>();

  // Handles player movement and commands
  public boolean go(Directions direction) {
    Room requestRoom = switch (direction) {
      case NORTH -> currentRoom.getNorthRoom();
      case SOUTH -> currentRoom.getSouthRoom();
      case EAST -> currentRoom.getEastRoom();
      case WEST -> currentRoom.getWestRoom();
      default -> null;
    };
    if (requestRoom == null) {
      return false;
    } else {
      currentRoom = requestRoom;
      return true;
    }
  }

  public Room getCurrentRoom() {
    return currentRoom;
  }

  public void setCurrentRoom(Room currentRoom) {
    this.currentRoom = currentRoom;
  }

  public ArrayList<Item> getInventory() {
    return inventory;
  }

  public Directions pickupItem(String command) {
    command = command.substring(5);
    Item item = currentRoom.getItem(command);
    if (item != null) {
      if (currentWeight + item.getWeight() <= maxCarryWeight) {
        inventory.add(item);
        currentWeight = currentWeight + item.getWeight();
        currentRoom.removeItem(item);
        return Directions.PICKUPSUCCESS;
      } else {
        return Directions.MAXWEIGHT;
      }
    }
    return Directions.ITEMNOTEXIST;
  }

  public boolean dropItem(String command) {
    command = command.substring(5);
    Item item = getItem(command);
    if (item != null) {
      inventory.remove(item);
      currentWeight = currentWeight - item.getWeight();
      currentRoom.addItem(item);
      return true;
    } else {
      return false;
    }
  }

  public ArrayList checkInventory() {
    ArrayList<Item> listOfRoomItems = new ArrayList<>();
    if (inventory != null)
      for (int i = 0; i < inventory.size(); i++) {
        listOfRoomItems.add(inventory.get(i));
      }
    return listOfRoomItems;
  }
  public void look () {
    currentRoom.printRoomAndItems();
  }
  public String getCurrentRoomName () {
    return currentRoom.getName();
  }
  public Item getItem (String itemName) {
      for (int i = 0; i < inventory.size(); i++) {
        if (itemName.equals(inventory.get(i).getName().toLowerCase())) {
          return inventory.get(i);
        }
      }
      return null;
    }
    public void status () {
      System.out.println("Health: " + health + "\nWeight: " + currentWeight);
    }
  }