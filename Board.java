package com.jon;

import org.jetbrains.annotations.NotNull;

public class Board {

    private int row = 10; //may set this to a user prompt
    private int col = 10; //may set this to a user prompt
    private int numBombs = 2;
    private int setBombs = 0;
    private int max = row - 1;
    private int min = 0;
    private Cell[][] gameBoard = new Cell[row][col];
    boolean firstcoords = true;

    /******************************************************************************

     ******************************************************************************/
    public Board() {
        //create board
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                gameBoard[i][j] = new Cell();
            }
        }
    }

    /******************************************************************************

     ******************************************************************************/
    public boolean userMove(int[] coords){

        if(firstcoords){
            gameBoard[coords[0]][coords[1]].setExplored(true);
            assignBombs(coords);
            firstcoords = false;
            gameBoard[coords[0]][coords[1]].setValue(gameBoard[coords[0]][coords[1]].getAdjacentBombs());

            if(gameBoard[coords[0]][coords[1]].getValue() == '0') {
                System.out.println("first move coordinates selected have 0 adjacent bombs");
                autoExpandSelection(coords);
            }
            printGameBoard();
            return true;
        }

        //if selection was a bomb, update values with '*' for bombs, display board, then end game
        if(gameBoard[coords[0]][coords[1]].isBomb()){
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if(gameBoard[i][j].isBomb()){
                        gameBoard[i][j].setValue('*');
                    }
                }
            }
            printGameBoard();
            System.out.println("Oh no, that was a bomb! Game Over :(");
            return false;
        }
        else if(gameBoard[coords[0]][coords[1]].getAdjacentBombs() == '0'){
            System.out.println("coordinates selected have 0 adjacent bombs");
            autoExpandSelection(coords);
            printGameBoard();
            return true;
        }
        else {
            System.out.println("coordinates selected have atleast 1 adjacent bomb");
            gameBoard[coords[0]][coords[1]].setExplored(true);
            gameBoard[coords[0]][coords[1]].setValue(gameBoard[coords[0]][coords[1]].getAdjacentBombs());
            printGameBoard();
            return true;
        }
    }


    /******************************************************************************

     ******************************************************************************/
    public void autoExpandSelection(int[] coords){
        gameBoard[coords[0]][coords[1]].setExplored(true);
        gameBoard[coords[0]][coords[1]].setValue(gameBoard[coords[0]][coords[1]].getAdjacentBombs());

        if(gameBoard[coords[0]][coords[1]].getValue() == '0'){
            //recursive calls with try/catch through all 8 adjacent cell possibilities
            //upper right
            try {
                gameBoard[coords[0] - 1][coords[1] - 1].setExplored(true);
                gameBoard[coords[0] - 1][coords[1] - 1].setValue(gameBoard[coords[0] - 1][coords[1] - 1].getAdjacentBombs());
            }catch(ArrayIndexOutOfBoundsException e){
                //this is not a valid element so do nothing
            }
            try{
                if(gameBoard[coords[0] - 1][coords[1] - 1].getValue() == '0') {
                    int[] tempCoords1 = new int[2];
                    tempCoords1[0] = coords[0] - 1;
                    tempCoords1[1] = coords[1] - 1;
                    autoExpandSelection(tempCoords1);
                }
            }catch(ArrayIndexOutOfBoundsException e){
                //this is not a valid element so do nothing
            }
            //upper middle
            try{
                gameBoard[coords[0] - 1][coords[1]].setExplored(true);
                gameBoard[coords[0] - 1][coords[1]].setValue(gameBoard[coords[0] - 1][coords[1]].getAdjacentBombs());
            }catch(ArrayIndexOutOfBoundsException e){
                //this is not a valid element so do nothing
            }
            try{
                if(gameBoard[coords[0] - 1][coords[1]].getValue() == '0') {
                    int[] tempCoords2 = new int[2];
                    tempCoords2[0] = coords[0] - 1;
                    tempCoords2[1] = coords[1];
                    autoExpandSelection(tempCoords2);
                }
            }catch(ArrayIndexOutOfBoundsException e){
                //this is not a valid element so do nothing
            }
            //upper right
            try{
                gameBoard[coords[0] - 1][coords[1] + 1].setExplored(true);
                gameBoard[coords[0] - 1][coords[1] + 1].setValue(gameBoard[coords[0] - 1][coords[1] + 1].getAdjacentBombs());
            }catch(ArrayIndexOutOfBoundsException e){
                //this is not a valid element so do nothing
            }
            try{
                if(gameBoard[coords[0] - 1][coords[1] + 1].getValue() == '0') {
                    int[] tempCoords3 = new int[2];
                    tempCoords3[0] = coords[0] - 1;
                    tempCoords3[1] = coords[1] + 1;
                    autoExpandSelection(tempCoords3);
                }
            }catch(ArrayIndexOutOfBoundsException e){
                //this is not a valid element so do nothing
            }
            //left
            try{
                gameBoard[coords[0]][coords[1] - 1].setExplored(true);
                gameBoard[coords[0]][coords[1] - 1].setValue(gameBoard[coords[0]][coords[1] - 1].getAdjacentBombs());
            }catch(ArrayIndexOutOfBoundsException e){
                //this is not a valid element so do nothing
            }
            try{
                if(gameBoard[coords[1]][coords[0] - 1].getValue() == '0') {
                    int[] tempCoords4 = new int[2];
                    tempCoords4[0] = coords[0];
                    tempCoords4[1] = coords[1] - 1;
                    autoExpandSelection(tempCoords4);
                }
            }catch(ArrayIndexOutOfBoundsException e){
                //this is not a valid element so do nothing
            }
            //right **************************************************************
            //causing overflow******************************************************************
            try{
                gameBoard[coords[0]][coords[1] + 1].setExplored(true);
                gameBoard[coords[0]][coords[1] + 1].setValue(gameBoard[coords[0]][coords[1] + 1].getAdjacentBombs());
            }catch(ArrayIndexOutOfBoundsException e){
                //this is not a valid element so do nothing
            }
//            try{
//                if(gameBoard[coords[0]][coords[1] + 1].getValue() == '0') {
//                    int[] tempCoords5 = new int[2];
//                    tempCoords5[0] = coords[0];
//                    tempCoords5[1] = coords[1] + 1;
//                    autoExpandSelection(tempCoords5);
//                }
//            }catch(ArrayIndexOutOfBoundsException e){
//                //this is not a valid element so do nothing
//            }
            //lower left
            try {
                gameBoard[coords[0] + 1][coords[1] - 1].setExplored(true);
                gameBoard[coords[0] + 1][coords[1] - 1].setValue(gameBoard[coords[0] + 1][coords[1] - 1].getAdjacentBombs());
            }catch(ArrayIndexOutOfBoundsException e){
                //this is not a valid element so do nothing
            }
//            try{
//                if(gameBoard[coords[0] + 1][coords[1] - 1].getValue() == '0') {
//                    int[] tempCoords6 = new int[2];
//                    tempCoords6[0] = coords[0] + 1;
//                    tempCoords6[1] = coords[1] - 1;
//                    autoExpandSelection(tempCoords6);
//                }
//            }catch(ArrayIndexOutOfBoundsException e){
//                //this is not a valid element so do nothing
//            }
            //lower middle
            try{
                gameBoard[coords[0] + 1][coords[1]].setExplored(true);
                gameBoard[coords[0] + 1][coords[1]].setValue(gameBoard[coords[0] + 1][coords[1]].getAdjacentBombs());
            }catch(ArrayIndexOutOfBoundsException e){
                //this is not a valid element so do nothing
            }
//            try{
//                if(gameBoard[coords[0] + 1][coords[1]].getValue() == '0') {
//                    int[] tempCoords7 = new int[2];
//                    tempCoords7[0] = coords[0] + 1;
//                    tempCoords7[1] = coords[1];
//                    autoExpandSelection(tempCoords7);
//                }
//            }catch(ArrayIndexOutOfBoundsException e){
//                //this is not a valid element so do nothing
//            }
            //lower right
            try{
                gameBoard[coords[0] + 1][coords[1] + 1].setExplored(true);
                gameBoard[coords[0] + 1][coords[1] + 1].setValue(gameBoard[coords[0] + 1][coords[1] + 1].getAdjacentBombs());
            }catch(ArrayIndexOutOfBoundsException e){
                //this is not a valid element so do nothing
            }
//            try{
//                if(gameBoard[coords[0] + 1][coords[1] + 1].getValue() == '0') {
//                    int[] tempCoords8 = new int[2];
//                    tempCoords8[0] = coords[0] + 1;
//                    tempCoords8[1] = coords[1] + 1;
//                    autoExpandSelection(tempCoords8);
//                }
//            }catch(ArrayIndexOutOfBoundsException e){
//                //this is not a valid element so do nothing
//            }
        }
    }

    /******************************************************************************

     ******************************************************************************/
    public void userFlagMove(int[] coords){
        if(gameBoard[coords[0]][coords[1]].isFlagged()){
            gameBoard[coords[0]][coords[1]].setValue('-');
            gameBoard[coords[0]][coords[1]].setFlagged(false);

            if(gameBoard[coords[0]][coords[1]].isBomb()){
                setBombs--;
            }
        }
        else {
            gameBoard[coords[0]][coords[1]].setValue('F');
            gameBoard[coords[0]][coords[1]].setFlagged(true);

            if(gameBoard[coords[0]][coords[1]].isBomb()){
                setBombs++;
            }
        }
        printGameBoard();
    }


    /******************************************************************************

     ******************************************************************************/
    public void assignBombs(int[] coords){
        //randomly assign bombs
        for (int i = 0; i < numBombs; i++) {
            boolean validCell = false;
            do {
                int randRow = (int) (Math.random() * ((max - min) + 1));
                int randCol = (int) (Math.random() * ((max - min) + 1));
                if (!gameBoard[randRow][randCol].isBomb() && randRow != coords[0] && randCol != coords[1]) {
//                    gameBoard[randRow][randCol].setValue('*'); //testing comment for random bomb placement
                    gameBoard[randRow][randCol].setBomb(true);
                    validCell = true;
                    System.out.println("bomb coordinates: " + randRow + " " + randCol);
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
                    if (gameBoard[i - 1][j].isBomb()) {
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

                //non-boarder cells (in the middle of the grid)
                if(i != 0 && i != 9 && j != 0 && j != 9){
                    if(gameBoard[i - 1][j - 1].isBomb()){
                    bombCount++;
                    }
                    if(gameBoard[i - 1][j].isBomb()){
                        bombCount++;
                    }
                    if(gameBoard[i - 1][j + 1].isBomb()){
                        bombCount++;
                    }
                    if(gameBoard[i][j + 1].isBomb()){
                        bombCount++;
                    }
                    if(gameBoard[i + 1][j + 1].isBomb()){
                        bombCount++;
                    }
                    if(gameBoard[i + 1][j].isBomb()){
                        bombCount++;
                    }
                    if(gameBoard[i + 1][j - 1].isBomb()){
                        bombCount++;
                    }
                    if(gameBoard[i][j - 1].isBomb()){
                        bombCount++;
                    }
                }

                adjacentBombs = (char)(bombCount + '0');
                gameBoard[i][j].setAdjacentBombs(adjacentBombs);
//                System.out.println("bomb count for " + i + " " + j + " " + gameBoard[i][j].getAdjacentBombs());
            }
        }
    }


       /******************************************************************************

     ******************************************************************************/
    public void printGameBoard(){
        System.out.print("    ");
        for(int r = 0; r < col; r++){
            System.out.print(r + "  ");
        }
        System.out.println();
        System.out.println("   -----------------------------");
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){

                if(j == 0){
                    System.out.print(i + " | " + gameBoard[i][j].getValue() + "  ");
                }
                else{
                    System.out.print(gameBoard[i][j].getValue() + "  ");
                }
            }
            System.out.print("\n");
        }
    }

    public int getNumBombs() {
        return numBombs;
    }

    public int getSetBombs() {
        return setBombs;
    }


}
