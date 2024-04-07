import java.util.ArrayList;
import java.util.Arrays;

public class Shop {
    private Player p;
    private ArrayList<Items> itemsSelling;
    private ArrayList<Items> possibleItems = new ArrayList<>(Arrays.asList(new LeatherArmor(), new Scimitar()));

    public Shop(Player p){
        this.p = p;
        System.out.println("Welcome to Merchant Miller's shop \uD83E\uDDD9");
        itemsSelling = new ArrayList<>();
        for(int i = 0; i < 6; i++){
            int x = (int) (Math.random() * possibleItems.size());
            itemsSelling.add(possibleItems.get(x));
        }
    }
    private void buy(int n){
        if (n > itemsSelling.size() || n < 1){
            return;
        }
        if(p.getGold() < itemsSelling.get(n-1).getPrice()){
            System.out.println("Come back when you have the money!");
        }else{
            p.addItem(itemsSelling.get(n-1));
        }
    }
}
