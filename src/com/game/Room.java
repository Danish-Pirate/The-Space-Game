package com.game;

public class Room {
  // Attributes
  private int roomName;
  private String roomDescription;
  private int northRoom;
  private int eastRoom;
  private int southRoom;
  private int westRoom;

  //constructor
  public Room(int roomNumber, String roomDescription, int northRoom, int eastRoom, int southRoom, int westRoom) {
    this.roomName = roomName;
    this.roomDescription = roomDescription;
    this.northRoom = northRoom;
    this.eastRoom = eastRoom;
    this.southRoom = southRoom;
    this.westRoom = westRoom;
  }
  public int getRoomNumber() {
    return this.roomName;
  }
  public String getRoomDescription() {
    return this.roomDescription;
  }
  public int getNorthRoom() {
    return this.northRoom;
  }
  public int getEastRoom() {
    return this.eastRoom;
  }
  public int getSouthRoom() {
    return this.southRoom;
  }
  public int getWestRoom() {
    return this.westRoom;
  }

}

