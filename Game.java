
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
            
            if(logic.onePieceOnBoard()) break;
            
            System.out.print("> ");
            String command = scanner.next();
            command = command.toLowerCase();
            if(command.equals("exit")) break;
            
            commands(command);
            
        }
        
        
        char winner = 'X';
        if(logic.getPoints() > 0) winner = 'O';
        System.out.println(winner + " won!");
        
        System.out.println("Do you want to play again? (y/n)");
        String command = scanner.next();
        if(command.equals("y")) newGame();
        
    }
    
    public void newGame(){
        board.initializeBoard();
        logic.changePlayer();
        start();
    }
    
    
    
    
    public void commands(String command){
        
        switch(command){
            case "help":{
                System.out.println("Commands: \n"
                                 + "help - shows all commands \n"
                                 + "print - prints the board \n"
                                 + "rules - shows rules of the game \n"
                                 + "exit - ends the game \n"
                                 + "save - saves the game \n"
                                 + "load - loads the game \n");
                break;
            }
            case "rules":{
                System.out.println("- White pieces are represented by \"O\"\n" +
                                   "- Black pieces are represented by \"X\"\n" +
                                   "- Regular pieces moves diagonally by one field forward\n" +
                                   "- Capturing is mandatory\n" +
                                   "- Pieces who reach end of the board are promoted to kings\n" +
                                   "- White kings are represented by \"A\"\n" +
                                   "- Black kings are represented by \"B\"\n" +
                                   "- Kings can move diagonally by more than one field forward amd backward");
                break;
            }
            case "print":{
                board.printBoard();
                break;
            }
            case "empty":{
                board.printEmptyBoard(); //for tests
                break;
            }
            case "save":{
                System.out.print("Enter name for save: ");
                String name = scanner.next();
                logic.saveGame(name);
                break;
            }
            case "load":{
                System.out.print("Enter name of the saved game: ");
                String name = scanner.next();
                logic.loadGame(name);
                break;
            }
            default: {
                board.setBoard(logic.move(command));
                
                if(board.getBoard()[0][8] == 't') logic.changePlayer();
                
                System.out.println("Move of player: " + logic.getPlayer());
            }
        }
        
        
    }
}
