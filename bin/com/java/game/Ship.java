/**
 * 
 */
package com.java.game;

/**
 * @author PC01
 *Oct 27, 2022
Ship.java
Ship configuration details
 */
public class Ship
{
		// Coordinate variables
       private int row;
       private int col;
       private int length;
       private String direction;
      
       public static final int UNSET = -1;
       public static final int HORIZONTAL = 0;
       public static final int VERTICAL = 1;
      
       /**
        * 
        * @param l
        * Initialize ship on length
        */
       public Ship(int l){
           length = l;
           row = UNSET;
           col = UNSET;
       }
       /**
        * 
        * @param r
        * @param c
        * place ship on coordinate
        */
       public void setLocation(int r, int c){
           row = r;
           col = c;
       }
      /**
       * set the direction
       * @param d
       */
       public void setDirection(int d){
          if(d == HORIZONTAL){
              direction = "horizontal";
          }
           if(d == VERTICAL){
              direction = "vertical";
          } if (d == UNSET){
              direction = "unset";
          }
          
       }
       public int getRow(){
           return row;
       }
       public int getCol(){
           return col;
       }
       public int getLength(){
           return length;
       }
       /**
        * 
        * @return direction
        */
       public int getDirection(){
           if (direction == "horizontal"){
               return HORIZONTAL;
           }
           if(direction == "vertical"){
               return VERTICAL;
           }
           return UNSET;
       }
   
       /**
        * 
        * @return whether location is free or not
        */
       public boolean isLocationSet(){
           if (row == -1 || col == -1){
               return false;
           }
           return true;
       }
       /**
        * 
        * @return direction set or not
        */
       public boolean isDirectionSet(){
           if (direction == null){
               return false;
           }
           return true;
       }

}
