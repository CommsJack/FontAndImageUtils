package com.commex.image;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

import com.commex.imagemagick.Magick;

public class Image 
{
	
	
	public static void resize(String imagePath, String MaxExtremity) throws IOException
	{
		File input = new File(imagePath);
		
		Magick convert = Image.createMagick();
		
    	File output = new File(input.getPath() + ".jpg");
    	
    	String resizeParameter = calculatePixelSize(MaxExtremity);
    	
    	resizeParameter = resizeParameter + "x" + resizeParameter;
    	
    	convert.addInputHandle("size", resizeParameter);
    	
    	convert.addOutputHandle("resize", resizeParameter);
    	
		convert.setInput(input);
		
		convert.setOutput(output);
		
		convert.execute();
    	
    	System.out.println("Process Complete!");
	}
	
	private static String calculatePixelSize(String mmSize)
	{
		BigDecimal calc = new BigDecimal(mmSize).multiply(new BigDecimal("23.62204"));
		
		BigDecimal rounded = calc.setScale(0, RoundingMode.HALF_UP);
		
		return rounded.toPlainString();
	}
	
	
	private static Magick createMagick()
	{
		Magick returnObj = new Magick();
		
		returnObj.addInputHandle("density", "600");
		returnObj.addInputHandle("colorspace", "cmyk");
		returnObj.addInputHandle("units", "pixelsperinch");
		
		
		returnObj.addOutputHandle("colorspace", "cmyk");
		returnObj.addInputHandle("density", "600");
		
		return returnObj;
	}
}
