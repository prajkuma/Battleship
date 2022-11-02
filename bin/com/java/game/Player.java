
package com.java.game;

/**
 * @author PC01
 *Oct 27, 2022
Player.java

* Player class represents player's board
 */

public class Player
{
	 // Individual ship length  D-2,C-3,B-4,A-5
    private static final int[] SHIP_LENGTHS = {2, 3, 4, 5};
    private static final int NUM_OF_SHIPS = 4;
    
    public Ship[] ships;
    
    public Grid playerGrid;
    public Grid oppGrid;
    
    /*
     * player initialized with ships
     */
    public Player()
    {
        ships = new Ship[NUM_OF_SHIPS];
        for (int i = 0; i < NUM_OF_SHIPS; i++)
        {
            Ship tempShip = new Ship(SHIP_LENGTHS[i]);
            ships[i] = tempShip;
        }
        
        playerGrid = new Grid();
        oppGrid = new Grid();
    }
    /**
     * Add ships to board
     */
    public void addShips()
    {
        for (Ship s: ships)
        {
            playerGrid.addShip(s);
        }
    }
    /**
     * 
     * @return no of ships left to be placed
     */
    public int numOfShipsLeft()
    {
        int counter = 4;
        for (Ship s: ships)
        {
            if (s.isLocationSet() && s.isDirectionSet())
                counter--;
        }
        
        return counter;
        
    }
  
}
