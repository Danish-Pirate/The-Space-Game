package com.game;

import java.util.Scanner;

public class Adventure {
    public static void main(String[] args) {

        System.out.println("Welcome to Space Game!\nType \"Start\" to begin.");
        startCheck();
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
        Map mapCreator = new Map();
        boolean isGameRunning = true;
        Map.currentRoom = Map.room1;
        Scanner input = new Scanner(System.in);
        System.out.println("You are in " + Map.currentRoom.getName() + ". " + Map.currentRoom.getRoomDescription());
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
                    System.out.println(Map.currentRoom.getRoomDescription());
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
        if (Map.currentRoom.getNorthRoom() != null) {
            Map.currentRoom = Map.currentRoom.getNorthRoom();
            System.out.println("You are in " + Map.currentRoom.getName() + ". " + Map.currentRoom.getRoomDescription());
        } else {
            System.out.println("You cannot go that way");
        }
    }
    public static void goEast() {
        if (Map.currentRoom.getEastRoom() != null) {
            Map.currentRoom = Map.currentRoom.getEastRoom();
            System.out.println("You are in " + Map.currentRoom.getName() + ". " + Map.currentRoom.getRoomDescription());
        } else {
            System.out.println("You cannot go that way");
        }
    }
    public static void goSouth() {
        if (Map.currentRoom.getSouthRoom() != null) {
            Map.currentRoom = Map.currentRoom.getSouthRoom();
            System.out.println("You are in " + Map.currentRoom.getName() + ". " + Map.currentRoom.getRoomDescription());
        } else {
            System.out.println("You cannot go that way");
        }
    }
    public static void goWest() {
        if (Map.currentRoom.getWestRoom() != null) {
            Map.currentRoom = Map.currentRoom.getWestRoom();
            System.out.println("You are in " + Map.currentRoom.getName() + ". " + Map.currentRoom.getRoomDescription());
        } else {
            System.out.println("You cannot go that way");
        }
    }

}