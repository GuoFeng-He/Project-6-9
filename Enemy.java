public class Enemy {
    private int health;
    private int attack;
    private boolean isVeteran;
    public Enemy(int health, int attack, boolean isVeteran){
        this.health = health;
        this.attack = attack;
        this.isVeteran = isVeteran;
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
