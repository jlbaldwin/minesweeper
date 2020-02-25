package com.jon;

import java.util.Scanner;

public class Minesweeper {

    static Scanner scanner = new Scanner(System.in);
    private static int coords[];


    public static void main(String[] args) {
        boolean playerAlive = true;
        int move;   //move selection from user to explore cell or mark as flag

        //initialize empty gameBoard and display
        Board gameBoard = new Board();
        gameBoard.printGameBoard();

        while(playerAlive){
            move = getMove();
            //prompt user for move or flag coordinates
            if(move == 1) {
                System.out.println("Enter MOVE coordinates y x:\r");
            }
            else{
                System.out.println("Enter FLAG coordinates (re-entering flagged cell will unflag) y x:\r");
            }
            coords = getCoordinates();

            //use input coordinates to explore or flag cell
            if(move == 1) {
                playerAlive = gameBoard.userMove(coords);
            }
            else{
                gameBoard.userFlagMove(coords);
            }

            //if all bombs have been successfully flagged, end game and print winning message
            if(gameBoard.getSetBombs() == gameBoard.getNumBombs()){
                System.out.println("You've set all the bombs, congratulations!");
                playerAlive = false;
            }
        }
        scanner.close();
    }


    private static int getMove(){
        int selection;
        System.out.println("Select option:");
        System.out.println("1. Enter move.");
        System.out.println("2. Flag a cell.");
        selection = scanner.nextInt();
        return selection;
    }


    //get player move coordinates
    private static int[] getCoordinates(){
        int[] coords = new int[2];

        coords[0] = scanner.nextInt();
        coords[1] = scanner.nextInt();

        return coords;
    }
}
