public class MantisLords extends Boss{
    public MantisLords(int health, double bossArmor, int attack){
        super(health, attack, bossArmor);
        Boss[] mantises = new Boss[] {new MantisLords(health, bossArmor, attack), new MantisLord(health, bossArmor, attack), new MantisLord(health, bossArmor, attack)};
    }

    public void introDialogue(){
        System.out.println();
    }

    public void midDialogue(){
        System.out.println();
    }

    public void endDialogue(){

    }
}
