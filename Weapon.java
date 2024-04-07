public class Weapon extends Items{
    private int dmg;
    public Weapon(String n, int p, int baseDmg) {
        super(n, p);
        this.dmg = baseDmg * (int) Math.round(Math.pow(1.75,getRarityNum()));;
    }

    public int getDmg() {
        return dmg;
    }

    @Override
    public String displayInfo() {
        return super.displayInfo() + "| Damage: " + dmg;
    }

    @Override
    public boolean upgradeRarity(int playerGold) {
        if(!super.upgradeRarity(playerGold)){
            dmg *= 1.75;
        }
        return true;
    }
}
