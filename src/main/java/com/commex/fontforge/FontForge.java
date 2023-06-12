package com.commex.fontforge;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class FontForge 
{ 
	
	private File libraryPath = null;
	
	private File ScriptLocation;
	
	public void execute(File ScriptLocation)
	{
		this.ScriptLocation = ScriptLocation;
		
		this.executeCommand(this.buildCommandLine());
	}
	
	public void setLibraryPath(String path) throws IOException
	{
		this.libraryPath = new File(path);
		
		if(!this.libraryPath.exists())
		{
			throw new IOException("Fontforge Library Path does not exist: " + this.libraryPath.getPath());
		}
	}
	
	
	private void executeCommand(String commandLine)
	{
		Process p;
		
	
        try 
        {
            p = Runtime.getRuntime().exec(commandLine);
    
            p.waitFor(); 
            
            BufferedReader reader=new BufferedReader(new InputStreamReader(p.getInputStream())); 
            
            String line; 
            
            while((line = reader.readLine()) != null) 
            { 
                System.out.println(line);
            } 
        } 
        catch (IOException e) 
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        catch (InterruptedException e) 
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
	
	private String buildCommandLine()
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append("cmd.exe /c cd \"" + libraryPath + "\" && start fontforge-script.bat \"" + this.ScriptLocation.getPath() + "\"");
		
		return sb.toString();
	}
}
