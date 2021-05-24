
package com.mycompany.checkers;
import java.util.Scanner;


public class Game {
    private Board board;
    private Logic logic;
    private Scanner scanner;

    public Game() {
        this.board = new Board();
        this.logic = new Logic();
        this.scanner = new Scanner(System.in);
    }

    
    
    
             
    public void start(){
        
        board.printBoard();
        
        while(true){
            
            System.out.print("> ");
            String command = scanner.next();
            if(command.equals("exit")) break;
            
            commands(command);
            
        }
        
        
        //end of the game
        System.out.println(logic.getPlayer() + " won!");
        
    }
    
    public void commands(String command){
        
        switch(command){
            case "help":{
                System.out.println("Commands: \n"
                                 + "help - shows all commands \n"
                                 + "print - prints the board \n"
                                 + "rules - shows rules of the game \n"
                                 + "exit - ends the game");
                break;
            }
            case "rules":{
                System.out.println("(there will be printed rules of the game)");
                break;
            }
            case "print":{
                board.printBoard();
                break;
            }
            case "empty":{
                board.printEmptyBoard(); //for tests
            }
            default: {
                board.setBoard(logic.move(command));
                
                if(board.getBoard()[0][8] == 't') logic.changePlayer();
                
                System.out.println("Move of player: " + logic.getPlayer());
            }
        }
        
        
    }
}
