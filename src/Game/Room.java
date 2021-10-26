package Game;

import java.util.ArrayList;

public class Room {

  // Attributes
  final private String name;
  final private String roomDescription;
  private ArrayList<Item> inventory = new ArrayList<>();
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

  public ArrayList<Item> getInventory() {
    return inventory;
  }

  public Item getItem(String itemName) {
    for (int i = 0; i < inventory.size(); i++) {
      if (itemName.equals(inventory.get(i).getName().toLowerCase()) || itemName.equals(inventory.get(i).getShortName().toLowerCase())) {
        return inventory.get(i);
      }
    }
    return null;
  }

  public void addItem(Item item) {
    inventory.add(item);
  }

  public void addItem(String name, int weight){
    Item item = new Item(name, weight);
    inventory.add(item);
  }

  public void removeItem(Item item) {
    inventory.remove(item);
  }

  public void printRoomAndItems() {
    System.out.println(roomDescription);
    if (inventory.size() != 0) {
      System.out.println("There are the following items in the room:");
      for (int i = 0; i < inventory.size(); i++) {
        System.out.print(inventory.get(i) + ", ");
      }
    }
  }
}



