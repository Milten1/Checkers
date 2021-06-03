
package com.mycompany.checkers;

public class Board {
    private char[][] board;
    private Pieces piece;
    
    public Board(){
        board = new char[8][9]; // additional field [0][8] for storing true-false statement
        this.piece = new Pieces();
        
        initializeBoard();
        //captureTest();
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
    
    public void printEmptyBoard(){//for tests
        System.out.print("  ");
        for(char ch = 'A'; ch <= 'H'; ch++ ){
            System.out.print(" " + ch + " ");
        }
        
        System.out.println();
         for(int i = 7; i >= 0; i--){
             System.out.print(i+1 + " ");
            for(int j = 0; j < 8; j++){
                System.out.print("[" + i + "" + j + "]");
            }
             System.out.println();
        }
    }
    
    
    
    public void captureTest(){
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                board[i][j] = ' ';
            }
        }

          board[6][0] = 'X';
          board[1][1] = 'A';
        
    }
    
}