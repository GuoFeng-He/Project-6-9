public class Enemy {
    private int health;
    private int attack;
    private double damageBoost;
    private boolean isVeteran;

    public Enemy(int health, int attack){
        this.health = health;
        this.attack = attack;
        damageBoost = 0;
    }

    public int getAttack(){
        return attack;
    }
    public void buffAttack(double multiplier){
        attack *= multiplier;
    }
    public int getHealth(){
        return health;
    }
    public boolean getVet(){
        return isVeteran;
    }

    // damage boost management
    public double getDamageBoost(){
        return damageBoost;
    }

    public void addDamageBoost(double boost){
        damageBoost += boost;
    }

    public void removeDamageBoost(){
        damageBoost = 0;
    }

    // placeholder for attacks and abilities
    public int attackOne(){
        return 0;
    }
    public int attackTwo(){
        return 0;
    }
    public int attackThree(){
        return 0;
    }
    public void abilityOne(){}
    public void abilityTwo(){}

    public void selectAttack(){}
}
