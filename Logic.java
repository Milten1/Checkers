
package com.mycompany.checkers;

public class Logic {
    private char[][] logicBoard;
    private Board board;
    private Pieces player;
    private int points; // if white get point, +1. If black get point -1

    public Logic() {
        this.logicBoard = new char[8][9];
        
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
    
    public char getPlayer(){
        return player.getPlayer();
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
        logicBoard[0][8] = 'f';
        
        if(isMoveValid(coordinates) == 1){
            
            if(checkMandatoryCapture()){
                System.out.println("Capturing is mandatory");
            } else{
                char piece = logicBoard[coordinates[0]][coordinates[1]];
                logicBoard[coordinates[0]][coordinates[1]] = ' ';
                logicBoard[coordinates[2]][coordinates[3]] = piece;
                
                board.printBoard();
                logicBoard[0][8] = 't';
            }
            
            
        
        
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
            logicBoard[0][8] = 't';

        } else if(isMoveValid(coordinates) == 0) System.out.println("Move invalid");

        
        return logicBoard;
    }
    
    public int isMoveValid(int[] coordinates){
        
        char enemy = 'O';
        if(player.getPlayer() == 'O') enemy = 'X';
        
        int[] enemyCoor = new int[2];
        enemyCoor[0] = coordinates[0]+1;
        enemyCoor[1] = coordinates[1]+1;
        
        
        logicBoard = board.getBoard();
        
        if((Math.abs(coordinates[0] - coordinates[2]) == 1) && (Math.abs(coordinates[1] - coordinates[3]) == 1) && (logicBoard[coordinates[2]][coordinates[3]] == ' ') 
            && logicBoard[coordinates[0]][coordinates[1]] == player.getPlayer()) return 1;
        
        if((Math.abs(coordinates[0] - coordinates[2]) == 2) && (Math.abs(coordinates[1] - coordinates[3]) == 2) && (logicBoard[enemyCoor[0]][enemyCoor[1]] == enemy)  
            && logicBoard[coordinates[0]][coordinates[1]] == player.getPlayer()) return 2; //statement dla bicia
        
        return 0;
    }
    
    public boolean checkMandatoryCapture(){
        logicBoard = board.getBoard();
        
        char enemy = 'O';
        if(player.getPlayer() == 'O') enemy = 'X';
        
        // sprawdza tablicę szukając pionków, a jak znajdzie, to sprawdza czy w rogach jest przeciwnik. Jeśli jest to sprawdza czy można go zbić
        
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(logicBoard[i][j] == 'X' || logicBoard[i][j] == 'O'){
                    if((logicBoard[i+1][j+1] == enemy && logicBoard[i+2][j+2] == ' ') ||
                       (logicBoard[i-1][j-1] == enemy && logicBoard[i-2][j-2] == ' ') ||
                       (logicBoard[i+1][j-1] == enemy && logicBoard[i+2][j-2] == ' ') ||
                       (logicBoard[i-1][j+1] == enemy && logicBoard[i-2][j+2] == ' ')) return true;
                    
                    // index out of bounds. use try-catch
                    
                }
            }
        }
        
        return false;
    }    
    
}