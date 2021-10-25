package Game;

import java.util.ArrayList;

public class Player {
    //Attributes
    private Room currentRoom;
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
}