//flamecharmer
public class φλογοβόλος extends Enemy{

    public φλογοβόλος() {
        super(1000, 300);
    }
    @Override
    public int attackOne(){
        int damage = getAttack();
        System.out.println("The φλογοβόλος has hit you with his blade for " + damage + " damage.");
        return damage;
    }
    @Override
    public int attackTwo(){
        int damage = getAttack();
        System.out.println("The φλογοβόλος shoots a wave of fire from his palm, dealing " + damage + " damage.");
        return damage;
    }
    @Override
    public int[] selectAction(Player player){
        int random = (int)(Math.random() * 2);
        if (random == 1){
            return new int[] {attackOne(),1};
        }
        return new int[] {attackTwo(),1};
    }
}
