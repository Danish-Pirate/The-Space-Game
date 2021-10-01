public class Map  {
    static Room currentRoom;
    static Room room1 = new Room("Medbay", "The place is sterile and the beeping of machines can be heard all over.");
    static Room room2 = new Room("Shield Generator", "The room is used to store the generator that generates the shield around the ship.");
    static Room room3 = new Room("Hyperspace Drive", "This room stores the hyperspace drive used for warp speed travel");
    static Room room4 = new Room("Storage", "A place used for storing things.");
    static Room room5 = new Room("Cockpit", "Where the pilot pilots the ship");
    static Room room6 = new Room("Canteen", "Good place to get some food.");
    static Room room7 = new Room("Weapon Systems", "This room is crucial for attacking other ships");
    static Room room8 = new Room("Bunks", "Everyone was in such a hurry that they didn't make their beds");
    static Room room9 = new Room("Engine", "The sound of the engine is deafening");
    public Map() {
        room1.setEastRoom(room2);
        room1.setSouthRoom(room4);
        room2.setWestRoom(room1);
        room2.setEastRoom(room3);
        room3.setWestRoom(room2);
        room3.setSouthRoom(room6);
        room4.setNorthRoom(room1);
        room4.setSouthRoom(room7);
        room5.setSouthRoom(room8);
        room6.setNorthRoom(room3);
        room6.setSouthRoom(room9);
        room7.setNorthRoom(room4);
        room7.setEastRoom(room8);
        room8.setWestRoom(room7);
        room8.setNorthRoom(room5);
        room8.setEastRoom(room9);
        room9.setWestRoom(room8);
        room9.setNorthRoom(room6);
    }
}
