public class Ouroboros extends Boss{
    public Ouroboros(int health, double bossArmor, int attack){
        super(health, attack, bossArmor);
        introDialogue();
    }

    // dialogue
    public void introDialogue(){
        System.out.println("Oh, what's this?");
        System.out.println("Such insolence; a tiny bug dares come to challenge me, the World Serpent?");
        System.out.println("I'll crush you like a fly!");
    }

    public void midDialogue(){
        System.out.println("Impressive, you have exceeded my expectations.");
        System.out.println("Doesn't matter, it's going to take a lot more than that to kill me!");
        System.out.println();
    }

    public void endDialogue(){
        System.out.println("");
    }

    @Override
    public int basicAttack(){
        int damage = super.basicAttack();
        System.out.println("Ouroboros bites you for " + damage + " damage");
        return damage;
    }


}
