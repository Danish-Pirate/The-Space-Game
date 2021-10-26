package Game;

public class Map {

  private Room starterRoom;
    //Connects the rooms together
    public Map() {

        Room room1 = new Room("Medbay", "The place is sterile and the beeping of machines can be heard all over.");
        Room room1a = new Room("Warehouse", "The wall is filled with different guns and on the table in the corner a vial with a health potion. Both might be useful! A small door to the east");
        Room secret1 = new Room("Secret room1","A monster is sleeping in the corner, a small transparent box with a key under him");
        Room room2 = new Room("Shield Generator", "The shield generator seems to be doing its job of a producing a shield.");
        Room room3 = new Room("Hyperspace Drive", "The hyperspace drive is broken. Without it, warp speed travel is impossible.");
        Room room4 = new Room("Storage", "This is where all the essentials for living in space are stored.");
        Room room5 = new Room("Cockpit", "The cockpit, a pilot's home.");
        Room room6 = new Room("Canteen", "There is still food left on some of the trays. Looks like people didn't have enough time to finish it.");
        Room room7 = new Room("Weapon Systems", "State-of-art weapon system that would humble any ship.");
        Room room7a = new Room("Lair","?");
        Room room8 = new Room("Bunks", "Members of the crew sleep here. Looks like no one made their bed.");
        Room room9 = new Room("Engine", "The engine is responsible for the ship's propulsion, it's deafening. A door is ajar in the back wall");
        Room room9a = new Room("Lab", "A deserted lab, with puddles of unknown liquid spilled from vials. The shelf with books to the left looks to be... a bit fake. Is that a lock?");
        Room secret2 = new Room("Secret room","A, seemingly, untouched ejection pod sits on its station by a window");

        room1.addItem("Toolkit", 3);
        room5.addItem("Knife", 2);
        room3.addItem("Blaster", 3);
        room6.addItem("Stimkit", 1);
        room1.addItem("Red Keycard", 1);
        room1.addItem("Flashlight", 3);
        room1a.addItem("Can of food", 2);
        room1a.addItem("Exo-Suit", 5);
        secret1.addItem("Green Keycard", 1);

        room1.connectEastRoom(room2);
        room1.connectSouthRoom(room4);
        room1.connectNorthRoom(room1a);
        room1a.connectEastRoom(secret1);
        room2.connectEastRoom(room3);
        room3.connectSouthRoom(room6);
        room4.connectSouthRoom(room7);
        room5.connectSouthRoom(room8);
        room6.connectSouthRoom(room9);
        room7.connectEastRoom(room8);
        room7.connectWestRoom(room7a);
        room8.connectEastRoom(room9);
        room9.connectSouthRoom(room9a);
        room9a.connectEastRoom(secret2);
        starterRoom = room1;
    }

    public Room getStarterRoom() {
        return starterRoom;
    }
}
