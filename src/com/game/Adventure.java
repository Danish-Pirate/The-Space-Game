package com.game;

import java.util.Scanner;

public class Adventure {


    public static void main(String[] args) {
        // Room objects
        Room room1 = new Room(1, "The room is covered in cobwebs", 0, 2, 4, 0);
        Room room2 = new Room(2, "The room is covered in cheese", 0, 3, 0, 1);
        Room room3 = new Room(3, "The room is covered in lava", 0, 0, 6, 2);
        Room room4 = new Room(4, "The room is covered in dust", 1, 0, 7, 0);
        Room room5 = new Room(5, "The room is covered in pasta", 0, 0, 8, 0);
        Room room6 = new Room(6, "The room is covered in yoghurt", 3, 0, 9, 0);
        Room room7 = new Room(7, "The room is covered in tomatoes", 4, 8, 0, 0);
        Room room8 = new Room(8, "The room is covered in snow", 5, 9, 0, 7);
        Room room9 = new Room(9, "The room is covered in books", 6, 0, 0, 8);
        Room currentRoom = room1;


        System.out.println("Welcome to Cave Game!\nType \"Start\" to begin.");
        startCheck();
        System.out.println(currentRoom.getRoomDescription());
        go();


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
                System.out.println("Typed \"Start\"");
                typedStart = true;
            } else {
                System.out.println("Didn't type \"Start\", try again.");
            }
        }
    }
    // Prompts the user to choose a direction to go to
    public static void go() {
        Scanner input = new Scanner(System.in);
        System.out.println("Choose the direction you want to go.");
        String goMessage = input.nextLine();
        if (goMessage.equals("north")) {
            System.out.println("Going north");
        } else if (goMessage.equals("east")) {
            System.out.println("Going east");
        } else if (goMessage.equals("south")) {
            System.out.println("Going south");
        } else if (goMessage.equals("west")) {
            System.out.println("Going west");
        } else {
            System.out.println("Invalid message");
        }
    }
}