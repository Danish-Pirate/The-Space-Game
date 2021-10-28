package Game;

public class Armour extends AbstractItem {
  private final int maxHealthIncrease;
  private final int maxCarryWeightIncrease;

  public Armour(String name, int weight, int maxHealthIncrease, int maxCarryWeightIncrease) {
    super(name, weight);
    this.maxHealthIncrease = maxHealthIncrease;
    this.maxCarryWeightIncrease = maxCarryWeightIncrease;
  }

  public int getMaxHealthIncrease() {
    return maxHealthIncrease;
  }

  public int getMaxCarryWeightIncrease() {
    return maxCarryWeightIncrease;
  }
}
