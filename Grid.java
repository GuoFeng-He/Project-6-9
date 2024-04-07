import java.util.Arrays;
import java.util.Scanner;

public class Grid {
    private String[][] grid;
    private int[] playerPos;
    private final String enemyEmoji = "\uD83E\uDD97";

    public Grid() {
        grid = new String[8][8];
        playerPos = new int[2];
        createGrid();
        printGrid();
        while (true) {
            System.out.print("Enter a direction (w/a/s/d): ");
            Scanner scan = new Scanner(System.in);
            movePlayer(scan.nextLine());
            if (Math.random() > 0.5) {
                int direction = (int) (Math.random() * 4);
                if (direction == 0 && playerPos[0] + 1 < grid.length && grid[playerPos[0] + 1][playerPos[1]].equals("⬛") && !grid[playerPos[0] + 1][playerPos[1]].equals("⬜")) {
                    grid[playerPos[0] + 1][playerPos[1]] = enemyEmoji; // down
                    System.out.println("You have encountered a monster!");
                } else if (direction == 1 && playerPos[0] - 1 >= 0 && grid[playerPos[0] - 1][playerPos[1]].equals("⬛") && !grid[playerPos[0] - 1][playerPos[1]].equals("⬜")) {
                    grid[playerPos[0] - 1][playerPos[1]] = enemyEmoji; // up
                    System.out.println("You have encountered a monster!");
                } else if (direction == 2 && playerPos[1] + 1 < grid.length && grid[playerPos[1] + 1][playerPos[1]].equals("⬛") && !grid[playerPos[0]][playerPos[1] + 1].equals("⬜")) {
                    grid[playerPos[0]][playerPos[1] + 1] = enemyEmoji; // right
                    System.out.println("You have encountered a monster!");
                } else if (direction == 3 && playerPos[1] - 1 >= 0 && grid[playerPos[1] - 1][playerPos[1]].equals("⬛") && !grid[playerPos[0]][playerPos[1] - 1].equals("⬜")) {
                    grid[playerPos[0]][playerPos[1] - 1] = enemyEmoji; // left
                    System.out.println("You have encountered a monster!");
                }
            }
            printGrid();
        }
    }

    public void movePlayer(String direction) {
        switch (direction) {
            case "w" -> moveUp();
            case "s" -> moveDown();
            case "a" -> moveLeft();
            case "d" -> moveRight();
            default -> System.out.println("YOU FUCKING ENTERED A WRONG DIRECTION");
        }
    }

    public void createGrid() {
        for (String[] strings : grid) {
            Arrays.fill(strings, "⬛");
        }
        int row = (int) (Math.random() * grid.length);
        int col = (int) (Math.random() * grid.length);
        playerPos[0] = row;
        playerPos[1] = col;
        grid[row][col] = "🤡";
        grid[(int) (Math.random() * grid.length)][(int) (Math.random() * grid.length)] = "\uD83D\uDFE9";
    }

    public void printGrid() {
        System.out.println("--------------------------------------");
        for (String[] row : grid) {
            for (String column : row) {
                System.out.print(column + " ");
            }
            System.out.println();
        }
    }

    public void moveUp() {
        if (playerPos[0] == 0) {
            System.out.println("You can't go up!");
            return;
        }
        if (grid[playerPos[0] - 1][playerPos[1]].equals(enemyEmoji)) {
            System.out.println("you gotta eat the green chicken");
            return;
        }
        grid[playerPos[0]][playerPos[1]] = "⬜";
        playerPos[0]--;
        grid[playerPos[0]][playerPos[1]] = "🤡";
    }

    public void moveDown() {
        if (playerPos[0] == grid.length - 1) {
            System.out.println("You can't go any further down!");
            return;
        }
        if (grid[playerPos[0] + 1][playerPos[1]].equals(enemyEmoji)) {
            System.out.println("you gotta eat the green chicken");
            return;
        }
        if (playerPos[0] < grid.length-1) {
            grid[playerPos[0]][playerPos[1]] = "⬜";
            playerPos[0]++;
            grid[playerPos[0]][playerPos[1]] = "🤡";
        }
    }

    public void moveLeft() {
        if (playerPos[1] == 0) {
            System.out.println("You can't go left!");
            return;
        }
        if (grid[playerPos[0]][playerPos[1] - 1].equals(enemyEmoji)) {
            System.out.println("you gotta eat the green chicken");
            return;
        }
        grid[playerPos[0]][playerPos[1]] = "⬜";
        playerPos[1]--;
        grid[playerPos[0]][playerPos[1]] = "🤡";
    }

    public void moveRight() {
        if (playerPos[1] == grid[0].length - 1) {
            System.out.println("You can't go any further right!");
            return;
        }
        if (grid[playerPos[0]][playerPos[1] + 1].equals(enemyEmoji)) {
            System.out.println("you gotta eat the green chicken");
            return;
        }
        if (playerPos[1] < grid[0].length-1) {
            grid[playerPos[0]][playerPos[1]] = "⬜";
            playerPos[1]++;
            grid[playerPos[0]][playerPos[1]] = "🤡";
        }
    }
}