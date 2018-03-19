import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Individual {

	public Individual(ArrayList<ArrayList<Double>> Cities, int[] indiv,BufferedWriter outputFile2) throws IOException
	{

		//print Elements of solution
		outputFile2.newLine();
		outputFile2.write(Arrays.toString(indiv));
		outputFile2.newLine();
		System.out.println(Arrays.toString(indiv));
		
		//print fitness of solution
		outputFile2.newLine();
		outputFile2.write("Fitness: " + GetFitness(Cities,indiv));
		outputFile2.newLine();
		System.out.println("Fitness: " + GetFitness(Cities,indiv));
	}
	
	public void Print(ArrayList<ArrayList<Double>> Cities, int[] indiv, BufferedWriter outputFile2) throws IOException
	{
				//print Elements of solution
				outputFile2.newLine();
				outputFile2.write(Arrays.toString(indiv));
				outputFile2.newLine();
				System.out.println(Arrays.toString(indiv));
				
				//print fitness of solution
				outputFile2.newLine();
				outputFile2.write("Fitness: " + GetFitness(Cities,indiv));
				outputFile2.newLine();
				System.out.println("Fitness: " + GetFitness(Cities,indiv));
	}
	
	public int GetFitness(ArrayList<ArrayList<Double>> Cities, int[] indiv)
	{
		int F = 0;
		for (int i = 0; i<Cities.size(); i++)
		{
		double Lat_i, Lat_j;
		double Long_i, Long_j;
		double q1, q2, q3, q4, q5;
		double distance;
		int distance_int; 
		// From each individual element subtracts 1 to match index of city
		// individual span is 1-->634 but cities are 0-->633 index
		Lat_i = (Math.PI * Cities.get(indiv[i]-1).get(0))/180;
		Long_i = (Math.PI * Cities.get(indiv[i]-1).get(1))/180;
		if (i+1 < Cities.size())
		{
		Lat_j = (Math.PI * Cities.get(indiv[i+1]-1).get(0))/180;
		Long_j = (Math.PI * Cities.get(indiv[i+1]-1).get(1))/180;
		}
		else
		{
			Lat_j = (Math.PI * Cities.get(indiv[0]-1).get(0))/180;
			Long_j = (Math.PI * Cities.get(indiv[0]-1).get(1))/180;
		}
		q1 = Math.cos(Lat_j) * Math.sin(Long_i - Long_j);
		q3 = Math.sin((Long_i - Long_j)/2.0);
		q4 = Math.cos((Long_i - Long_j)/2.0);
		q2 = Math.sin(Lat_i+Lat_j) * q3 * q3 - Math.sin(Lat_i - Lat_j) *q4 *q4;
		q5 = Math.cos(Lat_i-Lat_j) * q4 * q4 - Math.cos(Lat_i + Lat_j) *q3 *q3;
		distance = 6378388 * Math.atan2(Math.sqrt(q1 *q1 + q2 *q2), q5) + 1.0;
		distance_int = (int)distance;
		F = F + distance_int;
		}
		return Math.abs(F);
	}
	
	public int[] GetMutation(ArrayList<ArrayList<Double>> Cities, int[] indiv, BufferedWriter outputFile2) throws IOException
	{
		int [] MutatedIndiv = new int[Cities.size()];
		MutatedIndiv = indiv;
		Random random = new Random();
		//k-inversion mutation
		//chose random starting position
		int Start = random.nextInt(Cities.size()-1) + 1;
		//chose random k value where min value is 3
		int k = random.nextInt(Cities.size() - 3) + 3;
		outputFile2.newLine();
		outputFile2.write("using " + k + "-inversion mutation starting at index:" + Start );
		outputFile2.newLine();
		outputFile2.newLine();
		outputFile2.write("parent: ");
		outputFile2.newLine();
		System.out.println("using " + k + "-inversion mutation starting at index:" + Start );
		System.out.println("parent: ");
		Print(Cities, indiv, outputFile2);
		int [] temp = new int[k];
		int zeroindex = -1;
		for(int i =0; i<k;i++)
		{
			if (Start+i<Cities.size())
			{
				temp[i]=indiv[Start+i];
			}
			else
			{
				temp[i]=indiv[zeroindex+1];
				zeroindex++;
			}
		}
		outputFile2.newLine();
		outputFile2.write("array to invert: ");
		outputFile2.newLine();
		outputFile2.newLine();
		outputFile2.write(Arrays.toString(temp));
		outputFile2.newLine();
		System.out.println("array to invert: ");
		System.out.println(Arrays.toString(temp));
		int k_copy = k;
		int zeroindex2 = -1;
		for(int i =0; i<k; i++)
		{
			if(Start+i<Cities.size())
			{
				MutatedIndiv[Start+i] = temp[k_copy-1];
			}
			else
			{
				MutatedIndiv[zeroindex2+1] = temp[k_copy-1];
				zeroindex2++;
			}
			k_copy--;
		}
		outputFile2.newLine();
		outputFile2.write("child: ");
		outputFile2.newLine();
		System.out.println("child: ");
		Print(Cities, MutatedIndiv, outputFile2);
		return MutatedIndiv;
	}
}
