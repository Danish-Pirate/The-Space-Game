package com.game;

import java.util.Scanner;

public class Adventure {


    public static void main(String[] args) {
        // Room objects



        System.out.println("Welcome to Cave Game!\nType \"Start\" to begin.");
        startCheck();
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