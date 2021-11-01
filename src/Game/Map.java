package Game;

public class Map {

  private final Room starterRoom;
  private final Room endRoom;
    //Connects the rooms together
    public Map() {

        Room room1 = new Room("Medbay", "The place is sterile and the beeping of machines can be heard all over.");
        Room room1a = new Room("Armoury", "The wall is filled with different guns and on the table in the corner a vial with a health potion. Both might be useful! A small door to the east");
        Room secret1 = new Room("Secret room","A monster is sleeping in the corner, a small transparent box with a key under him");
        Room room2 = new Room("Shield Generator", "The shield generator seems to be doing its job of a producing a shield.");
        Room room3 = new Room("Hyperspace Drive", "The hyperspace drive is broken. Without it, warp speed travel is impossible.");
        Room room4 = new Room("Storage", "This is where all the essentials for living in space are stored.");
        Room room5 = new Room("Cockpit", "The cockpit, a pilot's home.");
        Room room6 = new Room("Canteen", "There is still food left on some of the trays. Looks like people didn't have enough time to finish it.");
        Room room7 = new Room("Weapon Systems", "State-of-art weapon system that would humble any ship.");
        Room room8 = new Room("Bunks", "Members of the crew sleep here. Looks like no one made their bed.");
        Room room9 = new Room("Engine", "The engine is responsible for the ship's propulsion, it's deafening. A door is ajar in the back wall");
        Room room9a = new Room("Lab", "A deserted lab, with puddles of unknown liquid spilled from vials. The shelf with books to the left looks to be... a bit fake. Is that a lock?");

        room1.createAndAddItem("Heavy Toolkit", 3);
        room4.createAndAddMeleeWeaponItem("Sharp Knife", 10, 10);
        room3.createAndAddRangeWeaponItem("Black Blaster", 5, 10, 10);
        secret1.createAndAddFoodItem("Ambrosia", 1, 50);
        room1.createAndAddItem("Red Keycard", 1);
        room1.createAndAddMeleeWeaponItem("Sturdy Flashlight", 3, 5);
        room1a.createAndAddFoodItem("Can of food", 2, 15);
        room1a.createAndAddArmourItem("Exo Suit", 5, 3, 20, 10);
        secret1.createAndAddItem("Green Keycard", 1);

        room2.createAndAddEnemy("Mantis", 20, new RangeWeapon("DC-15A", 5, 10, 10), room2);
        room2.createAndAddEnemy("Human", 20, new MeleeWeapon("Stun Stick", 5, 7), room2);
        room3.createAndAddEnemy("Human", 30, new RangeWeapon("DS-15S", 5, 8, 10), room3);
        room8.createAndAddEnemy("Android", 50, new MeleeWeapon("Vibro-blade", 10, 15), room8);

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
        room8.connectEastRoom(room9);
        room9.connectSouthRoom(room9a);

        starterRoom = room1;
        endRoom = room5;
    }

    public Room getStarterRoom() {
        return starterRoom;
    }

    public Room getEndRoom() {
        return endRoom;
    }
}
