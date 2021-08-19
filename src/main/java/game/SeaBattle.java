package game;

import java.util.*;

public class SeaBattle {
    private final int[][] board = new int[10][10];
    private int[][] playersBoard = new int[10][10];
    private Random random = new Random();
    private List<LinkedList<ArrayList<Integer>>> list = new LinkedList<>();
    private int size;
    private Scanner scanner = new Scanner(System.in);


    private void setSize(int size) {
        this.size = size;
    }

    public void initialize() {
        int length = 4;
        int count = 1;
        while (length >= 1) {
            for (int i = 0; i < count; i++) {
                generateCell(length);
            }
            count++;
            length--;
        }
        setSize(list.size());
    }

    private void generateCell(int length) {
        int x = random.nextInt(10);
        int y = random.nextInt(10);
        int z = random.nextInt(2);
        if (z == 0) {
            if (x < 5 && isDownAvailable(x, y, length)) {
                goDown(x, y, length);
            } else if (x >= 5 && isUpAvailable(x, y, length)) {
                goUp(x, y, length);
            } else generateCell(length);
        } else {
            if (y < 5 && isRightAvailable(x, y, length)) {
                goRight(x, y, length);
            } else if (y >= 5 && isLeftAvailable(x, y, length)) {
                goLeft(x, y, length);
            } else generateCell(length);
        }
    }

    private boolean isLeftAvailable(int x, int y, int length) {
        int z = y;
        for (int i = 0; i < length; i++) {
            if (board[x][z] == 0) {
                z--;
            } else break;
        }
        return z == y - length;
    }

    private void goLeft(int x, int y, int length) {
        if (x != 9 && y != 9) board[x + 1][y + 1] = 2;
        if (y != 9) board[x][y + 1] = 2;
        if (x != 0 && y != 9) board[x - 1][y + 1] = 2;
        LinkedList<ArrayList<Integer>> temp = new LinkedList<>();
        list.add(temp);
        for (int i = 0; i < length; i++) {
            if (x != 9) board[x + 1][y] = 2;
            board[x][y] = 1;
            temp.push(createArrayList(x, y));
            if (x != 0) board[x - 1][y] = 2;
            y--;
        }
        if (x != 9 && y != -1) board[x + 1][y] = 2;
        if (y != -1) board[x][y] = 2;
        if (x != 0 && y != -1) board[x - 1][y] = 2;
    }

    private ArrayList<Integer> createArrayList(int x, int y) {
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(x);
        arr.add(y);
        return arr;
    }

    private boolean isRightAvailable(int x, int y, int length) {
        int z = y;
        for (int i = 0; i < length; i++) {
            if (board[x][z] == 0) {
                z++;
            } else break;
        }
        return z == y + length;
    }

    private void goRight(int x, int y, int length) {
        if (x != 9 && y != 0) board[x + 1][y - 1] = 2;
        if (y != 0) board[x][y - 1] = 2;
        if (x != 0 && y != 0) board[x - 1][y - 1] = 2;
        LinkedList<ArrayList<Integer>> temp = new LinkedList<>();
        list.add(temp);
        for (int i = 0; i < length; i++) {
            if (x != 9) board[x + 1][y] = 2;
            board[x][y] = 1;
            temp.push(createArrayList(x, y));
            if (x != 0) board[x - 1][y] = 2;
            y++;
        }
        if (x != 9 && y != 10) board[x + 1][y] = 2;
        if (y != 10) board[x][y] = 2;
        if (x != 0 && y != 10) board[x - 1][y] = 2;
    }

    private boolean isDownAvailable(int x, int y, int length) {
        int z = x;
        for (int i = 0; i < length; i++) {
            if (board[z][y] == 0) {
                z++;
            } else break;
        }
        return z == x + length;
    }

    private void goDown(int x, int y, int length) {
        if (x != 0 && y != 0) board[x - 1][y - 1] = 2;
        if (x != 0) board[x - 1][y] = 2;
        if (x != 0 && y != 9) board[x - 1][y + 1] = 2;
        LinkedList<ArrayList<Integer>> temp = new LinkedList<>();
        list.add(temp);
        for (int i = 0; i < length; i++) {
            if (y != 9) board[x][y + 1] = 2;
            board[x][y] = 1;
            temp.push(createArrayList(x, y));
            if (y != 0) board[x][y - 1] = 2;
            x++;
        }
        if (x != 10 && y != 9) board[x][y + 1] = 2;
        if (x != 10) board[x][y] = 2;
        if (x != 10 && y != 0) board[x][y - 1] = 2;
    }

    private boolean isUpAvailable(int x, int y, int length) {
        int z = x;
        for (int i = 0; i < length; i++) {
            if (board[z][y] == 0) {
                z--;
            } else break;
        }
        return z == x - length;
    }

    private void goUp(int x, int y, int length) {
        if (x != 9 && y != 0) board[x + 1][y - 1] = 2;
        if (x != 9) board[x + 1][y] = 2;
        if (x != 9 && y != 9) board[x + 1][y + 1] = 2;
        LinkedList<ArrayList<Integer>> temp = new LinkedList<>();
        list.add(temp);
        for (int i = 0; i < length; i++) {
            if (y != 9) board[x][y + 1] = 2;
            board[x][y] = 1;
            temp.push(createArrayList(x, y));
            if (y != 0) board[x][y - 1] = 2;
            x--;
        }
        if (x != -1 && y != 9) board[x][y + 1] = 2;
        if (x != -1) board[x][y] = 2;
        if (x != -1 && y != 0) board[x][y - 1] = 2;
    }

    public void print() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    private boolean isCorrectCell(int x, int y) {
        return board[x][y] == 1;
    }

    private void remove(int x, int y) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(i).size(); j++) {
                if (x == list.get(i).get(j).get(0)
                        && y == list.get(i).get(j).get(1)) {
                    list.get(i).remove(j);
                    if (list.get(i).size() == 0) list.remove(i);
                    break;
                }
            }
        }
    }

    private void playersBoardFilling(int x, int y) {
        if (isCorrectCell(x, y)) {
            playersBoard[x][y] = 1; // success
        } else playersBoard[x][y] = 2; // fail
    }

    public void play() {
        int count = 100;
        int x, y;
        while (count > 0 && (list.size() != 0)) {
            x = receiveFirstCoordinate();
            y = receiveSecondCoordinate();
            playersBoardFilling(x, y);
            if (isCorrectCell(x, y)) {
                remove(x, y);
                if (size > list.size()) {
                    System.out.println("Killed");
                    size = list.size();
                } else System.out.println("Wounded");
            } else System.out.println("Fail");
            count--;
        }
        if (count == 0) {
            System.out.println("Game over");
        } else System.out.println("Win");
    }

    private int receiveFirstCoordinate() {

        int x;
        System.out.println("The first coordinate (1,2,3,4,5,6,7,8,9,10)");
        while (true) {
            if (!scanner.hasNextInt()) {
                scanner.nextLine();
            } else {
                x = scanner.nextInt();
                break;
            }
        }
        return x - 1;

    }

    private int receiveSecondCoordinate() {
        System.out.println("The second coordinate (a,b,c,d,e,f,g,h,i,j)");

        char y = scanner.next().charAt(0);

        if (y >= 'a' && y <= 'j') {
            return getInt(y);
        } else return receiveSecondCoordinate();
    }

    private int getInt(char y) {
        if (y == 'a') return 0;
        if (y == 'b') return 1;
        if (y == 'c') return 2;
        if (y == 'd') return 3;
        if (y == 'e') return 4;
        if (y == 'f') return 5;
        if (y == 'g') return 6;
        if (y == 'h') return 7;
        if (y == 'i') return 8;
        return 9;
    }
}
