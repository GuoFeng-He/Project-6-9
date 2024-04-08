public class MantisLord extends Boss{
    public static int count = 1;
    private int num;
    private static int burstTracker; // tracks whenever the boss can perform their strongest attack
    public static int phase;
    private boolean canRepeat;

    public MantisLord(){
        super(2000, 250, 0.21);
        num = count;
        burstTracker = 0;
        count++;
        phase = 1;
        canRepeat = true;
    }


    @Override
    public String toString() {
        return "Mantis Lord " + num;
    }

    @Override
    public int[] basicAttack() {
        int damage = super.getAttack();
        System.out.println("Mantis Lord " + num + " impaled you for " + damage + " damage!");
        return new int[]{damage, 0};
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
            removeDamageBoost(true);
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

    public void abilityTwo(Player player){
        System.out.println("Mantis Lord " + num + " used Action Advance. It can take two actions this turn.");
        for (int i = 0; i < 2; i++){
            canRepeat = false;
            selectAction(player);
        }
        canRepeat = true;
    }

    // selects action, canRepeat is to make sure the boss can't chain attacks infinitely
    public int[] selectAction(Player player) {
        if (super.getHealth() > 0) {
            removeDamageBoost(false);
            int num = (int) (Math.random() * 7);
            if (num == 1 || num == 2) {
                return basicAttack();
            } else if (num == 3) {
                return new int[]{attackOne(), 1};
            } else if (num == 4) {
                return new int[]{attackTwo(), 0};
            } else if (num == 5) {
                abilityOne();
            } else if (canRepeat) {
                abilityTwo(player);
            } else {
                return basicAttack();
            }
        }
        return new int[]{0, 0};
    }

    @Override
    public void printEnemy(){
        if (getHealth() > 0){
            super.printEnemy();
        } else {
            System.out.println(this + " << DOWN >>");
        }
    }
}
