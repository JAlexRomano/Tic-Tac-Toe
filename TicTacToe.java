import java.util.Scanner;
public class TicTacToe {
    final static int ROW_SIZE = 3;
    final static int COLUMN_SIZE = 3;
    final static int BOARD_SIZE = ROW_SIZE * COLUMN_SIZE;
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String[][] board = new String[3][3];
        resetArray(board);

        outerLoop:
        while(true) {
            System.out.println();
            System.out.println("1 | 2 | 3");
            System.out.println("----------");
            System.out.println("4 | 5 | 6");
            System.out.println("----------");
            System.out.println("7 | 8 | 9");
            System.out.println();

            for (int i = 0; i < BOARD_SIZE; i++) {
                System.out.print("Where would player " + (i % 2 + 1) + " like to make their move?: ");
                if (s.hasNextInt()) {
		            int input = s.nextInt(); 
                    int row = (input - 1) / 3;
                    int column = (input - 1) % 3;
                    System.out.println();
                    if (isValidInput(row, column) && board[row][column].equals(" ")) {
                        int playerTurn = i % 2 + 1;
                        if (playerTurn == 1) board[row][column] = "x";
                        else if (playerTurn == 2) board[row][column] = "o";
                    }
                    else {
                            System.out.println("Invalid move");
                            i--;
                            continue;
                    }
    
                    printBoard(board);
    
                    System.out.println();
                    if (winner(board)) {
                        System.out.println("Player " + (i % 2 + 1) + " wins!!!");
                        resetArray(board);
                        while (true) {
                            System.out.print("Continue(y/n)? ");
                            String proceed = s.next();
                            if (proceed.toLowerCase().equals("y")) continue outerLoop;
                            else if (proceed.toLowerCase().equals("n")) break outerLoop; 
                            else System.out.println("Invalid Input.");
                        }
                    }
                }
                else {
                    s.next();
                    if (s.hasNextLine()) {
                        System.out.println("Invalid input");
                        i--;
                    }
                } 
            }
        } 
        s.close();
        System.out.println("Thank you for playing :)");
    }

    public static boolean isValidInput(int one, int two) {
        return one <= ROW_SIZE - 1 && one >= 0 &&
            two <= COLUMN_SIZE - 1 && two >= 0;    
    }

    public static void printBoard(String array[][]) {
        for (int k = 0; k < ROW_SIZE; k++) {
            for (int l = 0; l < COLUMN_SIZE; l++) {
                if (l == 2) {
                    System.out.println(array[k][l]);
                    System.out.println("----------");
                }
                else {
                    System.out.print(array[k][l] + " | ");
                }
            }
        }
    }

    public static void resetArray(String array[][]) {
        for (int row = 0; row < ROW_SIZE; row++) {
            for (int column = 0; column < COLUMN_SIZE; column++) {
                array[row][column] = " ";
            }
        }
    }

    public static boolean threeInARow(String[][] array) {
        for (int row = 0; row < ROW_SIZE; row++) {
            for (int column = 0; column < COLUMN_SIZE; column++) {
                if (array[row][column] == " ") {
                    continue;
                }
                else {
                    int i = 0;
                    if (array[row][column] == array[row][i] &&
                    array[row][column] == array[row][i+1] &&
                    array [row][column] == array[row][i+2]) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean threeInAColumn(String [][] array) {
        for (int row = 0; row < ROW_SIZE; row++) {
            for (int column = 0; column < COLUMN_SIZE; column++) {
                if (array[row][column] == " ") {
                    continue;
                }
                else {
                    int i = 0;
                    if (array[row][column] == array[i][column] &&
                    array[row][column] == array[i+1][column] &&
                    array [row][column] == array[i+2][column]) {
                        return true;
                    }
                    
                }
            }
        }
        return false;
    }
    
    public static boolean threeAcross(String[][] array) {
        for (int row = 0; row < ROW_SIZE; row++) {
            for (int column = 0; column < COLUMN_SIZE; column++) {
                if (array[row][column] == " ") {
                    continue;
                }
                else {
                    int i = 0;
                    int j = 0;
                    if (row%(ROW_SIZE-1) == 0 && column%(COLUMN_SIZE-1) == 0) {
                        if ((array[row][column] == array[i][j] &&
                        array[row][column] == array[i+1][j+1] &&
                        array [row][column] == array[i+2][j+2]) ||
                        (array[row][column] == array[i][j+2] &&
                        array[row][column] == array[i+1][j+1] &&
                        array[row][column] == array[i+2][j])) {
                        return true;
                        }
                    }
                    else {
                        break;
                    }
                    
                }
            }
        }
        return false;
    }

    public static boolean winner(String[][] array) {
        return threeInARow(array) || 
        threeInAColumn(array) || 
        threeAcross(array);
    }    
}

