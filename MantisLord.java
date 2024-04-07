public class MantisLord extends MantisLords{
    public static int count = 1;
    private int num;
    private static int burstTracker; // tracks whenever the boss can perform their strongest attack

    public MantisLord(int health, double bossArmor, int attack){
        super(health, bossArmor, attack);
        num = count;
        burstTracker = 0;
        count++;
    }


    @Override
    public String toString() {
        return "Mantis Lord " + count;
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

    public int attackTwo(){
        burstTracker++;
        if (burstTracker < 5){
            System.out.println("Mantis Lord " + num + " has charged up the World Cutting Slash.");
        }
        else {
            addDamageBoost(20, 1);
            int damage = (int) (super.getAttack() * 4 * (1 + super.getDamageBoost()));
            System.out.println("The Mantis Lords unleashed the World Cutting Slash!\nYou took " + damage + " damage!");
            removeDamageBoost();
            burstTracker = 0;
            return damage;
        }
        return 0;
    }

    @Override
    public void abilityOne(){
        System.out.println("Mantis Lord " + num + " used Field Promotion! \nAll enemies gain a 20% boost for their next attack");
        super.addDamageBoost(20, 1);
    }

    @Override
    public void abilityTwo(){
        System.out.println("Mantis Lord " + num + " used Action Advance. It can take two actions this turn.");
        for (int i = 0; i < 2; i++){
            selectAction(false);
        }
    }

    // selects action, canRepeat is to make sure the boss can't chain attacks infinitely
    public void selectAction(boolean canRepeat) {
        if (super.getHealth() > 0) {
            int num = (int) (Math.random() * 7);
            if (num == 1 || num == 2) {
                basicAttack();
            } else if (num == 3) {
                attackOne();
            } else if (num == 4) {
                attackTwo();
            } else if (num == 5) {
                abilityOne();
            } else if (canRepeat) {
                abilityTwo();
            }
        }
    }
}
