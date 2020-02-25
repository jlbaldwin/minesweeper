package com.jon;

public class Board {

    private int row = 10; //may set this to a user prompt
    private int col = 10; //may set this to a user prompt
    private int numBombs = 10;
    private int max = row - 1;
    private int min = 0;
    private Cell[][] gameBoard = new Cell[row][col];
    boolean firstMove = true;


    public Board() {
        //create board
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                gameBoard[i][j] = new Cell();
            }
        }
    }

    public boolean userMove(int[] coords){
        System.out.println("user move was: " + coords[0] + " " + coords[1] );

        if(firstMove){
            gameBoard[coords[1]][coords[0]].setExplored(true);
            assignBombs(coords);
            firstMove = false;
            gameBoard[coords[1]][coords[0]].setValue(gameBoard[coords[1]][coords[0]].getAdjacentBombs());
            printGameBoard();
            return true;
        }

        if(gameBoard[coords[1]][coords[0]].isBomb()){

            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if(gameBoard[coords[j]][coords[i]].isBomb()){
                        gameBoard[coords[j]][coords[i]].setValue('*');
                    }
                }
            }
            printGameBoard();
            System.out.println("Oh no, that was a bomb! Game Over :(");
            return false;
        }
        else {
            gameBoard[coords[1]][coords[0]].setExplored(true);
            gameBoard[coords[1]][coords[0]].setValue(gameBoard[coords[1]][coords[0]].getAdjacentBombs());
            printGameBoard();
            return true;
        }
    }

    public void assignBombs(int[] coords){
        //randomly assign bombs
        for (int i = 0; i < numBombs; i++) {
            boolean validCell = false;
            do {
                int randRow = (int) (Math.random() * ((max - min) + 1));
                int randCol = (int) (Math.random() * ((max - min) + 1));
                if (!gameBoard[randRow][randCol].isBomb() && randRow != coords[1] && randCol != coords[0]) {
//                    gameBoard[randRow][randCol].setValue('*'); //testing comment for random bomb placement
                    gameBoard[randRow][randCol].setBomb(true);
                    validCell = true;
                }
//                System.out.println("counter and valid cell: " + i + " " + validCell);
//                System.out.println("rand row col: " + randRow + " " + randCol);
            } while (validCell == false);
        }


        //Add logic for perimeter cells
        //count adjacent bombs
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int bombCount = 0;
                char adjacentBombs;

                //test for top row
                if (i == 0) {
                    //upper left corner cell
                    if (j == 0) {
                        if (gameBoard[i][j + 1].isBomb()) {
                            bombCount++;
                        }
                        if (gameBoard[i + 1][j].isBomb()) {
                            bombCount++;
                        }
                        if (gameBoard[i + 1][j + 1].isBomb()) {
                            bombCount++;
                        }
                    }

                    //upper right corner cell
                    if (j == 9) {
                        if (gameBoard[i][j - 1].isBomb()) {
                            bombCount++;
                        }
                        if (gameBoard[i + 1][j - 1].isBomb()) {
                            bombCount++;
                        }
                        if (gameBoard[i + 1][j].isBomb()) {
                            bombCount++;
                        }
                    }
                    //top middle cells
                    if( j != 0 && j != 9){
                        if (gameBoard[i][j - 1].isBomb()) {
                            bombCount++;
                        }
                        if (gameBoard[i][j + 1].isBomb()) {
                            bombCount++;
                        }
                        if (gameBoard[i + 1][j - 1].isBomb()) {
                            bombCount++;
                        }
                        if (gameBoard[i + 1][j].isBomb()) {
                            bombCount++;
                        }
                        if (gameBoard[i + 1][j + 1].isBomb()) {
                            bombCount++;
                        }
                    }
                }

                //test for bottom row
                if (i == 9) {
                    //bottom left corner cell
                    if (j == 0) {
                        if (gameBoard[i -1][j].isBomb()) {
                            bombCount++;
                        }
                        if (gameBoard[i - 1][j + 1].isBomb()) {
                            bombCount++;
                        }
                        if (gameBoard[i][j + 1].isBomb()) {
                            bombCount++;
                        }
                    }
                    //bottom right corner cell
                    if (j == 9) {
                        if (gameBoard[i][j - 1].isBomb()) {
                            bombCount++;
                        }
                        if (gameBoard[i - 1][j - 1].isBomb()) {
                            bombCount++;
                        }
                        if (gameBoard[i - 1][j].isBomb()) {
                            bombCount++;
                        }
                    }
                    //bottom middle cells
                    if ( j != 0 && j != 9){
                        if (gameBoard[i][j - 1].isBomb()) {
                            bombCount++;
                        }
                        if (gameBoard[i - 1][j - 1].isBomb()) {
                            bombCount++;
                        }
                        if (gameBoard[i - 1][j].isBomb()) {
                            bombCount++;
                        }
                        if (gameBoard[i - 1][j + 1].isBomb()) {
                            bombCount++;
                        }
                        if (gameBoard[i][j + 1].isBomb()) {
                            bombCount++;
                        }
                    }
                }

                //test for left side
                if (j == 0 && i != 0 && i != 9) {
                    //bottom left corner cell
                    if (gameBoard[i - 1][j].isBomb()) {
                        bombCount++;
                    }
                    if (gameBoard[i - 1][j + 1].isBomb()) {
                        bombCount++;
                    }
                    if (gameBoard[i][j + 1].isBomb()) {
                        bombCount++;
                    }
                    if (gameBoard[i + 1][j + 1].isBomb()) {
                        bombCount++;
                    }
                    if (gameBoard[i + 1][j].isBomb()) {
                        bombCount++;
                    }
                }

                //test for right side
                if (j == 9 && i != 0 && i != 9) {
                    //bottom left corner cell
                    if (gameBoard[i -1][j].isBomb()) {
                        bombCount++;
                    }
                    if (gameBoard[i - 1][j - 1].isBomb()) {
                        bombCount++;
                    }
                    if (gameBoard[i][j - 1].isBomb()) {
                        bombCount++;
                    }
                    if (gameBoard[i + 1][j - 1].isBomb()) {
                        bombCount++;
                    }
                    if (gameBoard[i + 1][j].isBomb()) {
                        bombCount++;
                    }
                }

                if(i != 0 && i != 9 && j != 0 && j != 9){
                    if(gameBoard[j - 1][i - 1].isBomb()){
                    bombCount++;
                    }
                    if(gameBoard[j - 1][i].isBomb()){
                        bombCount++;
                    }
                    if(gameBoard[j - 1][i + 1].isBomb()){
                        bombCount++;
                    }
                    if(gameBoard[j][i + 1].isBomb()){
                        bombCount++;
                    }
                    if(gameBoard[j + 1][i + 1].isBomb()){
                        bombCount++;
                    }
                    if(gameBoard[j + 1][i].isBomb()){
                        bombCount++;
                    }
                    if(gameBoard[j + 1][i - 1].isBomb()){
                        bombCount++;
                    }
                    if(gameBoard[j][i - 1].isBomb()){
                        bombCount++;
                    }
                }

                adjacentBombs = (char)(bombCount + '0');
                gameBoard[j][i].setAdjacentBombs(adjacentBombs);
            }
        }
    }

    public void printGameBoard(){
        for(int i = 0; i <= row; i++){
            for(int j = 0; j <= col; j++){
                if(i == 0 && j == 0){
                    System.out.print("  ");
                }
                else if(i == 0 && j != 0){
                    System.out.print((j - 1) + " ");
                }
                else if(i != 0 && j == 0){
                    System.out.print((i - 1) + " ");
                }
                else{
                    System.out.print(gameBoard[i - 1][j - 1].getValue() + " ");
                }
            }
            System.out.print("\n");
        }
    }
}
