public class Boss extends Enemy {
    private int health;
    private double bossArmor;
    private int attack;
    public int phase;
    public int maxPhases;
    public boolean isDead;

    public Boss(int health, int attack, double bossArmor) {
        super(health, attack);
        this.bossArmor = bossArmor;
        isDead = false;
    }

    // bosses have slight damage reduction because of boss armor
    @Override
    public void takeDamage(int damage){
        super.takeDamage((int)(damage * (1 - bossArmor)));
    }

    public int[] basicAttack(){
        return new int[]{(int)(attack * (1 + getDamageBoost())), 0};
    }

    public boolean isDead() {
        return isDead;
    }
}
