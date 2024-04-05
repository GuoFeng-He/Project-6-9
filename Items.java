public class Items {
    private int price;
    private String rarity;
    private int rarityNum;
    public Items(int p, String r, int rNum){
        price = p;
        rarity = r;
        rarityNum = rNum;
    }

    public int getPrice() {
        return price;
    }
    public String getRarity() {
        return rarity;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public void setRarity(String rarity) {
        this.rarity = rarity;
    }
    public void upgradeRarity(int playerGold){
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
        }
    }
}
