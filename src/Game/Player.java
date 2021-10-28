package Game;

import java.util.ArrayList;

public class Player {
  //Attributes
  private int health = 100;
  private int maxHealth = 100;
  private Room currentRoom;
  private int maxCarryWeight = 10;
  private int carryWeight;
  private Weapon currentWeapon;
  private Armour currentArmour;
  private ArrayList<AbstractItem> inventory = new ArrayList<>();

  // Handles player movement and commands
  public boolean go(GameValues direction) {
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

  public void setCurrentRoom(Room currentRoom) {
    this.currentRoom = currentRoom;
  }


  public GameValues pickupItem(String itemName) {
    itemName = itemName.substring(5);
    AbstractItem item = currentRoom.getItem(itemName);
    if (item != null) {
      if (carryWeight + item.getWeight() <= maxCarryWeight) {
        inventory.add(item);
        addWeight(item.getWeight());
        currentRoom.removeItem(item);
        return GameValues.PICKUPSUCCESS;
      } else {
        return GameValues.MAXWEIGHT;
      }
    }
    return GameValues.ITEMNOTEXIST;
  }

  public boolean dropItem(String itemName) {
    itemName = itemName.substring(5);
    AbstractItem item = getItem(itemName);
    if (item != null) {
      inventory.remove(item);
      carryWeight = carryWeight - item.getWeight();
      currentRoom.addItem(item);
      if (item == currentWeapon) {
        currentWeapon = null;
      }
      return true;
    } else {
      return false;
    }
  }

  public ArrayList<AbstractItem> getInventory() {
    ArrayList<AbstractItem> listOfRoomItems = new ArrayList<>();
    if (inventory != null)
      for (int i = 0; i < inventory.size(); i++) {
        listOfRoomItems.add(inventory.get(i));
      }
    return listOfRoomItems;
  }

  public void look() {
    currentRoom.printRoomAndItems();
  }

  public String getCurrentRoomName() {
    return currentRoom.getName();
  }

  public AbstractItem getItem(String itemName) {
    for (int i = 0; i < inventory.size(); i++) {
      if (itemName.equals(inventory.get(i).getName().toLowerCase()) || itemName.equals(inventory.get(i).getShortName().toLowerCase())) {
        return inventory.get(i);
      }
    }
    return null;
  }

  public void status() {
    if (health == maxHealth) {
      System.out.println("Health: " + health + "/" + maxHealth + " - You are in peak condition!" +
          "\nWeight: " + carryWeight + "/" + maxCarryWeight);
      System.out.println(getCurrentWeapon());
    } else if (health >= maxHealth * 0.8) {
      System.out.println("Health: " + health + "/" + maxHealth + " - You only have a few scratches on you" +
          "\nWeight: " + carryWeight + "/" + maxCarryWeight);
      System.out.println(getCurrentWeapon());
    } else if (health >= maxHealth * 0.6) {
      System.out.println("Health: " + health + "/" + maxHealth + " - You are looking kind of beat up" +
          "\nWeight: " + carryWeight + "/" + maxCarryWeight);
      System.out.println(getCurrentWeapon());
    } else if (health >= maxHealth * 0.4) {
      System.out.println("Health: " + health + "/" + maxHealth + " - You are badly wounded, best to avoid combat if you can" +
          "\nWeight: " + carryWeight + "/" + maxCarryWeight);
      System.out.println(getCurrentWeapon());
    } else if (health >= maxHealth * 0.2) {
      System.out.println("Health: " + health + "/" + maxHealth + " - You are extremely wounded, your next fight might be your last" +
          "\nWeight: " + carryWeight + "/" + maxCarryWeight);
      System.out.println(getCurrentWeapon());
    } else if (health >= 0) {
      System.out.println("Health: " + health + "/" + maxHealth + " - You are on the brink of death. Find a way to recover or you're done for" +
          "\nWeight: " + carryWeight + "/" + maxCarryWeight);
      System.out.println(getCurrentWeapon());
    }
  }

  public GameValues checkHealthStatus() {
    if (health >= 0) {
      return GameValues.DEATH;
    } else {
      return null;
    }
  }

  public String getCurrentWeapon() {
    if (currentWeapon != null) {
      String message = "Your current weapon is " + currentWeapon;
      if (currentWeapon instanceof RangeWeapon) {
        message = "Your current weapon is " + currentWeapon + " It has " + ((RangeWeapon) currentWeapon).getAmmo() + " ammo";
      }
      return message;
    } else {
      return "You have no weapon equipped";
    }
  }

  public void eat(String itemName) {
    itemName = itemName.substring(4);
    AbstractItem food = getItem(itemName);
    if (food instanceof Food) {
      if (health + ((Food) food).getHealthPoints() <= maxHealth) {
        addHealth(((Food) food).getHealthPoints());
        removeWeight(food.getWeight());
        System.out.println("You have eaten the " + food.getName() + "! Your health is now " + health);
      } else if (health == maxHealth) {
        System.out.println("You are already at full health!");
      } else {
        removeWeight(food.getWeight());
        health = maxHealth;
        System.out.println("You have eaten the " + food.getName() + "! Your health is now " + health);
      }
    } else if (food == null) {
      System.out.println("You don't have that in your inventory!");
    } else {
      System.out.println("You cannot eat this!");
    }
  }

  public void addHealth(int healthPoints) {
    health += healthPoints;
  }

  public void removeHealth(int healthPoints) {
    health -= healthPoints;
  }

  public void addWeight(int weight) {
    carryWeight += weight;
  }

  public void removeWeight(int weight) {
    carryWeight -= weight;
  }

  public void equip(String itemName) {
    itemName = itemName.substring(6);
    if ((getItem(itemName) instanceof Weapon weaponItem)) {
      if (weaponItem instanceof Armour armour) {
        currentArmour = armour;
        maxCarryWeight += armour.getMaxCarryWeightIncrease();
        maxHealth += armour.getMaxHealthIncrease();
        addHealth(armour.getMaxHealthIncrease());
        System.out.println("Armour equipped! Your current armour is " + armour.getName());
      } else {
        currentWeapon = weaponItem;
        System.out.println("Weapon equipped! Your weapon is " + currentWeapon.getName());
      }
    } else {
      if ((getItem(itemName) != null)) {
        System.out.println("You cannot equip that!");
      } else {
        System.out.println("There is no such item in your inventory!");
      }
    }
  }
}