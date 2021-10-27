package Game;

public class Food extends AbstractItem {
   private int healthPoints;

   public Food (String name, int weight, int healthPoints) {
       super(name, weight);
       this.healthPoints = healthPoints;
   }

    public int getHealthPoints() {
        return healthPoints;
    }
}
