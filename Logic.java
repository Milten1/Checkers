
package com.mycompany.checkers;

public class Logic {
    private char[][] logicBoard;
    private Board board;

    public Logic() {
        this.logicBoard = new char[8][8];
        this.board = new Board();
    }   
    
    public int[] convertCommandToCoordinates(String command){
        
        
        //coordinates format example; c3-d4
        
        String[] commands = command.split("-");
        
        String[] cmd1 = commands[0].split("");
        String[] cmd2 = commands[1].split("");


        int[] coordinates = new int[4];
        
        coordinates[0] = letterToNumber(cmd1[0]);
        coordinates[1] = Integer.valueOf(cmd1[1]);
        coordinates[2] = letterToNumber(cmd2[0]);
        coordinates[3] = Integer.valueOf(cmd2[1]);
        
        
        return coordinates;
    }
    
    public int letterToNumber(String letter){
        switch(letter){
            case "a": return 1;
            case "b": return 2;
            case "c": return 3;
            case "d": return 4;
            case "e": return 5;
            case "f": return 6;
            case "g": return 7;
            case "h": return 8;
        }
        return 0;
    }
    
    public void move(String command){
        int[] coordinates = convertCommandToCoordinates(command);
        
        // test
        System.out.println("Coodinates: ");
        System.out.println(coordinates[0] + ", " + coordinates[1]);
        System.out.println(coordinates[2] + ", " + coordinates[3]);
        //
        
        
        logicBoard = board.getBoard();
        char piece = logicBoard[coordinates[0]][coordinates[1]];
        logicBoard[coordinates[0]][coordinates[1]] = ' ';
        logicBoard[coordinates[2]][coordinates[3]] = piece;
        
        board.setBoard(logicBoard);
        board.printBoard();
        
    }
    
    public boolean isMoveValid(int[] coordinates){
        // if coor[0] == coor[3] and coor[1] == coor[2] (tylko w przypadku bicia)
        return false;
    }
    
}
