
package com.mycompany.checkers;


public class Pieces {
    private char black = 'X';
    private char blackKing = 'B';
    private char white = 'O';
    private char whiteKing = 'A';
    private char player = 'O';

    public char getBlack() {
        return black;
    }

    public char getWhite() {
        return white;
    }
    
    public void changePlayer(){
        if(player == 'X') player = 'O';
        else player = 'X';
    }
    
    public char getPlayer(){
        return player;
    }
    
}
