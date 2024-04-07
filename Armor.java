public class Armor extends Items{
    private int hp;
    public Armor(String n, int p,int baseHp) {
        super(n, p);
        this.hp = baseHp * (int) Math.round(Math.pow(2,getRarityNum()));
    }

    public int getHp() {
        return hp;
    }

    @Override
    public String displayInfo() {
        return super.displayInfo() + "| Health: " + hp;
    }

    @Override
    public boolean upgradeRarity(int playerGold) {
        if(!super.upgradeRarity(playerGold)){
            hp *= 2;
        }
        return true;
    }
}
