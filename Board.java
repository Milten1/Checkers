
package com.mycompany.checkers;

public class Board {
    private char[][] board;
    private Pieces piece;
    
    public Board(){
        board = new char[8][8];
        this.piece = new Pieces();
        
        initializeBoard();
    }
    
    public void initializeBoard(){
        
        int whiteCount = 0;
        
        
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(((j%2 == 0 && i%2 == 0) || (j%2 != 0 && i%2 != 0)) && whiteCount < 12){
                    board[i][j] = piece.getWhite();
                    whiteCount++;
                } else if(i >= 5 && ((j%2 == 0 && i%2 == 0) || (j%2 != 0 && i%2 != 0))){
                    board[i][j] = piece.getBlack();
                    
                } else board[i][j] = ' ';
            }
        }
    }
    
    
    public void printBoard(){
        System.out.print("  ");
        for(char ch = 'A'; ch <= 'H'; ch++ ){
            System.out.print(" " + ch + " ");
        }
        
        System.out.println();
         for(int i = 7; i >= 0; i--){
             System.out.print(i+1 + " ");
            for(int j = 0; j < 8; j++){
                System.out.print("[" + board[i][j] + "]");
            }
             System.out.println();
        }
         
    }

    public char[][] getBoard() {
        return board;
    }

    public void setBoard(char[][] board) {
        this.board = board;
    }
    
}