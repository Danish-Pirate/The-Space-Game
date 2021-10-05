package Game;

public class Room {

    // Attributes
    final private String name;
    final private String roomDescription;
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

    public String getRoomDescription() {
        return roomDescription;
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
        if(northRoom.southRoom == null)
            northRoom.southRoom = this;
    }

    public void connectEastRoom(Room eastRoom) {
        setEastRoom(eastRoom);
        if(eastRoom.westRoom == null)
        eastRoom.westRoom = this;
    }

    public void connectSouthRoom(Room southRoom) {
        setSouthRoom(southRoom);
        if(southRoom.northRoom == null)
        southRoom.northRoom = this;
    }

    public void connectWestRoom(Room westRoom) {
        setWestRoom(westRoom);
        if(westRoom.eastRoom == null)
        westRoom.eastRoom = this;
    }
}



