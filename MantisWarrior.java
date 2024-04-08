public class MantisWarrior extends Enemy{
    public MantisWarrior(){
        super( 2500, 150);
    }

    @Override
    public String toString(){
        return "Mantis Warrior";
    }
    @Override
    public int attackOne(){
        int damage = (int)(super.getAttack() * (1.5));
        System.out.println("The Mantis Warrior slashed you for " + damage + " damage!");
        return damage;
    }

    @Override
    public void abilityOne(){
        buffAttack(1.2);
        System.out.println("The Mantis Warrior enrages and buffed its attack by 20%");
    }

    @Override
    public int[] selectAction(Player player){
        int random = (int)(Math.random() * 2);
        if (random == 0){
            return new int[]{attackOne(), 0};
        }
        abilityOne();
        return new int[]{attackTwo(), 0};
    }
}
