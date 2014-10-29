/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;

public class Hangman extends ConsoleProgram
{  
	public static void main(String[] args) {
		new Hangman().start(args);
	}
//set the size of the window
   public static final int APPLICATION_WIDTH = 800;
   public static final int APPLICATION_HEIGHT = 600;
//instances of the program 
	//the object that used for choosing a random number
	private HangmanLexicon chooser=new HangmanLexicon();
	//to generate random numbers for helping in choosing a random word
	private RandomGenerator rgen=RandomGenerator.getInstance();
	//number of wrong turns that the player can guess
	private int numberOfGuesses;
	//keeping track of the guessed word of the player
	private String guessedWord;
	//the random word chosen for the player to guess
	private String word;
	boolean play;
	//canvas instance
	private HangmanCanvas canvas;
//CONSTANTS OF THE PROGRAM
	private final int MAXNUMBEROFCHOOSER=chooser.getWordCount();

    public void run()
    {
      while(true)
      {
    	initialize();
    	println("Welcome to Hang Man :D");
    	while(play)
     		{canvas.displayWord(guessedWord);
     		 char guessLetter=yourGuess();
     		 checkGuess(guessLetter);
     		 checkTerminate();
     		}
    	print("Do you want to play more ?(Y/N)");
    	String answer=readLine();
    	if(answer.equalsIgnoreCase("n"))
    	{break;}   
      }
	}
    public void init()
    {
    	canvas = new HangmanCanvas();
    	add(canvas);
    	
    	
    }
    private void checkTerminate()
    {
    	if(numberOfGuesses==0)
    	{
    		println("you lose");
    		println("the word is ::::"+word);
    		play=false;
    	}
    	if(guessedWord.indexOf('-')==-1)
    	{
    		println("you WIN");
    		canvas.displayWord(guessedWord);
    		play=false;
    	}
    }
    //checks the guess if right
    private void checkGuess(char guess)
    {   
    	if(isPresent(guess))
    	{
    		updateGuessedWord(guess);
    	}
    	else 
    	{
    		numberOfGuesses--;
    	}
    }
    //updates the guessed word and put instead of dashes the guess
    private void updateGuessedWord(char guess)
    {   
    	String temp="";
    	for(int i=0;i<guessedWord.length();i++)
    	{
    		if(word.charAt(i)==guess)
    		{
    			temp+=guess;
    		}
    		else temp+=guessedWord.charAt(i);
    	}
    	guessedWord=temp;
    }
    //checks if the char is present in the random word
    private boolean isPresent(char guess)
    {
    	if(word.indexOf(guess)==-1)
    		{
    		 println("that guess is incorrect");
    		 canvas.noteIncorrectGuess(guess);
    		 return false;
    		}	
    	else 
    		{
    		 println("that guess is correct !!");
    		 return true;
    		}
    }
    
    //allows the user to enter the guess and returns it
    private char yourGuess()
    { println("The Word now looks like this :"+guessedWord);
      println("you have "+numberOfGuesses+" Left");
      String guess;
      while(true)
      	{ 	print("your guess:");
      		guess=readLine();
      		if (guess.length()==1&&Character.isLetter(guess.charAt(0)))
      			break;
      		else
      			println("This is not a valid guess !! :S");
      	}
      char temp=guess.charAt(0);
      return Character.toUpperCase(temp);
    }
    //puts dashes in the place of the mystery word
    private void initialize()
    {   play=true;
    	numberOfGuesses=8;
    	word=chooseRandomWord();
        initGuessedWord(word);
        canvas.reset();
    }
    private void initGuessedWord(String x)
    {	guessedWord="";
    	for(int i=0;i<x.length();i++)
    	{
    		guessedWord+="-";
    	}
    }
    //a function that generates a random word from the class lexicon
    private String chooseRandomWord()
    {
    	int randomNumber=rgen.nextInt(MAXNUMBEROFCHOOSER);
    	String temp=chooser.getWord(randomNumber);
    	return temp;
    }

}
