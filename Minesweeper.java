package com.jon;

import java.util.Arrays;
import java.util.Scanner;

public class Minesweeper {

    public static void main(String[] args) {

        boolean playerAlive = true;
        int coords[];

        Board gameBoard = new Board();
        gameBoard.printGameBoard();

        int testCount = 0;
        while(playerAlive){

            coords = getCoordinates();
            playerAlive = gameBoard.userMove(coords);


            //System.out.println("coords were: " + Arrays.toString(coords));
//            testCount++;
//            if(testCount == 3)
//                playerAlive = false;
        }



    }

    //need to add validation
    //get player move coordinates
    private static int[] getCoordinates(){
        Scanner scanner = new Scanner(System.in);
        int coords[] = new int[2];

        System.out.println("Select x cooridnate:\r");
        coords[0] = scanner.nextInt();
        System.out.println("Select y cooridnate:\r");
        coords[1] = scanner.nextInt();

        return coords;
    }
}
