package Game;

public class Player {
    //Attributes
    private Room currentRoom;

    // Handles player movement and commands
    public boolean go(Game.Direction direction) {
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
}