package Game;

public class RangeWeapon extends Weapon {
    private int ammo;

    public RangeWeapon (String name, int weight, int damage, int ammo) {
        super(name, weight, damage);
        this.ammo = ammo;
    }

    public int getAmmo() {
        return ammo;
    }
}
