/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import acm.util.*;
import java.io.*;
import java.util.*;

public class HangmanLexicon {
	private ArrayList <String> words; 
	public HangmanLexicon()
	{  words=new ArrayList<String>();
		try
		{
			BufferedReader rd=new BufferedReader(new FileReader("HangmanLexicon.txt"));
			while(true)
			{
				String line=rd.readLine();
				if(line==null)break;
				words.add(line);
			}
		}
		catch (IOException ex)
		{
			throw new ErrorException(ex);
		}
	   
	}
/** Returns the number of words in the lexicon. */
	public int getWordCount() {
		return words.size();
	}

/** Returns the word at the specified index. */
	public String getWord(int index) {
		return words.get(index);
	};
}
