package com.commex.font;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Year;

public class FontForgeScript 
{
	private BufferedWriter writer = null;
	
	private String FontName;
	
	private boolean headerWritten = false;
	
	private File outputDirectory;
	
	private File scriptFile;
	
	public FontForgeScript (File workingPath) throws IOException
	{	
    	this.outputDirectory = workingPath;
    	
    	this.scriptFile = new File(workingPath + "\\FFScript.pe");
    	
    	writer = new BufferedWriter(new FileWriter(this.scriptFile));
    	
	}
	
	public void cleanup()
	{
		this.scriptFile.delete();
	}
	
	private void writeHeader() throws IOException
	{
		
		int year = Year.now().getValue();
		
		if(this.FontName == null)
		{
			throw new IOException("No Font Name specified.");
		}
		
		writer.write("New();\r\n");
		
		writer.write("SetFontNames(\"" + this.FontName + "\",\"" + this.FontName + "\",\"" + this.FontName + "\",\"Regular\", \"Copyright (C) " + year + ", Lloyds Banking Group\", \"001.000\")\r\n");
		
		writer.write("ScaleToEm(1024)\r\n");
		
		this.headerWritten = true;
	}
	
	public void addImageFile(File imageFile)
	{
		
		String path = imageFile.getPath().replace("\\", "\\\\");
		
		String filename = imageFile.getName();
		
		filename = filename.substring(0,filename.length() - 4).replace("_", "");
		
		try {
			
			if(!this.headerWritten)
			{
				this.writeHeader();
			}
			
			writer.write("Select(\"" + filename + "\");\r\n");

			writer.write("Import(\"" + path + "\");\r\n");
			
			writer.write("AutoTrace();\r\n");
			
			writer.write("ClearBackground();\r\n");
			
			writer.flush();
			
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void close() throws IOException
	{
		
		String outputName = this.outputDirectory.getPath() + "\\" + this.FontName;
		
		writer.write("Generate(\"" + outputName.replace("\\", "\\\\") + ".ttf\");\r\n");
		
		writer.write("Save(\"" + outputName.replace("\\", "\\\\") + ".sfd\");\r\n");
		
		this.writer.close();
	}

	public String getFontName() {
		return FontName;
	}

	public void setFontName(String fontName) {
		this.FontName = fontName.trim().replace(" ", "_");
	}

	public File getScriptFile() {
		return scriptFile;
	}
}
