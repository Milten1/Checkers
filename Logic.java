
package com.mycompany.checkers;

public class Logic {
    private char[][] logicBoard;
    private Board board;
    private Pieces player;
    private int points; // if white get point, +1. If black get point -1

    public Logic() {
        this.logicBoard = new char[8][8];
        this.board = new Board();
        this.player = new Pieces();
        this.points = 0;
    }

    public int getPoints() {
        return points;
    }
    
    public void changePlayer(){
        player.changePlayer();
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
        
        if(isMoveValid(coordinates) == 1){
        // test
        System.out.println("Coodinates: ");
        System.out.println(coordinates[0] + ", " + coordinates[1]);
        System.out.println(coordinates[2] + ", " + coordinates[3]);
        // 
        
        
        char piece = logicBoard[coordinates[0]][coordinates[1]];
        logicBoard[coordinates[0]][coordinates[1]] = ' ';
        logicBoard[coordinates[2]][coordinates[3]] = piece;
        
        board.printBoard();
        
        
        
        } else if(isMoveValid(coordinates) == 2){
            
            char enemy = 'O';
            if(player.getPlayer() == 'O') enemy = 'X';
            
            if(enemy == 'O') points--;
            else points++;
            
            
            logicBoard[coordinates[0]+1][coordinates[1]+1] = ' '; //enemy coordinate
            
            char piece = logicBoard[coordinates[0]][coordinates[1]];
            logicBoard[coordinates[0]][coordinates[1]] = ' ';
            logicBoard[coordinates[2]][coordinates[3]] = piece;
            
            board.printBoard();    

        } else if(isMoveValid(coordinates) == 0) System.out.println("Move invalid");

        
        return logicBoard;
    }
    
    public int isMoveValid(int[] coordinates){ //zastosować coś w rodzaju logiki trójwartościowej
        
        char enemy = 'O';
        if(player.getPlayer() == 'O') enemy = 'X';
        
        int[] enemyCoor = new int[2];
        enemyCoor[0] = coordinates[0]+1;
        enemyCoor[1] = coordinates[1]+1;
        
        
        // abs rożnica między coor 0 i coor 2 == 1 & coor 1 i coor 3 == 1 (albo 2 dla bicia)
        logicBoard = board.getBoard();
        
        if((Math.abs(coordinates[0] - coordinates[2]) == 1) && (Math.abs(coordinates[1] - coordinates[3]) == 1) && (logicBoard[coordinates[2]][coordinates[3]] == ' ') 
            && logicBoard[coordinates[0]][coordinates[1]] == player.getPlayer()) return 1;
        
        if((Math.abs(coordinates[0] - coordinates[2]) == 2) && (Math.abs(coordinates[1] - coordinates[3]) == 2) && (logicBoard[enemyCoor[0]][enemyCoor[1]] == enemy)  
            && logicBoard[coordinates[0]][coordinates[1]] == player.getPlayer()) return 2; //statement dla bicia
        
        return 0;
    }
    
}
