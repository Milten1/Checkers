
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
            default: logic.move(command);
        }
        
        
    }
}
