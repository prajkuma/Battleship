/**
 * 
 */
package com.java.game;

/**
 * @author PC01
 *Oct 27, 2022
Location.java
 */
public class Location
{
    // Global Vars
    public static final int UNGUESSED = 0;
    public static final int HIT = 1;
    public static final int MISSED = 2;
    
    // Instance Variables
    private boolean hasShip;
    private int status;
    private int lengthOfShip;
    private int directionOfShip;
    
    // Location constructor. 
    public Location()
    {
        // Set initial values
        status = 0;
        hasShip = false;
        lengthOfShip = -1;
        directionOfShip = -1;
    }

    // Check hit
    public boolean checkHit()
    {
        if (status == HIT)
            return true;
        else
            return false;
    }

    //Check miss
    public boolean checkMiss()
    {
        if (status == MISSED)
            return true;
        else
            return false;
    }

    // location unassigned
    public boolean isUnguessed()
    {
        if (status == UNGUESSED)
            return true;
        else
            return false;
    }

    //  location is hit
    public void markHit()
    {
        setStatus(HIT);
    }

    // location is miss
    public void markMiss()
    {
        setStatus(MISSED);
    }

    // check location has ship
    public boolean hasShip()
    {
        return hasShip;
    }

    // Set location has a ship.
    public void setShip(boolean val)
    {
        this.hasShip = val;
    }

    // Set hit/miss status
    public void setStatus(int status)
    {
        this.status = status;
    }

    // Get hit/miss status
    public int getStatus()
    {
        return status;
    }
    
    //get Length ship
    public int getLengthOfShip()
    {
        return lengthOfShip; 
    }
    //set Length  ship
    public void setLengthOfShip(int val)
    {
        lengthOfShip = val;
    }
    
   public int getDirectionOfShip()
    {
        return directionOfShip; 
    }
    
    // horizontal/vertical direction
    public void setDirectionOfShip(int val)
    {
        directionOfShip = val;
    }
}
