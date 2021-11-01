package Game;

public class Enemy {
    private String name;
    private int health;
    private Weapon currentWeapon;

    public Enemy(String name, int health, Weapon currentWeapon) {
        this.name = name;
        this.health = health;
        this.currentWeapon = currentWeapon;
    }
    public void takeDamage (int damage) {
        health -= damage;
    }

    public int getHealth() {
        return health;
    }

    public String getName() {
        return name;
    }
    public String toString () {
        return name;
    }
}
