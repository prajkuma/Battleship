
package com.java.game;

/**
 * @author PC01
 *Oct 27, 2022
Randomizer.java
User defined Random class class
 */
import java.util.Random;

public class Randomizer
{

 public static Random theInstance = null;
 
 public Randomizer(){
  
 }
 /**
  * initializes random class
  * @return
  */
 public static Random getInstance(){
  if(theInstance == null){
   theInstance = new Random();
  }
  return theInstance;
 }
 
 
 /*
  * returns next number
  */
 public static int nextInt(){
  return Randomizer.getInstance().nextInt();
 }
/*
 * returns next integer
 */
 public static int nextInt(int n){
  return Randomizer.getInstance().nextInt(n);
 }
/*
 * returns number betwen 0 and 9
 */
 public static int nextInt(int min, int max){
  return min + Randomizer.nextInt(max - min + 1);
 }

}
