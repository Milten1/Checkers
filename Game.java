
package com.mycompany.checkers;
import java.util.Scanner;


public class Game {
    private Board board;
    private Logic logic;
    private Scanner scanner;
    private Pieces player;

    public Game() {
        this.board = new Board();
        this.logic = new Logic();
        this.player = new Pieces();
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
        System.out.println(player.getPlayer() + " won!");
        
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
            default: {
                board.setBoard(logic.move(command));
                logic.changePlayer();
            }
        }
        
        
    }
}
