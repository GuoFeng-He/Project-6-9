public class MantisWarrior extends Enemy{
    public MantisWarrior(int health, int attack){
        super(health, attack, "grove");
    }

    @Override
    public int attackOne(){
        int damage = (int)(super.getAttack() * (1.5));
        System.out.println("The Mantis Warrior slashed you for " + damage + " damage!");
        return damage;
    }

    @Override
    public void abilityOne(){
        buffAttack(20);
        System.out.println("The Mantis Warrior enrages and buffed its attack by 20%");
    }

    @Override
    public void selectAction(){
        int random = (int)(Math.random() * 2);
        if (random == 0){
            attackOne();
        } else{
            abilityOne();
        }
    }
}
