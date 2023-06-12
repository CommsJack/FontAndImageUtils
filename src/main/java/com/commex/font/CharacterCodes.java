package com.commex.font;

import java.util.ArrayList;

public class CharacterCodes 
{
	private ArrayList<String> charCodes;
	
	private int count = 0;
	
	public CharacterCodes()
	{
		this.construct();
	}
	
	public String next()
	{
		count++;
		
		return this.charCodes.get(count-1);
	}
	
	private void construct()
	{
		this.charCodes = new ArrayList<String>();
		
		this.charCodes.add("_a");
		this.charCodes.add("_b");
		this.charCodes.add("_c");
		this.charCodes.add("_d");
		this.charCodes.add("_e");
		this.charCodes.add("_f");
		this.charCodes.add("_g");
		this.charCodes.add("_h");
		this.charCodes.add("_i");
		this.charCodes.add("_j");
		this.charCodes.add("_k");
		this.charCodes.add("_l");
		this.charCodes.add("_m");
		this.charCodes.add("_n");
		this.charCodes.add("_o");
		this.charCodes.add("_p");
		this.charCodes.add("_q");
		this.charCodes.add("_r");
		this.charCodes.add("_s");
		this.charCodes.add("_t");
		this.charCodes.add("_u");
		this.charCodes.add("_v");
		this.charCodes.add("_w");
		this.charCodes.add("_x");
		this.charCodes.add("_y");
		this.charCodes.add("_z");
		this.charCodes.add("A");
		this.charCodes.add("B");
		this.charCodes.add("C");
		this.charCodes.add("D");
		this.charCodes.add("E");
		this.charCodes.add("F");
		this.charCodes.add("G");
		this.charCodes.add("H");
		this.charCodes.add("I");
		this.charCodes.add("J");
		this.charCodes.add("K");
		this.charCodes.add("L");
		this.charCodes.add("M");
		this.charCodes.add("N");
		this.charCodes.add("O");
		this.charCodes.add("P");
		this.charCodes.add("Q");
		this.charCodes.add("R");
		this.charCodes.add("S");
		this.charCodes.add("T");
		this.charCodes.add("U");
		this.charCodes.add("V");
		this.charCodes.add("W");
		this.charCodes.add("X");
		this.charCodes.add("Y");
		this.charCodes.add("Z");
		this.charCodes.add("zero");
		this.charCodes.add("one");
		this.charCodes.add("two");
		this.charCodes.add("three");
		this.charCodes.add("four");
		this.charCodes.add("five");
		this.charCodes.add("six");
		this.charCodes.add("seven");
		this.charCodes.add("eight");
		this.charCodes.add("nine");
		this.charCodes.add("exclam");
		this.charCodes.add("numbersign");
		this.charCodes.add("dollar");
		this.charCodes.add("percent");
		this.charCodes.add("ampersand");
		this.charCodes.add("parenleft");
		this.charCodes.add("parenright");
		this.charCodes.add("asterisk");
		this.charCodes.add("plus");
		this.charCodes.add("comma");
		this.charCodes.add("hyphen");
		this.charCodes.add("period");
		this.charCodes.add("slash");
		this.charCodes.add("colon");
		this.charCodes.add("semicolon");
		this.charCodes.add("less");
		this.charCodes.add("equal");
		this.charCodes.add("greater");
		this.charCodes.add("question");
		this.charCodes.add("at");
		this.charCodes.add("underscore");
		this.charCodes.add("bracketleft");
		this.charCodes.add("backslash");
		this.charCodes.add("bracketright");
		this.charCodes.add("braceleft");
		this.charCodes.add("bar");
		this.charCodes.add("braceright");
		this.charCodes.add("asciicircum");
		this.charCodes.add("grave");
		this.charCodes.add("asciitilde");
		this.charCodes.add("quotedbl");
		this.charCodes.add("quotesingle");
	}
	
}
