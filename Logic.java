
package com.mycompany.checkers;

public class Logic {
    private char[][] logicBoard;
    private Board board;

    public Logic() {
        this.logicBoard = new char[8][8];
        this.board = new Board();
    }   
    
    public int[] convertCommandToCoordinates(String command){
        
        
        //coordinates format example; a3-d4
        
        String[] commands = command.split("-");
        
        String[] cmd1 = commands[0].split("");
        String[] cmd2 = commands[1].split("");


        int[] coordinates = new int[4];
        
        coordinates[1] = letterToNumber(cmd1[0])-1;
        coordinates[0] = Integer.valueOf(cmd1[1])-1;
        coordinates[3] = letterToNumber(cmd2[0])-1;
        coordinates[2] = Integer.valueOf(cmd2[1])-1;
        
        
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
    
    public char[][] move(String command){
        
        int[] coordinates = convertCommandToCoordinates(command);
        logicBoard = board.getBoard();
        
        if(isMoveValid(coordinates)){
        // test
        System.out.println("Coodinates: ");
        System.out.println(coordinates[0] + ", " + coordinates[1]);
        System.out.println(coordinates[2] + ", " + coordinates[3]);
        // 
        
        
        char piece = logicBoard[coordinates[0]][coordinates[1]];
        logicBoard[coordinates[0]][coordinates[1]] = ' ';
        logicBoard[coordinates[2]][coordinates[3]] = piece;
        
        board.printBoard();
        
        
        
        } else System.out.println("Move invalid");
        
        return logicBoard;
    }
    
    public boolean isMoveValid(int[] coordinates){
        // abs rożnica między coor 0 i coor 2 == 1 & coor 1 i coor 3 == 1
        logicBoard = board.getBoard();
        
        return (Math.abs(coordinates[0] - coordinates[2]) == 1) && (Math.abs(coordinates[1] - coordinates[3]) == 1)
                && logicBoard[coordinates[2]][coordinates[3]] == ' ';
    }
    
}
