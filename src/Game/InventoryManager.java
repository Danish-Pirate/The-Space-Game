package Game;


public class InventoryManager {
  public boolean pickupItem(Player playerObject, String command) {
    command = command.substring(5);
    for (int i = 0; i < playerObject.getCurrentRoom().getInventory().size(); i++) {
      if (command.equals(playerObject.getCurrentRoom().getInventory().get(i).getName().toLowerCase())) {
        playerObject.getInventory().add(playerObject.getCurrentRoom().getInventory().get(i));
        playerObject.getCurrentRoom().getInventory().remove(i);
        return true;
      }
    }
    return false;
  }

  public boolean dropItem(Player playerObject, String command) {
    command = command.substring(5);
    for (int i = 0; i < playerObject.getInventory().size(); i++) {
      if (command.equals(playerObject.getInventory().get(i).getName().toLowerCase())) {
        playerObject.getCurrentRoom().getInventory().add(playerObject.getInventory().get(i));
        playerObject.getInventory().remove(i);
        return true;
      }
    }
    return false;
  }

  public void checkInv(Player playerObject) {
    if (playerObject.getInventory().size() != 0)
      for (int i = 0; i < playerObject.getInventory().size(); i++) {
        System.out.println(playerObject.getInventory().get(i));
      }
    else {
      System.out.println("Your inventory is empty!");
    }
  }
}