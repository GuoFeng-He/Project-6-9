import java.util.ArrayList;

public class Room {
    private String description;
    private int worldNum;
    private String[][] area;
    private ArrayList<Enemy> enemies;
    public Room(String world, String description){
        this.description = description;
    }
    public String getDescription(){
        return description;
    }
    public int getWorldNum(){
        return worldNum;
    }
    public void updateWorld(){};

    public boolean clearRoom(){
        for(int r = 0; r < area.length; r++){
            for(int c = 0; c < area[0].length; c++){
                for(Enemy e : enemies){
                    if(r == e.getX() && c == e.getY()){
                        return false;
                    }
                }
            }
        }
        return true;
    }
    // Black: U+25FC
    //White: U+25FB
    public void makeRoom(String s){
        for(int r = 0; r < area.length; r++){
            for(int c = 0; c < area[0].length; c++){
                area[r][c] = new String(s);
            }
        }
    }
}