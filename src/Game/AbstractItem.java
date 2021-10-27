package Game;

public abstract class AbstractItem {
  private final String name;
  private final String shortName;
  private int weight;

  public AbstractItem(String name, int weight) {
    this.name = name;
    this.weight = weight;
    String[] words = name.split(" ");
    this.shortName = words[words.length - 1];
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
  public String getShortName() {
    return shortName;
  }
}

