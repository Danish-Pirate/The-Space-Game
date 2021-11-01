package Game;

public class Enemy {
    private String name;
    private int health;
    private Weapon currentWeapon;
    private Room currentRoom;

    public Enemy(String name, int health, Weapon currentWeapon, Room currentRoom) {
        this.name = name;
        this.health = health;
        this.currentWeapon = currentWeapon;
        this.currentRoom = currentRoom;
    }

    public void dropWeapon () {
            currentRoom.addItem(currentWeapon);
    }

    public void takeDamage (int damage) {
        health -= damage;
    }

    public boolean isAlive() {
        if (health <= 0) {
            return false;
        } else {
            return true;
        }
    }

    public Weapon getCurrentWeapon() {
        return currentWeapon;
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
    public void dead () {
        dropWeapon();
        currentRoom.getListOfEnemies().remove(this);
    }
    public void attack (Player player) {
        player.takeDamage(currentWeapon.getDamage());
    }
}
