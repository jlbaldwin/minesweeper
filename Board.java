package com.jon;

class Board {

    private int row = 10;
    private int col = 10;
    private int numBombs = 2;       //number of bombs to set on board
    private int flaggedBombs = 0;   //number of bombs correctly flagged by user
    private int max = row - 1;
    private int min = 0;
    private Cell[][] gameBoard = new Cell[row][col];
    private boolean firstCoords = true; //flag used in users first move to trigger bomb allocation

    /******************************************************************************
     Initialize board with all cells having value '-' and adjacentBombs '0'
     ******************************************************************************/
    Board() {
        //create board
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                gameBoard[i][j] = new Cell();
            }
        }
    }

    /******************************************************************************
     boolean userMove(int[] coords)
     Input:
        move coordinates [row, col]
     Returns:
        boolean. true = user still alive, false = user selected bomb
     Description:
         For initial move, the coordinate is marked explored, then bombs
         are assigned and adjacent bomb count calculated. If the first move had 0
         adjacent bombs, the surrounding cells are recursively auto expanded to show bomb
         count.

         Subsequent moves are tested to determine if cell is a bomb (game over), if cell
         has 0 adjacent bombs the selection is recursively auto expanded to show bomb count,
         if cell had an adjacent bomb the bomb count is displayed (no recursive auto expand).
     ******************************************************************************/
    boolean userMove(int[] coords){
        //first move, bombs and adjacent bomb count assigned to board cells
        if(firstCoords){
            gameBoard[coords[0]][coords[1]].setExplored(true);
            assignBombs(coords);
            firstCoords = false;
            gameBoard[coords[0]][coords[1]].setValue(gameBoard[coords[0]][coords[1]].getAdjacentBombs());

            if(gameBoard[coords[0]][coords[1]].getValue() == '0') {
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
        //selection has 0 adjacent bombs, recursively auto expand
        else if(gameBoard[coords[0]][coords[1]].getAdjacentBombs() == '0'){
            autoExpandSelection(coords);
            printGameBoard();
            return true;
        }
        //selection had one or more adjacent bombs, display adjacent bombs only
        else {
            gameBoard[coords[0]][coords[1]].setExplored(true);
            gameBoard[coords[0]][coords[1]].setValue(gameBoard[coords[0]][coords[1]].getAdjacentBombs());
            printGameBoard();
            return true;
        }
    }


    /******************************************************************************
     private void autoExpandSelection(int[] coords)
     Input:
        move coordinates [row, col]
     Description:
        If a cell has 0 adjacent bombs the user's selection should be auto expanded
        to display surrounding cell adjacentBomb counts. For each adjacent cell that
        has a 0 adjacentBomb count, recursively expand in all 8 directions.
     ******************************************************************************/
    private void autoExpandSelection(int[] coords){
        //set the initial selection to explored and update value to show adjacentBomb count
        gameBoard[coords[0]][coords[1]].setExplored(true);
        gameBoard[coords[0]][coords[1]].setValue(gameBoard[coords[0]][coords[1]].getAdjacentBombs());

        if(gameBoard[coords[0]][coords[1]].getValue() == '0'){
            //recursive calls with try/catch through all 8 adjacent cell possibilities
            //upper left
            try {
                if(!gameBoard[coords[0] - 1][coords[1] - 1].isExplored()) {
                    gameBoard[coords[0] - 1][coords[1] - 1].setExplored(true);
                    gameBoard[coords[0] - 1][coords[1] - 1].setValue(gameBoard[coords[0] - 1][coords[1] - 1].getAdjacentBombs());
                }
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
                if(!gameBoard[coords[0] - 1][coords[1]].isExplored()) {
                    gameBoard[coords[0] - 1][coords[1]].setExplored(true);
                    gameBoard[coords[0] - 1][coords[1]].setValue(gameBoard[coords[0] - 1][coords[1]].getAdjacentBombs());
                }
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
                if(!gameBoard[coords[0] - 1][coords[1] + 1].isExplored()) {
                    gameBoard[coords[0] - 1][coords[1] + 1].setExplored(true);
                    gameBoard[coords[0] - 1][coords[1] + 1].setValue(gameBoard[coords[0] - 1][coords[1] + 1].getAdjacentBombs());
                }
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
                if(!gameBoard[coords[0]][coords[1] - 1].isExplored()) {
                    gameBoard[coords[0]][coords[1] - 1].setExplored(true);
                    gameBoard[coords[0]][coords[1] - 1].setValue(gameBoard[coords[0]][coords[1] - 1].getAdjacentBombs());
                }
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
            //causing overflow below this point***************************************************************
            try{
                if(!gameBoard[coords[0]][coords[1] + 1].isExplored()) {
                    gameBoard[coords[0]][coords[1] + 1].setExplored(true);
                    gameBoard[coords[0]][coords[1] + 1].setValue(gameBoard[coords[0]][coords[1] + 1].getAdjacentBombs());
                }
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
                if(!gameBoard[coords[0] + 1][coords[1] - 1].isExplored()) {
                    gameBoard[coords[0] + 1][coords[1] - 1].setExplored(true);
                    gameBoard[coords[0] + 1][coords[1] - 1].setValue(gameBoard[coords[0] + 1][coords[1] - 1].getAdjacentBombs());
                }
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
                if(!gameBoard[coords[0] + 1][coords[1]].isExplored()) {
                    gameBoard[coords[0] + 1][coords[1]].setExplored(true);
                    gameBoard[coords[0] + 1][coords[1]].setValue(gameBoard[coords[0] + 1][coords[1]].getAdjacentBombs());
                }
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
                if(!gameBoard[coords[0] + 1][coords[1] + 1].isExplored()) {
                    gameBoard[coords[0] + 1][coords[1] + 1].setExplored(true);
                    gameBoard[coords[0] + 1][coords[1] + 1].setValue(gameBoard[coords[0] + 1][coords[1] + 1].getAdjacentBombs());
                }
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
     void userFlagMove(int[] coords)
     Input:
        move coordinates [row, col]
     Description:
        Processes flag move by user. Toggle isFlagged each time a cell is processed,
        so if a flagged cell is selected a second time it will be unflagged. Update
        flaggedBomb count.
     ******************************************************************************/
    void userFlagMove(int[] coords){
        if(gameBoard[coords[0]][coords[1]].isFlagged()){
            gameBoard[coords[0]][coords[1]].setValue('-');
            gameBoard[coords[0]][coords[1]].setFlagged(false);

            if(gameBoard[coords[0]][coords[1]].isBomb()){
                flaggedBombs--;
            }
        }
        else {
            gameBoard[coords[0]][coords[1]].setValue('F');
            gameBoard[coords[0]][coords[1]].setFlagged(true);

            if(gameBoard[coords[0]][coords[1]].isBomb()){
                flaggedBombs++;
            }
        }
        printGameBoard();
    }


    /******************************************************************************
     private void assignBombs(int[] coords)
     Input:
        move coordinates [row, col]
     Description:
        Randomly assigns bombs and calculates adjacentBomb count for each cell.
     ******************************************************************************/
    private void assignBombs(int[] coords){
        //randomly assign bombs
        for (int i = 0; i < numBombs; i++) {
            boolean validCell = false;
            do {
                int randRow = (int) (Math.random() * ((max - min) + 1));
                int randCol = (int) (Math.random() * ((max - min) + 1));
                if (!gameBoard[randRow][randCol].isBomb() && randRow != coords[0] && randCol != coords[1]) {
                    gameBoard[randRow][randCol].setBomb(true);
                    validCell = true;
//                    System.out.println("bomb coordinates: " + randRow + " " + randCol);
                }
            } while (!validCell);
        }

        //Add logic testing perimeter cells to avoid array index issues
        //count adjacent bombs and increment bombCount
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int bombCount = 0;
                char adjacentBombs;

                //test for top row
                if (i == min) {
                    //upper left corner cell
                    if (j == min) {
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
                    if (j == max) {
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
                    if( j != min && j != max){
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
                if (i == max) {
                    //bottom left corner cell
                    if (j == min) {
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
                    if (j == max) {
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
                    if ( j != min && j != max){
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
                if (j == min && i != min && i != max) {
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
                if (j == max && i != min && i != max) {
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
                if(i != min && i != max && j != min && j != max){
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
                //assign bombCount to each cell's adjacentBomb var
                adjacentBombs = (char)(bombCount + '0');
                gameBoard[i][j].setAdjacentBombs(adjacentBombs);
            }
        }
    }


    /******************************************************************************
     Print game board
     ******************************************************************************/
       void printGameBoard(){
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

    int getNumBombs() {
        return numBombs;
    }

    int getFlaggedBombs() {
        return flaggedBombs;
    }
}
