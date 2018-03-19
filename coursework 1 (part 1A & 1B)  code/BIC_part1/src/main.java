import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;
import java.util.*;

public class main {

	 public static void main(String[] args) throws Exception {

		 
		 	// specify site to get content
	        URL site = new URL("http://www.math.uwaterloo.ca/tsp/world/lu980.tsp");
	        BufferedReader in = new BufferedReader(
	        new InputStreamReader(site.openStream()));
	        FileWriter fwriter0 =new FileWriter("output.txt",true);
			BufferedWriter outputFile0 = new BufferedWriter(fwriter0);
	        String inputLine;
	        
	        // Array-list of Array-list that will contain all cities with Latitude & Longitude
	        ArrayList<ArrayList<Double>> Citylist = new ArrayList<ArrayList<Double>>();
	        
	        //read file
	        while ((inputLine = in.readLine()) != null)
	        	
	        	//read lines that start with a number
	        	if (Character.isDigit(inputLine.charAt(0)))
	        	{
	        	
	        	// split lines to number - latitude - longitude (by space)
	        	String[] splited = inputLine.split("\\s+");
	        	double d0 = Double.parseDouble(splited[0]);
	        	double d1 = Double.parseDouble(splited[1]);
	        	double d2 = Double.parseDouble(splited[2]);
	        	
	        	// put content Latitude and longitude to buffer Array-list
	        	ArrayList<Double> city = new ArrayList<Double>();
	        	city.add(d1); city.add(d2);
	        	
	        	//Eliminate redundant Cities
	        	//check of main Array-list of Array-list contains buffer Array-list and add if not
	        	if (!Citylist.contains(city))
	        	{
	        		Citylist.add(city);
	        	}
	        	}
	        in.close();
	        
	        // call Genetic Algorithm class
	        genetic_algorithim ga= new genetic_algorithim(Citylist,outputFile0);
	        outputFile0.close();
	    }

}
