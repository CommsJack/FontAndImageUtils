package com.commex.font;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.FilenameUtils;

import com.commex.console.ProgressBarReporter;
import com.commex.fontforge.FontForge;
import com.commex.imagemagick.Magick;

public class Font 
{
	
	public static void createFont(String imageDirectoryPath) throws IOException
	{
		File input = new File(imageDirectoryPath);
		
		CharacterCodes charCodes = new CharacterCodes();
		
		FontForgeScript script = new FontForgeScript(input);
		
		script.setFontName(input.getName());
		
		Magick convert = Font.createMagick();
		
    	File outputDirectory = new File(input.getPath() + "\\FF-Images");
    	
    	File[] backlog = new File[] {};
    	
    	if(!outputDirectory.exists())
    	{
    		outputDirectory.mkdir();
    	}
    	else
    	{
    		backlog = outputDirectory.listFiles();
    	}
    	
    	ProgressBarReporter pb = new ProgressBarReporter();
    	
    	pb.setMeasureAgainst(input.listFiles().length);
    	
    	pb.setPreCursorText("Image Conversion Progress: ");
    	
    	int countFiles = 0;
    	
    	for(File file : input.listFiles())
    	{
    		if(!file.isDirectory() && !file.getName().equals("FFScript.pe"))
    		{	
    			String cc = charCodes.next();
    			
    			String ext = FilenameUtils.getExtension(file.getName());
    			
    			convert.setInput(file);
    			
    			convert.setOutput(outputDirectory.getPath() + "\\" + cc.concat(".png"));

    			convert.execute();
    			
    			script.addImageFile(convert.getOutput());
    			
    			if(!file.getName().equals(cc + "." + ext))
    			{
    				file.renameTo(new File(file.getParent() + "\\" + cc + "." + ext));
    			}
    			
    			countFiles++;
    			
    			pb.reportProgress(countFiles);
    		}
    	}
    	Map<File, byte[]> fileContents = new HashMap<>();
    	for(File file : backlog)
    	{
    		String cc = charCodes.next();
    		
    		String ext = FilenameUtils.getExtension(file.getName());
    		
    		byte[] bytes = Files.readAllBytes(file.toPath());
    		
    		File targetFile = new File(file.getParent() + "\\" + cc + "." + ext);
    		
    		fileContents.put(targetFile, bytes);
    		
    		script.addImageFile(targetFile);
    		
    		countFiles++;
    	}
    	for(Entry<File, byte[]> entry : fileContents.entrySet())
    	{
    		entry.getKey().delete();
    		Files.write(entry.getKey().toPath(), entry.getValue());
    	}
    	
    	script.close();
    	
    	System.out.print("Compiling font - this may take a few minutes.");
    	
    	FontForge ff = new FontForge();
    	
    	ff.setLibraryPath("C:\\Font Forge Portable");
    	
    	ff.execute(script.getScriptFile());
    	
    	script.cleanup();
    	
    	deleteFolder(outputDirectory);
    	
    	System.out.print("Process Complete!");
	}
	
	private static void deleteFolder(File folder) {
	    File[] files = folder.listFiles();
	    if(files!=null) { //some JVMs return null for empty dirs
	        for(File f: files) {
	            if(f.isDirectory()) {
	                deleteFolder(f);
	            } else {
	                f.delete();
	            }
	        }
	    }
	    folder.delete();
	}
	
	private static Magick createMagick()
	{
		Magick returnObj = new Magick();
		
		returnObj.addInputHandle("density", "600");
		returnObj.addInputHandle("colorspace", "gray");
		returnObj.addInputHandle("level", "50x100%");
		returnObj.addInputHandle("units", "pixelsperinch");
		returnObj.addInputHandle("size", "1000x1000");    	  
		returnObj.addOutputHandle("thumbnail", "1000x1000");
		returnObj.addOutputHandle("background", "white");
		returnObj.addOutputHandle("gravity", "center");
		returnObj.addOutputHandle("extent", "1000x1000");
		returnObj.addInputHandle("density", "600");
		
		return returnObj;
	}
}
