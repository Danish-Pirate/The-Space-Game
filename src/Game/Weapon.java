package Game;

public abstract class Weapon extends AbstractItem {
   private final int damage;

    public Weapon (String name, int weight, int damage) {
        super(name, weight);
        this.damage = damage;
    }
    public int getDamage() {
        return damage;
    }
}
