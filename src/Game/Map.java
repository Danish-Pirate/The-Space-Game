package Game;

public class Map {

   Room starterRoom;
    //Connects the rooms together
    public Map() {

        // Creates the room objects
        Room room1 = new Room("Medbay", "The place is sterile and the beeping of machines can be heard all over.");
        Room room2 = new Room("Shield Generator", "The room is used to store the generator that generates the shield around the ship.");
        Room room3 = new Room("Hyperspace Drive", "This room stores the hyperspace drive used for warp speed travel");
        Room room4 = new Room("Storage", "A place used for storing things.");
        Room room5 = new Room("Cockpit", "Where the pilot pilots the ship");
        Room room6 = new Room("Canteen", "Good place to get some food.");
        Room room7 = new Room("Weapon Systems", "This room is crucial for attacking other ships");
        Room room8 = new Room("Bunks", "Everyone was in such a hurry that they didn't make their beds");
        Room room9 = new Room("Engine", "The sound of the engine is deafening");

        room1.setEastRoom(room2);
        room1.setSouthRoom(room4);
        room2.setEastRoom(room3);
        room3.setSouthRoom(room6);
        room4.setSouthRoom(room7);
        room5.setSouthRoom(room8);
        room6.setSouthRoom(room9);
        room7.setEastRoom(room8);
        room8.setEastRoom(room9);
        starterRoom = room1;
    }

    public Room getStarterRoom() {
        return starterRoom;
    }
}
