
package com.mycompany.checkers;


public class Pieces {
    private final char black = 'X';
    private final char blackKing = 'B';
    private final char white = 'O';
    private final char whiteKing = 'A';
    private char player = 'O';
    private char playerKing = 'A';
    private char enemy = 'X';
    private char enemyKing = 'B';

    public char getBlack() {
        return black;
    }

    public char getWhite() {
        return white;
    }

    public char getBlackKing() {
        return blackKing;
    }

    public char getWhiteKing() {
        return whiteKing;
    }
    
    public void changePlayer(){
        if(player == 'X') player = 'O';
        else player = 'X';
        
        if(playerKing == 'B') playerKing = 'A';
        else playerKing = 'B';
        
        if(player == 'O') enemy = 'X';
        else enemy = 'O';
        
        if(playerKing == 'B') enemyKing = 'A';
        else enemyKing = 'B';
    }
    
    public char getPlayer(){
        return player;
    }
    
    public char getPlayerKing(){
        return playerKing;
    }
    
    public char getEnemy(){
        return enemy;
    }
    
    public char getEnemyKing(){
        return enemyKing;
    }
    
}
