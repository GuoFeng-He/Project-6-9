public class MantisLords extends Boss{
    Boss[] mantises;
    private int maxHealth;

    public MantisLords(int health, double bossArmor, int attack){
        super(health, attack, bossArmor);
        maxHealth = health;
        mantises = new Boss[] {new MantisLord(), new MantisLord(), new MantisLord()};
    }

    public void introDialogue(){
        System.out.println("Oh, it seems we have a new visitor");
        pause(500);
        System.out.println("Hate to break it to you, but our tribe isn't really comfortable with receiving new visitors");
        pause(500);
        System.out.println("Apologies, we unfortunately can't let you leave alive either, for risk of our sanctuary getting compromised");
        pause(500);
        System.out.println("Goodbye.");
    }

    public void midDialogue(){
        System.out.println("We shall not falter");
        pause(500);
        System.out.println("RISE!");
        buffAttack(25);
        for (Boss mantis : mantises){
            maxHealth *= 1.5;
            mantis.setHealth(maxHealth);
        }
        MantisLord.phase++;
    }

    public void endDialogue(){
        System.out.println("It seems we have been bested");
        pause(500);
        System.out.println("You have earned our respect");
        pause(500);
        System.out.println("Very well, you may proceed");
        pause(500);
        System.out.println("Maybe you really are our final hope in stopping this madness");
        System.out.println("...");
        pause(1000);
        System.out.println("Good Luck.");
    }
}
