
package com.mycompany.checkers;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.nio.file.Paths;
import java.util.Scanner;

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
    
    public char getPlayerKing(){
        return player.getPlayerKing();
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
        char piece = logicBoard[coordinates[0]][coordinates[1]];
        logicBoard = board.getBoard();
        

        logicBoard[0][8] = 'f';
        
        if(piece == player.getBlackKing() || piece == player.getWhiteKing()){
            if(isKingMoveValid(coordinates) == 1) normalMove(coordinates);
            else if(isKingMoveValid(coordinates) == 2) capturingMoveForKings(coordinates);
            else if(isKingMoveValid(coordinates) == 0) System.out.println("Move invalid");
        }
        
        if(piece == player.getBlack() || piece == player.getWhite()){
            if(isMoveValid(coordinates) == 1){
                normalMove(coordinates);
            } else if(isMoveValid(coordinates) == 2){
                capturingMove(coordinates);
            } else if(isMoveValid(coordinates) == 0){
                System.out.println("Move invalid");
            }
        }
        
        return logicBoard;
    }
    
    public void normalMove(int[] coordinates){
        if(checkMandatoryCapture()){
            System.out.println("Capturing is mandatory");
        } else{
            char piece = logicBoard[coordinates[0]][coordinates[1]];
            logicBoard[coordinates[0]][coordinates[1]] = ' ';
            logicBoard[coordinates[2]][coordinates[3]] = piece;
            
            promoteToKing();
            board.printBoard();
            
            logicBoard[0][8] = 't';
            }
    }
    
    public void capturingMove(int[] coordinates){
        
            if(player.getEnemy() == player.getWhite()) points--;
            else points++;
            
            
            logicBoard[(coordinates[0]+coordinates[2])/2][(coordinates[1]+coordinates[3])/2] = ' '; //enemy coordinate
            
            char piece = logicBoard[coordinates[0]][coordinates[1]];
            logicBoard[coordinates[0]][coordinates[1]] = ' ';
            logicBoard[coordinates[2]][coordinates[3]] = piece;
            
            promoteToKing();
            board.printBoard();
            if(!(checkMandatoryCapture())) logicBoard[0][8] = 't';
    }
    
    
    public int isMoveValid(int[] coordinates){
        
        int[] enemyCoor = new int[2];
        enemyCoor[0] = (coordinates[0]+coordinates[2])/2;
        enemyCoor[1] = (coordinates[1]+coordinates[3])/2;
        
        
        logicBoard = board.getBoard();
        
        if((Math.abs(coordinates[0] - coordinates[2]) == 1) && (Math.abs(coordinates[1] - coordinates[3]) == 1) && (logicBoard[coordinates[2]][coordinates[3]] == ' ') 
            && logicBoard[coordinates[0]][coordinates[1]] == player.getPlayer()) return 1;
        
        if(((Math.abs(coordinates[0] - coordinates[2]) == 2) && (Math.abs(coordinates[1] - coordinates[3]) == 2) && (logicBoard[enemyCoor[0]][enemyCoor[1]] == player.getEnemy())
            && logicBoard[coordinates[0]][coordinates[1]] == player.getPlayer()) ||
                ((Math.abs(coordinates[0] - coordinates[2]) == 2) && (Math.abs(coordinates[1] - coordinates[3]) == 2) && (logicBoard[enemyCoor[0]][enemyCoor[1]] == player.getEnemyKing())
            && logicBoard[coordinates[0]][coordinates[1]] == player.getPlayer())) return 2; //statement for capturing
        
        return 0;
    }
    
    public boolean checkMandatoryCapture(){
        logicBoard = board.getBoard();
        
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                try{
                    if((logicBoard[i][j] == player.getBlack() && logicBoard[i+1][j+1] == player.getWhite() && logicBoard[i+2][j+2] == ' ') ||
                       (logicBoard[i][j] == player.getBlack() && logicBoard[i-1][j-1] == player.getWhite() && logicBoard[i-2][j-2] == ' ') ||
                       (logicBoard[i][j] == player.getBlack() && logicBoard[i+1][j-1] == player.getWhite() && logicBoard[i+2][j-2] == ' ') ||
                       (logicBoard[i][j] == player.getBlack() && logicBoard[i-1][j+1] == player.getWhite() && logicBoard[i-2][j+2] == ' ')
                            
                   || ((logicBoard[i][j] == player.getWhite() && logicBoard[i+1][j+1] == player.getBlack() && logicBoard[i+2][j+2] == ' ') ||
                       (logicBoard[i][j] == player.getWhite() && logicBoard[i-1][j-1] == player.getBlack() && logicBoard[i-2][j-2] == ' ') ||
                       (logicBoard[i][j] == player.getWhite() && logicBoard[i+1][j-1] == player.getBlack() && logicBoard[i+2][j-2] == ' ') ||
                       (logicBoard[i][j] == player.getWhite() && logicBoard[i-1][j+1] == player.getBlack() && logicBoard[i-2][j+2] == ' '))
                            
                            
                   || ((logicBoard[i][j] == player.getBlack() && logicBoard[i+1][j+1] == player.getWhiteKing() && logicBoard[i+2][j+2] == ' ') ||
                       (logicBoard[i][j] == player.getBlack() && logicBoard[i-1][j-1] == player.getWhiteKing() && logicBoard[i-2][j-2] == ' ') ||
                       (logicBoard[i][j] == player.getBlack() && logicBoard[i+1][j-1] == player.getWhiteKing() && logicBoard[i+2][j-2] == ' ') ||
                       (logicBoard[i][j] == player.getBlack() && logicBoard[i-1][j+1] == player.getWhiteKing() && logicBoard[i-2][j+2] == ' '))
                            
                   || ((logicBoard[i][j] == player.getWhite() && logicBoard[i+1][j+1] == player.getBlackKing() && logicBoard[i+2][j+2] == ' ') ||
                       (logicBoard[i][j] == player.getWhite() && logicBoard[i-1][j-1] == player.getBlackKing() && logicBoard[i-2][j-2] == ' ') ||
                       (logicBoard[i][j] == player.getWhite() && logicBoard[i+1][j-1] == player.getBlackKing() && logicBoard[i+2][j-2] == ' ') ||
                       (logicBoard[i][j] == player.getWhite() && logicBoard[i-1][j+1] == player.getBlackKing() && logicBoard[i-2][j+2] == ' '))) return true;
                    
                }catch(ArrayIndexOutOfBoundsException e){
                    //nothing
                }   
            }
        }
        
        
        return false;
    }
    
    public void promoteToKing(){
        
        for(int i = 0; i < 8; i++){
            if(logicBoard[0][i] == player.getBlack()) logicBoard[0][i] = player.getBlackKing();
        }
        
        for(int i = 0; i < 8; i++){
            if(logicBoard[7][i] == player.getWhite()) logicBoard[7][i] = player.getWhiteKing();
        }
    }
    
    public int isKingMoveValid(int[] coordinates){
        logicBoard = board.getBoard();
        int statement = 0;
        
        //[+-][  ][++]
        //[  ][X ][  ]
        //[--][  ][-+]
        
        if(coordinates[2] > coordinates[0] && coordinates[3] > coordinates[1]){
//            System.out.println("right-up");
            int j = coordinates[1]+1;
            for(int i = coordinates[0]+1; i <= coordinates[2]; i++){
//                System.out.println(i + "" + j);
                if(logicBoard[i][j] != ' '){
                    statement = 0;
                    break;
                }
                j++;
                statement = 1;
                try{
                    if((logicBoard[i+1][j+1] == player.getEnemy() && logicBoard[i+2][j+2] == ' ') || (logicBoard[i+1][j+1] == player.getEnemyKing() && logicBoard[i+2][j+2] == ' ')) statement = 2;
                }catch(ArrayIndexOutOfBoundsException e){
                    //nothing
                }
            }
        }
        
        if(coordinates[2] > coordinates[0] && coordinates[3] < coordinates[1]){
//            System.out.println("left-up");
            int j = coordinates[1]-1;
            for(int i = coordinates[0]+1; i <= coordinates[2]; i++){
//                System.out.println(i + "" + j);
                if(logicBoard[i][j] != ' '){
                    statement = 0;
                    break;
                }
                j--;
                statement = 1;
            }
        }
        
        if(coordinates[2] < coordinates[0] && coordinates[3] < coordinates[1]){
//            System.out.println("left-down");
            int j = coordinates[1]-1;
            for(int i = coordinates[0]-1; i >= coordinates[2]; i--){
//                System.out.println(i + "" + j);
                if(logicBoard[i][j] != ' '){
                    statement = 0;
                    break;
                }
                j--;
                statement = 1;
            }
        }
        
        if(coordinates[2] < coordinates[0] && coordinates[3] > coordinates[1]){
//            System.out.println("right-down");
            int j = coordinates[1]+1;
            for(int i = coordinates[0]-1; i >= coordinates[2]; i--){
//                System.out.println(i + "" + j);
                if(logicBoard[i][j] != ' '){
                    statement = 0;
                    break;
                }
                j++;
                statement = 1;
            }
        }
        
        return statement;
    }
    
    
    
    
    public void capturingMoveForKings(int[] coordinates){
        System.out.println("Chwilowo brak metody");
    }
    
    
    
    
    public boolean onePieceOnBoard(){
        int counter = 0;
        logicBoard = board.getBoard();
        
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(logicBoard[i][j] != ' ') counter++;
                if(counter > 1) break;
            }
            if(counter > 1) break;
        }
        
        return counter == 1;
    }
    
    public void saveGame(String name){
        name = name + ".txt";
        try{
            File save = new File(name);
            if (save.createNewFile()) {
                try {
                    FileWriter saver = new FileWriter(name);
                    
                    logicBoard = board.getBoard();
                    
                    for(int i = 0; i < 8; i++){
                        for(int j = 0; j < 8; j++){
                            saver.write(logicBoard[i][j] + "\n");
                        }
                    }
                    
                    saver.close();
                } catch (IOException e) {
                    System.out.println("An error occurred");
                    e.printStackTrace();
                }
                
                System.out.println("Game saved in: " + save.getName());
            } else System.out.println("Save already exists");
            
        }catch(IOException e){
            System.out.println("An error occurred");
            e.printStackTrace();
        }
    }
    
    public void loadGame(String name){
        name = name + ".txt";
        try(Scanner scan  = new Scanner(Paths.get(name))){
            logicBoard = board.getBoard();
            
            for(int i = 0; i < 8; i++){
                for(int j = 0; j < 8; j++){
                    if(scan.hasNextLine()){
                    String row = scan.nextLine();
                    logicBoard[i][j] =  row.charAt(0);
                    }
                }
            }
            
            System.out.println("Game loaded");
            board.printBoard();
            
        }catch(Exception e){
            System.out.println("An error occured");
            e.printStackTrace();
        }
    }
    
    
}