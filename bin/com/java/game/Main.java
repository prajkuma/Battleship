/**
 * 
 */
package com.java.game;

import java.util.Scanner;

/**
 * @author PC01
 *Oct 27, 2022
Main.java
 */
public class Main {

	 public static Scanner reader = new Scanner(System.in);
     
	 /*
	  * Game starts here
	  */
	    public static void main(String[] args)
	    {
	        System.out.println("Welcome to Battleship boardgame!!!");  
	        System.out.println("You are given 5 fleets of D-2,C-3,B-4,A-5 i.e."
	        		+ " D with 2 tanks, C with 3 tanks and so on... ");
	        System.out.println("Lets start..");
	        
	        //User Board setup
	        System.out.println("\nPlace your ships:");
	        Player userPlayer = new Player();
	        setup(userPlayer);
	        
	        System.out.println("PRESS ENTER TO ALLOW COMPUTER TO CONTINUE...");
	        reader.nextLine();
	        reader.nextLine();
	        Player computer = new Player();
	        setupComputer(computer);
	        System.out.println("\nCOMPUTER GRID (FOR TESTING)...");
	        computer.playerGrid.printShips();
	        
	        String result = "";
	        while(true)
	        {
	           
	            System.out.println("\nUSER MAKE GUESS:");
	            result = askForGuess(userPlayer, computer);
	            System.out.println(result);
	           // System.out.println("DEBUG "+computer.playerGrid.hasLost());
	            if (userPlayer.playerGrid.hasLost())
	            {
	                System.out.println("COMP HIT!...USER LOSES..GAME OVER!");
	                break;
	            }
	            else if (computer.playerGrid.hasLost())
	            {
	                System.out.println("HIT!...COMPUTER LOSES..GAME OVER!");
	                break;
	            }
	            
	            System.out.println("\nCOMPUTER's TURN...");
	              
	              
	            compMakeGuess(computer, userPlayer);
	        }
	    }
	    
	    private static void compMakeGuess(Player comp, Player user)
	    {
	        int row = Randomizer.nextInt(0, 9);
	        int col = Randomizer.nextInt(0, 9);
	        
	        // Computer picks a location
	        while (comp.oppGrid.alreadyGuessed(row, col))
	        {
	            row = Randomizer.nextInt(0, 9);
	            col = Randomizer.nextInt(0, 9);
	        }
	        
	        //If user played
	        if (user.playerGrid.hasShip(row, col))
	        {
	            comp.oppGrid.markHit(row, col);
	            user.playerGrid.markHit(row, col);
	            System.out.println("COMPUTER HIT AT " + convertIntToLetter(row) + convertCompColToRegular(col));
	        }
	        else
	        {
	            comp.oppGrid.markMiss(row, col);
	            user.playerGrid.markMiss(row, col);
	            System.out.println("COMPUTER MISSED AT " + convertIntToLetter(row) + convertCompColToRegular(col));
	        }
	        
	        
	        //System.out.println("\nUser Board Now,");
	        //reader.nextLine();
	        user.playerGrid.printCombined();
	        System.out.println("PRESS ENTER TO CONTINUE...");
	        reader.nextLine();
	    }
	    
	    /**
	     * 
	     * @param p
	     * @param opp
	     * @return result
	     * User board play
	     */
	    private static String askForGuess(Player p, Player opp)
	    {
	        System.out.println("User turn..Enter to continue...");
	        System.out.println("Computer's Grid Status,");
	        p.oppGrid.printStatus();
	        
	        int row = -1;
	        int col = -1;
	        
	        String oldRow = "Z";
	        int oldCol = -1;
	        
	        while(true)
	        {
	            System.out.print("Enter row (A-J): ");
	            String userInputRow = reader.next();
	            userInputRow = userInputRow.toUpperCase();
	            oldRow = userInputRow;
	            row = convertLetterToInt(userInputRow);
	                    
	            System.out.print("Enter column (1-10): ");
	            col = reader.nextInt();
	            oldCol = col;
	            col = convertUserColToProCol(col);
	                    
	            //System.out.println("DEBUG: " + row + col);
	                    
	            if (col >= 0 && col <= 9 && row != -1)
	                break;
	                    
	            System.out.println("Invalid location!");
	        }
	      //  System.out.println("DEBUG "+ opp.playerGrid.hasShip(row, col));
	        
	        if (opp.playerGrid.hasShip(row, col))
	        {
	            p.oppGrid.markHit(row, col);
	            opp.playerGrid.markHit(row, col);
	            return "** USER HIT AT " + oldRow + oldCol + " **";
	        }
	        else
	        {
	            p.oppGrid.markMiss(row, col);
	            opp.playerGrid.markMiss(row, col);
	            return "** USER MISS AT " + oldRow + oldCol + " **";
	        }
	    }
	    /**
	     * User Board setup
	     * @param p
	     */
	    private static void setup(Player p)
	    {
	        p.playerGrid.printShips();
	        System.out.println();
	        int counter = 1;
	        int normCounter = 0;
	        while (p.numOfShipsLeft() > 0)
	        {
	            for (Ship s: p.ships)
	            {
	                System.out.println("\nShip #" + counter + ": of Length-" + s.getLength());
	                int row = -1;
	                int col = -1;
	                int dir = -1;
	                while(true)
	                {
	                    System.out.print("Enter row (A-J): ");
	                    String userInputRow = reader.next();
	                    userInputRow = userInputRow.toUpperCase();
	                    row = convertLetterToInt(userInputRow);
	                    
	                    System.out.print("Enter column (1-10): ");
	                    col = reader.nextInt();
	                    col = convertUserColToProCol(col);
	                    
	                    System.out.print("Enter direction (0-H, 1-V): ");
	                    dir = reader.nextInt();
	                    
	                   //validate location
	                    
	                    if (col >= 0 && col <= 9 && row != -1 && dir != -1) 
	                    {
	                        if (!hasErrors(row, col, dir, p, normCounter)) 
	                        {
	                            break;
	                        }
	                    }
	    
	                    System.out.println("Invalid location!");
	                }
	            
	                //System.out.println("FURTHER DEBUG: row = " + row + "; col = " + col);
	                p.ships[normCounter].setLocation(row, col);
	                p.ships[normCounter].setDirection(dir);
	                p.playerGrid.addShip(p.ships[normCounter]);
	                p.playerGrid.printShips();
	                System.out.println();
	                System.out.println("You have " + p.numOfShipsLeft() + " remaining ships to place.");
	                
	                normCounter++;
	                counter++;
	            }
	        }
	    }

	    /**
	     * Computer board setup
	     * @param p
	     */
	    private static void setupComputer(Player p)
	    {
	        System.out.println();
	        int counter = 1;
	        int normCounter = 0;
	        
	        Randomizer rand = new Randomizer();
	        
	        while (p.numOfShipsLeft() > 0)
	        {
	            for (Ship s: p.ships)
	            {
	                int row = rand.nextInt(0, 9);
	                int col = rand.nextInt(0, 9);
	                int dir = rand.nextInt(0, 1);
	                //validate errors             
	                while (hasErrors(row, col, dir, p, normCounter)) 
	                {
	                    row = rand.nextInt(0, 9);
	                    col = rand.nextInt(0, 9);
	                    dir = rand.nextInt(0, 1);
	                  
	                }
	                
	                p.ships[normCounter].setLocation(row, col);
	                p.ships[normCounter].setDirection(dir);
	                p.playerGrid.addShip(p.ships[normCounter]);
	                
	                normCounter++;
	                counter++;
	            }
	        }
	    }
	    
	    /**
	     * Validation of Users Ship placements
	     * 
	     * @param row
	     * @param col
	     * @param dir
	     * @param p
	     * @param count
	     * @return
	     */
	    private static boolean hasErrors(int row, int col, int dir, Player p, int count)
	    {
	        
	        int length = p.ships[count].getLength();
	        
	        // Check fitting- Horizontal
	        if (dir == 0)
	        {
	            int checker = length + col;
	            //System.out.println("DEBUG: checker is " + checker);
	            if (checker > 10)
	            {
	                System.out.println("Ship cannot be placed");
	                return true;
	            }
	        }
	        // Check fitting- Vertical
	        if (dir == 1) 
	        {
	            int checker = length + row;
	            if (checker > 10)
	            {
	                System.out.println("Ship cannot be placed");
	                return true;
	            }
	        }
	            
	        // Overlapping - Horizontally
	        if (dir == 0) 
	        {
	            
	            for (int i = col; i < col+length; i++)
	            {
	               
	                if(p.playerGrid.hasShip(row, i))
	                {
	                    System.out.println("There is ship already present at the given horizontal coordinates");
	                    return true;
	                }
	            }
	        }
	     // Overlapping - vertically
	        else if (dir == 1) 
	        {
	            
	            for (int i = row; i < row+length; i++)
	            {
	                
	                if(p.playerGrid.hasShip(i, col))
	                {
	                    System.out.println("There is ship already present at the given vertical coordinates");
	                    return true;
	                }
	            }
	        }
	        
	        return false;
	    }
	

	    /**
	     * 
	     * @param val
	     * @return characters of val
	     */
	    private static int convertLetterToInt(String val) {
	    	
	    	char c = val.charAt(0);
			if(c>= 'A' && c<='J' ) {
			return ((int)c-65);
			}
	    	
	    	return -1;
	    }
	    
	
	    /**
	     * 
	     * @param val
	     * @return character of value
	     */
	    private static String convertIntToLetter(int val) {
	    	if(val>=0 && val<=9) {
	    		return String.valueOf((char)(val+65));
	    	}
	    	return "Z";
	    }
	    
	 
	    
	    /**
	     * 
	     * @param val
	     * @return val-1
	     */
	    private static int convertUserColToProCol(int val) {
	    	
	    	if(val >=1 && val<=10) {
	    		return (val-1);
	    	}
	    	
	    	return -1;
	    }

	    
	    /**
	     * 
	     * @param val
	     * @return val+1
	     */
	    private static int convertCompColToRegular(int val) {
	    	
	    	if(val>=0 && val<=9) {
	    		return (val+1);
	    	}
	    	
	    	return -1;
	    }
	    
	
}
