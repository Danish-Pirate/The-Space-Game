package com.game;

import java.util.Scanner;

public class Adventure {
    // Room objects
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

    public static void main(String[] args) {


        System.out.println("Welcome to Space Game!\nType \"Start\" to begin.");
        startCheck();
        createMap();
        startGame();
    }
    // Checks if user typed "start"
    public static void startCheck() {
        Scanner input = new Scanner(System.in);
        boolean typedStart = false;
        while (!typedStart) {
            String startMessage = input.nextLine();
            startMessage = startMessage.toLowerCase();
            startMessage = startMessage.replaceAll(" ", "");
            if (startMessage.equals("start")) {
                typedStart = true;
            } else {
                System.out.println("Didn't type \"Start\", try again.");
            }
        }
    }
    // starts the game
    public static void startGame() {
        boolean isGameRunning = true;
        currentRoom = room1;
        Scanner input = new Scanner(System.in);
        System.out.println("You are in " + currentRoom.getName() + ". " + currentRoom.getRoomDescription());
        System.out.println("Type \"help\" to get help");
        while (isGameRunning) {
            String goMessage = input.nextLine();
            switch (goMessage) {
                case "go north": goNorth();
                break;
                case "go east": goEast();
                break;
                case "go south": goSouth();
                break;
                case "go west": goWest();
                break;
                case "exit": isGameRunning = false;
                break;
                case "look":
                    System.out.println(currentRoom.getRoomDescription());
                break;
                case "help":
                    System.out.println("\"go (north, south, east, west)\" to choose a direction to go." +
                        "\n\"look\" gives you a description of the room.\n\"exit\" stops the game.");
                break;
                default:
                    System.out.println("Unknown command");
                break;
            }
        }
    }
    public static void goNorth() {
        if (currentRoom.getNorthRoom() != null) {
            currentRoom = currentRoom.getNorthRoom();
            System.out.println("You are in " + currentRoom.getName() + ". " + currentRoom.getRoomDescription());
        } else {
            System.out.println("You cannot go that way");
        }
    }
    public static void goEast() {
        if (currentRoom.getEastRoom() != null) {
            currentRoom = currentRoom.getEastRoom();
            System.out.println("You are in " + currentRoom.getName() + ". " + currentRoom.getRoomDescription());
        } else {
            System.out.println("You cannot go that way");
        }
    }
    public static void goSouth() {
        if (currentRoom.getSouthRoom() != null) {
            currentRoom = currentRoom.getSouthRoom();
            System.out.println("You are in " + currentRoom.getName() + ". " + currentRoom.getRoomDescription());
        } else {
            System.out.println("You cannot go that way");
        }
    }
    public static void goWest() {
        if (currentRoom.getWestRoom() != null) {
            currentRoom = currentRoom.getWestRoom();
            System.out.println("You are in " + currentRoom.getName() + ". " + currentRoom.getRoomDescription());
        } else {
            System.out.println("You cannot go that way");
        }
    }
    public static void createMap() {
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