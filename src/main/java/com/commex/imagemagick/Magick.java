package com.commex.imagemagick;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;

public class Magick 
{
	private LinkedHashMap<String, String> inputHandles = new LinkedHashMap<String, String>();
	
	private LinkedHashMap<String, String> outputHandles = new LinkedHashMap<String, String>();
			
	private File input = null;
	
	private File output = null;
	
	public void execute()
	{
		this.executeCommand(this.buildCommandLine());
	}
	
	public String getOutputHandle(String handle)
	{
		handle = "-" + handle;
		
		if(outputHandles.containsKey(handle))
		{
			outputHandles.get(handle);
		}
		return null;
	}
	
	public void addOutputHandle(String handle, String value)
	{
		handle = "-" + handle;
		
		if(outputHandles.containsKey(handle))
		{
			outputHandles.replace(handle, value);
		}
		else
		{
			outputHandles.put(handle, value);
		}
	}
	
	public String getInputHandle(String handle)
	{
		handle = "-" + handle;
		
		if(inputHandles.containsKey(handle))
		{
			inputHandles.get(handle);
		}
		return null;
	}
	
	public void addInputHandle(String handle, String value)
	{
		handle = "-" + handle;
		
		if(inputHandles.containsKey(handle))
		{

			inputHandles.replace(handle, value);
		}
		else
		{
			inputHandles.put(handle, value);
		}
	}
	
	public File getInput()
	{
		return this.input;
	}
	
	public File getOutput()
	{
		return this.output;
	}
	
	public void setInput(File inputFile) throws IOException
	{
		if(!inputFile.exists())
		{
			throw new IOException("Cannot locate file: " + inputFile.getPath());
		}
		this.input = inputFile;
	}
	
	public void setInput(String inputFilePath) throws IOException
	{
		this.setInput(new File(inputFilePath));
	}
	
	public void setOutput(File outputFile) throws IOException
	{
		this.output = outputFile;
	}
	
	public void setOutput(String outputFilePath) throws IOException
	{
		this.setOutput(new File(outputFilePath));
	}
	
	private void executeCommand(String commandLine)
	{
		Process p;
        try 
        {
            p = Runtime.getRuntime().exec("cmd /c " + commandLine);
    
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
		
		sb.append("Magick convert ");
		
		for (Map.Entry<String, String> entry : inputHandles.entrySet()) 
		{
	        sb.append(entry.getKey());
	        sb.append(" ");
	        sb.append(entry.getValue());
	        sb.append(" ");
	    }
		
		sb.append("\"");
		sb.append(this.input.getPath());
		sb.append("\" ");
		
		for (Map.Entry<String, String> entry : outputHandles.entrySet()) 
		{
	        sb.append(entry.getKey());
	        sb.append(" ");
	        sb.append(entry.getValue());
	        sb.append(" ");
	    }
		
		sb.append("\"");
		sb.append(this.output.getPath());
		sb.append("\"");
		System.out.println(sb.toString());
		
		return sb.toString();
	}
}
