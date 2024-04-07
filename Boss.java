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
        health -= (int)(damage * (1.0 - bossArmor));
        if (health <= 0){
            health = 0;
            isDead = true;
        }
    }

    public int basicAttack(){
        return (int)(attack * (1 + getDamageBoost()));
    }

    public boolean isDead() {
        return isDead;
    }
}
