package com.commex.image_tools;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;

import com.commex.font.Font;
import com.commex.image.Image;
public class App 
{

    public static void main(String[] args) throws IOException, URISyntaxException 
    {
    	if(args.length > 0)
    	{
    		switch(args[0])
    		{
	    		case "-font":
	    		{
	    			cli_font();
	    		}
	    		case "-image":
	    		{
	    			cli_image();
	    		}
	    		default:
	    		{
	    			System.out.println("You did not supply a valid mode.  Valid modes are '-font' or '-image'");
	    		}
    		}
    	}
    	else
    	{
    		System.out.println("You did not supply a valid mode.  Valid modes are '-font' or '-image'");
    	}
    	
    }
    
    public static void cli_font()
    {
    	// create a scanner so we can read the command-line input
        @SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

        //  prompt for the user's name
        System.out.print("Enter the path to your EPS Folder: ");
        
        // get their input as a String
        File path = new File(scanner.nextLine());
    
        if(path.exists())
        {
        	System.out.println("Commencing font creation for: " + path.getName());
        	
        	try 
        	{
				Font.createFont(path.getPath());
			} 
        	catch (IOException e) 
        	{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        else
        {
        	System.out.print("Fatal Error: Your folder does not exist: " + path.getPath());
        }
    }
    
    public static void cli_image()
    {
    	// create a scanner so we can read the command-line input
        @SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

        //  prompt for the user's name
        System.out.print("Enter the path to the image you want to resize: ");
        
        // get their input as a String
        File path = new File(scanner.nextLine());

        if(path.exists())
        {
        	System.out.print("Enter the max size in mm (e.g 24.1): ");
        	
        	try 
        	{
				Image.resize(path.getPath(), scanner.nextLine());
			} 
        	catch (IOException e) 
        	{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        else
        {
        	System.out.print("Fatal Error: Your image does not exist: " + path.getPath());
        }
    }
}
