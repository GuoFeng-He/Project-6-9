public class Boss {
    private int health;
    private double bossArmor;
    private int attack;
    private double damageBoost;

    public Boss(int health, int attack, double bossArmor) {
        super(health, attack);
        this.bossArmor = bossArmor;
        damageBoost = 0;
    }

    public int getAttack(){
        return attack;
    }

    public void takeDamage(int damage){
        health -= (int)(damage * (1.0 - bossArmor));
    }

    public int basicAttack(){
        return (int)(attack * (1 + damageBoost));
    }



}
