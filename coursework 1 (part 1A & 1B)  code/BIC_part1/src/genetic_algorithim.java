import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class genetic_algorithim {

	// population initialization
	int[][] population;
	
	// population size initialization
	int popsize = 500;
	
	// fitness of population initialization
	int [] Popfitness;
	
	// Max number of generations
	int Maxgen =1000;
	
	//tournament selection selection size
	int tourSize = (int) Math.ceil(popsize/100)+1;
	
	public genetic_algorithim(ArrayList<ArrayList<Double>> Cities, BufferedWriter outputFile1) throws IOException
	{
		PrintWriter writer = new PrintWriter("bestfitness.txt", "UTF-8");
		// printing of all Cities and their corresponding latitude and longitude
		System.out.println("My Cities");
		for (int i=0; i<Cities.size();i++)
        {
			outputFile1.newLine();
			outputFile1.write(i+1 + ": Latitude --> " + Cities.get(i).get(0) + " Longitude --> " + Cities.get(i).get(1));
			outputFile1.newLine();
        	System.out.println(i+1 + ": Latitude --> " + Cities.get(i).get(0) + " Longitude --> " + Cities.get(i).get(1));
        }
		
		// setting population of 5 solutions with each containing cities.size() elements
		population = new int[popsize][Cities.size()];
		
		// setting fitness of population array to 5 (matches number of solutions
		Popfitness = new int[popsize];
		outputFile1.newLine();
		outputFile1.newLine();
		System.out.println();
		System.out.println();
		
		//creating a population 
		outputFile1.newLine();
		outputFile1.write("My Population");
		outputFile1.newLine();
		System.out.println("My Population");
		for (int i=0; i<popsize; i++)
		{
			//each solution in population will have Cities.size() with distinct random elements from 1 to excluding Cities.size() 
			population[i] = new Random().ints(1, Cities.size()+1).distinct().limit(Cities.size()).toArray();
			// Call individual Class
			outputFile1.newLine();
			outputFile1.write("Population:" + i + "--->");
			outputFile1.newLine();
			System.out.println("Population:" + i + "--->");
			Individual idv = new Individual(Cities, population[i], outputFile1);	
			//populate population fitness array
			Popfitness[i] =idv.GetFitness(Cities, population[i]);
		}
		
		outputFile1.newLine();
		outputFile1.newLine();
		outputFile1.write("Fitness Array is given:");
		outputFile1.newLine();
		outputFile1.newLine();
		outputFile1.write(Arrays.toString(Popfitness));
		outputFile1.newLine();
		outputFile1.newLine();
		outputFile1.newLine();
		outputFile1.newLine();
		System.out.println();
		System.out.println("Fitness Array is given:");
		System.out.println(Arrays.toString(Popfitness));
		System.out.println();
		System.out.println();
		System.out.println();
		
		// initialize counter that keeps record of number of generations without improvement
		int NoImprovCounter=0;
		
		
		//Initialize array with tournament size
		
		int [] tourSel = new int [tourSize];
		
		int count = 1;
		for (int i =0; i<Maxgen && NoImprovCounter <30 ; i++ )
		{
			//Randomize the indices from which we will select our solutions from the population (selection)
			tourSel = new Random().ints(0, popsize).distinct().limit(tourSize).toArray();
			//Print selected solutions and their corresponding fitness
			outputFile1.newLine();
			outputFile1.write(count + ": selected solutions and their corresponding fitness");
			outputFile1.newLine();
			System.out.println(count + ": selected solutions and their corresponding fitness");
			for (int g=0; g<tourSize; g++)
			{
				Individual idv = new Individual(Cities, population[tourSel[g]], outputFile1);
			}	
			//compare fitness of both selections, get most fit
			int FittestParent = 0;
			for (int k =0; k<tourSize; k++)
			{
				if (Popfitness[tourSel[k]]<Popfitness[tourSel[FittestParent]])
				{
					FittestParent = k;
				}
			}
			outputFile1.newLine();
			outputFile1.write("Best solution from this selection is: ");
			outputFile1.newLine();
			System.out.println("Best solution from this selection is: ");
			Individual idv2 = new Individual(Cities, population[tourSel[FittestParent]], outputFile1);
			
			outputFile1.newLine();
			outputFile1.newLine();
			outputFile1.newLine();
			outputFile1.newLine();
			System.out.println();
			System.out.println();
			System.out.println();
			
			//mutate Fittest and check for improvement
			int [] MutatedChild = new int[Cities.size()];
			MutatedChild = idv2.GetMutation(Cities, population[tourSel[FittestParent]], outputFile1);
			int ChildFitness = idv2.GetFitness(Cities, MutatedChild);
			
			//Check If parnet fitness is greater than child fitness
			int minPopFitness = GetWorstFitnessFromPop ();
			if(Popfitness[tourSel[FittestParent]]<ChildFitness)
			{
				// increment no improvment counter
				NoImprovCounter++;
			}
			else
			{
				NoImprovCounter=0;
				//update worst in population with child
				population[minPopFitness] = MutatedChild;
				//update weakest population fitness with child fitness
				Popfitness[minPopFitness] = ChildFitness;
			}
			
			
			outputFile1.newLine();
			outputFile1.write("no improvment counter = " + NoImprovCounter);
			outputFile1.newLine();
			outputFile1.newLine();
			outputFile1.write("******************************************************");
			outputFile1.newLine();
			outputFile1.newLine();
			outputFile1.write("best Fitness in Pop: " + Popfitness[GetBestFitnessFromPop ()]);
			outputFile1.newLine();
			outputFile1.newLine();
			outputFile1.write("******************************************************");
			outputFile1.newLine();
			outputFile1.newLine();
			outputFile1.newLine();
			System.out.println("no improvment counter = " + NoImprovCounter);
			System.out.println("******************************************************");
			System.out.println("best Fitness in Pop: " + Popfitness[GetBestFitnessFromPop ()]);
			writer.println(Popfitness[GetBestFitnessFromPop ()]);
			System.out.println("******************************************************");
			System.out.println();
			System.out.println();
			
			count++;
		}
		writer.close();
	}
	
	
	// function to get index of worst fitness in population
	public int GetWorstFitnessFromPop ()
	{
		int index = 0;
		for(int i=0; i<popsize; i++ )
		{
			if (Popfitness[i]>Popfitness[index])
			{
				index = i;
			}
		}
		return index;
	}
	
	public int GetBestFitnessFromPop ()
	{
		int index = 0;
		for(int i=0; i<popsize; i++ )
		{
			if (Popfitness[i]<Popfitness[index])
			{
				index = i;
			}
		}
		return index;
	}
}
