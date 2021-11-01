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
  public boolean go(Direction direction) {
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


  public Status pickupItem(String itemName) {
    itemName = itemName.substring(5);
    Status status;
    AbstractItem item = currentRoom.getItem(itemName);
    if (item != null) {
      if (carryWeight + item.getWeight() <= maxCarryWeight) {
        inventory.add(item);
        addWeight(item.getWeight());
        currentRoom.removeItem(item);
        status = Status.PICKUP_SUCCESS;
      } else {
        status = Status.MAXWEIGHT;
      }
    } else {
      status = Status.ITEM_NOT_EXIST;
    }
    return status;
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
      for (AbstractItem abstractItem : inventory) {
        listOfRoomItems.add(abstractItem);
      }
    return listOfRoomItems;
  }

  public void look() {
    currentRoom.printRoomAndItemsAndEnemies();
  }

  public String getCurrentRoomName() {
    return currentRoom.getName();
  }

  public AbstractItem getItem(String itemName) {
    for (AbstractItem abstractItem : inventory) {
      if (itemName.equals(abstractItem.getName().toLowerCase()) || itemName.equals(abstractItem.getShortName().toLowerCase())) {
        return abstractItem;
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

  public Status eat(String itemName) {
    itemName = itemName.substring(4);
    Status status;
    AbstractItem food = getItem(itemName);
    if (food instanceof Food) {
      if (health + ((Food) food).getHealthPoints() <= maxHealth) {
        addHealth(((Food) food).getHealthPoints());
        removeWeight(food.getWeight());
        status = Status.EAT_SUCCESS;
      } else if (health == maxHealth) {
        status = Status.EAT_FAIL_FULLHP;
      } else {
        removeWeight(food.getWeight());
        health = maxHealth;
        status = Status.EAT_SUCCESS;
      }
    } else if (food == null) {
      status = Status.ITEM_NOT_EXIST;
    } else {
      status = Status.EAT_FAIL;
    }
    return status;
  }

  public void addHealth(int healthPoints) {
    health += healthPoints;
  }

  public void addWeight(int weight) {
    carryWeight += weight;
  }

  public void removeWeight(int weight) {
    carryWeight -= weight;
  }

  public Status equip(String itemName) {
    itemName = itemName.substring(6);
    Status status;
    if ((getItem(itemName) instanceof Weapon weaponItem)) {
      if (weaponItem instanceof Armour armour) {
        currentArmour = armour;
        maxCarryWeight += armour.getMaxCarryWeightIncrease();
        maxHealth += armour.getMaxHealthIncrease();
        addHealth(armour.getMaxHealthIncrease());
        status = Status.ARMOUR_EQUIP_SUCCESS;
      } else {
        currentWeapon = weaponItem;
        status = Status.WEAPON_EQUIP_SUCCESS;
      }
    } else {
      if ((getItem(itemName) != null)) {
        status = Status.CANNOT_EQUIP;
      } else {
        status = Status.ITEM_NOT_EXIST;
      }
    }
    return status;
  }

  public Armour getCurrentArmour() {
    return currentArmour;
  }

  public Status getEquippedWeaponStatus() {
    if (currentWeapon == null) {
      return Status.NO_WEAPON_EQUIPPED;
    }
    if (currentWeapon instanceof RangeWeapon && ((RangeWeapon) currentWeapon).getAmmo() <= 0) {
      return Status.OUT_OF_AMMO;
    }
    return Status.CAN_USE_WEAPON;
  }

  public boolean hasEnemiesInRoom() {
    if (!currentRoom.getListOfEnemies().isEmpty()) {
      return true;
    } else {
      return false;
    }
  }
  public int getTotalDamage () {
    if (currentArmour != null) {
      return currentArmour.getDamage() + currentWeapon.getDamage();
    } else {
      return currentWeapon.getDamage();
    }
  }
  public void useAmmo () {
    if (currentWeapon instanceof RangeWeapon) {
      ((RangeWeapon) currentWeapon).useAmmo();
    }
  }


  public void attack(Enemy enemy) {
    enemy.takeDamage(getTotalDamage());
    if (currentWeapon instanceof RangeWeapon) {
      ((RangeWeapon) currentWeapon).useAmmo();
    }
  }

  public Room getCurrentRoom() {
    return currentRoom;
  }

  public int getHealth() {
    return health;
  }

  public Enemy findEnemy(String enemyName) {
    Enemy enemy = currentRoom.findEnemy(enemyName);
    return enemy;
  }

  public Enemy findNearestEnemy() {
    return currentRoom.getListOfEnemies().get(0);
  }

  public void takeDamage(int damage) {
    health -= damage;
  }

  public boolean isAlive() {
    if (health <= 0) {
      return false;
    } else {
      return true;
    }
  }
}