package Game;

public class Item {
  private final String name;
  private int weight;

  public Item(String name, int weight) {
    this.name = name;
    this.weight = weight;
  }

  public String getName() {
    return name;
  }
  public String toString () {
    return this.name;
  }

  public int getWeight() {
    return weight;
  }
}

