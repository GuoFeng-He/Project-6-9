public class MantisLord extends MantisLords{
    public static int count = 1;
    private int num;
    public MantisLord(int health, double bossArmor, int attack){
        super(health, bossArmor, attack);
        num = count;
        count++;
    }

    @Override
    public int basicAttack() {
        int damage = super.basicAttack();
        System.out.println("Mantis Lord " + num + " impaled you for " + damage + " damage!");
        return damage;
    }

    @Override
    public int attackOne(){
        int damage = (int)(super.getAttack() * 1.5 * (1 + super.getDamageBoost()));
        System.out.println("Mantis Lord " + num + " slashed you for " + damage + " damage! You will be taking " + (damage / 2) + " damage for the next two turns.");
        return damage;
    }

    @Override
    public void abilityOne(){
        System.out.println("Mantis Lord " + num + " used Field Promotion! \nAll enemies gain a 20% boost for their next attack");
        super.addDamageBoost(20);
    }


}
