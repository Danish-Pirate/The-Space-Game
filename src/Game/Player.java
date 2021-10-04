package Game;

public class Player {
  //Attributes
  private Room currentRoom;

  // Handles player movement and commands
  public boolean go(Parser.Direction direction) {
    Room requestRoom = null;
    if (direction == Parser.Direction.NORTH) {
      requestRoom = currentRoom.getNorthRoom();
    } else if (direction == Parser.Direction.SOUTH) {
      requestRoom = currentRoom.getSouthRoom();
    } else if (direction == Parser.Direction.EAST) {
      requestRoom = currentRoom.getEastRoom();
    } else if (direction == Parser.Direction.WEST) {
      requestRoom = currentRoom.getWestRoom();
    }
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
}