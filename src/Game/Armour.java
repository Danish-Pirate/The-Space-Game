package Game;

public class Armour extends Weapon {
  private final int maxHealthIncrease;
  private final int maxCarryWeightIncrease;

  public Armour(String name, int weight, int damage, int maxHealthIncrease, int maxCarryWeightIncrease) {
    super(name, weight, damage);
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
