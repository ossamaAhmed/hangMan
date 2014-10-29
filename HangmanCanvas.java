/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import acm.graphics.*;

public class HangmanCanvas extends GCanvas {
	
/** Resets the display so that only the scaffold appears */
	public void reset()
	{    removeAll();
		 int center_y=(getHeight()/2);
         int center_x=(getWidth()/2);
		ScaffoldEnd=new GPoint((center_x-BEAM_LENGTH),ARM_OFFSET_FROM_HEAD+(2*HEAD_RADIUS)+ROPE_LENGTH);
		GLine scaffold=new GLine((center_x-BEAM_LENGTH),ScaffoldEnd.getY()+SCAFFOLD_HEIGHT,ScaffoldEnd.getX(),ScaffoldEnd.getY());
		add(scaffold);
		BeamEnd=new GPoint((center_x),ScaffoldEnd.getY());
		GLine beam=new GLine(ScaffoldEnd.getX(),ScaffoldEnd.getY(),BeamEnd.getX(),BeamEnd.getY());
		add(beam);
		RopeEnd=new GPoint((center_x),(BeamEnd.getY()+ROPE_LENGTH));
		GLine rope=new GLine(BeamEnd.getX(),BeamEnd.getY(),RopeEnd.getX(),RopeEnd.getY());
		add(rope);
		guessedLabel=new GLabel("-----",20,20);
		wrongLabel=new GLabel("-----",20,40);
		i=1;
		wrong="";
	}

/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String word) 
	{  
		guessedLabel.setLabel(word);
		add(guessedLabel);
	}
	
/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(char letter) 
	{ 
	  choosePart(i);
	  i++;
	  wrong+=letter;
	  wrongLabel.setLabel(wrong);
	  add(wrongLabel);
	}
	private void choosePart(int i)
	{ switch (i)
		{case 1:HeadEnd=new GPoint(RopeEnd.getX(),RopeEnd.getY()+(2*HEAD_RADIUS));
		   		GOval face=new GOval(RopeEnd.getX()-HEAD_RADIUS,RopeEnd.getY(),HEAD_RADIUS*2,HEAD_RADIUS*2);
		   		add(face);
		   		break;
		 case 2:bodyEnd=new GPoint(HeadEnd.getX(),HeadEnd.getY()+BODY_LENGTH);
		    	GLine body=new GLine(HeadEnd.getX(),HeadEnd.getY(),bodyEnd.getX(),bodyEnd.getY());
		    	add(body);
		   		break;
		 case 3:armBegin=new GPoint(HeadEnd.getX(),HeadEnd.getY()+ARM_OFFSET_FROM_HEAD);
		  		GLine arm=new GLine(armBegin.getX(),armBegin.getY(),armBegin.getX()-UPPER_ARM_LENGTH,armBegin.getY());
		  		GLine arm2=new GLine(armBegin.getX()-UPPER_ARM_LENGTH,armBegin.getY(),armBegin.getX()-UPPER_ARM_LENGTH,armBegin.getY()+LOWER_ARM_LENGTH);
		  		add(arm);
		  		add(arm2);
		  		break;
		 case 4:GLine arm1=new GLine(armBegin.getX(),armBegin.getY(),armBegin.getX()+UPPER_ARM_LENGTH,armBegin.getY());
	  			GLine arm3=new GLine(armBegin.getX()+UPPER_ARM_LENGTH,armBegin.getY(),armBegin.getX()+UPPER_ARM_LENGTH,armBegin.getY()+LOWER_ARM_LENGTH);
	  			add(arm1);
	  			add(arm3);
	  			break;
		 case 5:leftLegEnd=new GPoint(bodyEnd.getX()-HIP_WIDTH,bodyEnd.getY()+LEG_LENGTH);
		        GLine leg=new GLine(bodyEnd.getX(),bodyEnd.getY(),bodyEnd.getX()-HIP_WIDTH,bodyEnd.getY());
		        GLine leg2=new GLine(bodyEnd.getX()-HIP_WIDTH,bodyEnd.getY(),bodyEnd.getX()-HIP_WIDTH,bodyEnd.getY()+LEG_LENGTH);
		        add(leg2);
		        add(leg);
		        break;
		 case 6:rightLegEnd=new GPoint(bodyEnd.getX()+HIP_WIDTH,bodyEnd.getY()+LEG_LENGTH);
		 		GLine leg1=new GLine(bodyEnd.getX(),bodyEnd.getY(),bodyEnd.getX()+HIP_WIDTH,bodyEnd.getY());
		 		GLine leg3=new GLine(bodyEnd.getX()+HIP_WIDTH,bodyEnd.getY(),bodyEnd.getX()+HIP_WIDTH,bodyEnd.getY()+LEG_LENGTH);
		 		add(leg1);
		 		add(leg3);
		 		break;
		 case 7:GLine leftfoot=new GLine(leftLegEnd.getX(),leftLegEnd.getY(),leftLegEnd.getX()-FOOT_LENGTH,leftLegEnd.getY());
		        add(leftfoot);
		        break;
		 case 8:GLine rightfoot=new GLine(rightLegEnd.getX(),rightLegEnd.getY(),rightLegEnd.getX()+FOOT_LENGTH,rightLegEnd.getY());
	            add(rightfoot);
	            break;
		}
	}

/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;
	
	/*instace variables*/
	private GPoint ScaffoldEnd;
	private GPoint BeamEnd;
	private GPoint RopeEnd;
	private GPoint HeadEnd;
	private GPoint bodyEnd;
	private GPoint armBegin;
	private GPoint rightLegEnd;
	private GPoint leftLegEnd;
    private GLabel guessedLabel;
    private GLabel wrongLabel;
    private int i;
    private String wrong;
}
