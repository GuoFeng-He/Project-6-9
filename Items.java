public class Items {
    private int price;
    private String rarity;
    private int rarityNum;
    private String name;
    public Items(String n, int p){
        name = n;
        double rarityRoll = (Math.random());
        if (rarityRoll < 0.01){
            rarity = Color.BLACK + "secret" + Color.RESET;
            rarityNum = 3;
        }else if (rarityRoll < 0.25){
            rarity = Color.YELLOW + "legendary" + Color.RESET;
            rarityNum = 2;
        }else {
            rarity = Color.BLUE + "rare" + Color.RESET;
            rarityNum = 1;
        }
        price = p * rarityNum;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
    public String getRarity() {
        return rarity;
    }

    public int getRarityNum() {
        return rarityNum;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    public void setRarity(String rarity) {
        this.rarity = rarity;
    }
    public boolean upgradeRarity(int playerGold){
        if (rarityNum + 1 > 3){
            System.out.println("You can't upgrade further");
        }else if (playerGold < price * 2){
            System.out.println("You simply can't afford it");
        }else{
            rarityNum += 1;
            if (rarityNum == 2){
                setRarity("Legendary");
            }else{
                setRarity("Secret?!");
            }
            setPrice(price *= 1.5);
            System.out.println("You have successfully upgraded your " + getClass() + " to " + getRarity());
            return false;
        }
        return true;
    }
    public String displayInfo(){
        return "Name: "+ name + "| Price: " + price + "| Rarity: " + getRarity();
    }
}
