import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Shop {
    private Player p;
    private boolean leaveShop;
    private ArrayList<Items> itemsSelling;
    private Scanner scan = new Scanner(System.in);
    private ArrayList<Items> possibleItems = new ArrayList<>(Arrays.asList(new LeatherArmor(),new ObsidianArmor(), new NetheriteArmor(), new Scimitar(), new Katana(), new DevilsPaintbrush()));

    public Shop(Player p){
        leaveShop = false;
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
    private void menu(){
        while (!leaveShop) {
            System.out.println("1. (B)uy");
            System.out.println("2. (E)quip");
            System.out.println("3. (L)eave");
            String option = scan.nextLine().toLowerCase();
            if (!option.equals("b") && !option.equals("l") && !option.equals("e")) {
                System.out.println("Invalid option, try again");
                menu();
            } else if (option.equals("b")) {
                showShop();
            } else if (option.equals("e")){
                p.equipInventory();
            }else {
                leaveShop = true;
                if (Grid.worldNum == 1) {
                    new ForsakenGrove();
                } else if (Grid.worldNum == 2) {
                    new αβίωσητέφρα();
                } else if (Grid.worldNum == 3) {
                    new LaboratoryZero();
                }
            }
        }
    }
    private void showShop(){
        System.out.println("Items Selling: ");
        for (int i = 0; i < itemsSelling.size(); i++) {
            System.out.println((i+1) + ". " + itemsSelling.get(i).displayInfo());
        }
        System.out.print("Enter the number you want to buy (0 to leave): ");
        int option = scan.nextInt();
        if (option < 0 || option > itemsSelling.size()){
            System.out.println("Invalid option, tru again");
            showShop();
        }else if(option != 0){
            p.addItem(itemsSelling.remove(option-1));
            showShop();
        }
    }
}
