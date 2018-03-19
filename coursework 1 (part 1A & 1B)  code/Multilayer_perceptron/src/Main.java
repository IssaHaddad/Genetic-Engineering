import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Random rand = new Random();
		ArrayList<ArrayList<Double>> DataSet = new ArrayList<ArrayList<Double>>();
		double[][] WeightsFromInput;
		double[][] WeightsToOutput;
		WeightsFromInput = new double[10][5];
		WeightsToOutput = new double[10][5];
		double [] ErrorRate = new double[10];
		double [] ErrorRate2 = new double[10];
		double [] Mean_SE = new double[10];
		
		// Initializing 10 random inputs for Cubic function
		double [] In = new double[10];
		In = new Random().doubles(-100, 101).distinct().limit(10).toArray();
		
		// creating a Dataset of 10 random inputs for Cubic function and their corresponding outputs
		for(int i=0;i<10; i++)
		{
			ArrayList<Double> Set = new ArrayList<Double>();
			double out = (In[i]*In[i]*In[i]);
			Set.add(In[i]); Set.add(out);
        	DataSet.add(Set);
        	System.out.println("input: " + DataSet.get(i).get(0) + "   output: " + DataSet.get(i).get(1));
		}
		System.out.println("\n\n");
		
		//initializing 10 sets of weights randomly from input to hidden layer
		//initializing 10 sets of weights randomly from hidden layer to output
		for (int i =0; i<10;i++)
		{
			for(int j =0; j<5;j++)
			{
			WeightsFromInput[i][j] = -5 + (5 - (-5)) * rand.nextDouble();
			WeightsToOutput[i][j] = -5 + (5 - (-5)) * rand.nextDouble();
			}
			System.out.println("WeightsFromInput:" + i + " " + Arrays.toString(WeightsFromInput[i]));
			System.out.println("WeightsToOutput:"  + i + " " + Arrays.toString(WeightsToOutput[i]) + "\n");
		}
		
		//performing forward propagation for all 10 points (with different weights for each time)
		// topology = 1 input, hidden layer= 1x5, output = 1
		System.out.println("\n");
		System.out.println("performing 10 forward propagation for all 10 points (with different weights for each time)");
		for(int j =0;j<10;j++){
			System.out.println("****************************SET of weights = " + (j+1) + "*********************************** -----> ");
		for(int i =0;i<10;i++)
		{
			double hiddenNeuron1_Value;
			double hiddenNeuron2_Value;
			double hiddenNeuron3_Value;
			double hiddenNeuron4_Value;
			double hiddenNeuron5_Value;
			
			double hiddenNeuron1_Sigmoid;
			double hiddenNeuron2_Sigmoid;
			double hiddenNeuron3_Sigmoid;
			double hiddenNeuron4_Sigmoid;
			double hiddenNeuron5_Sigmoid;
			
			double output_Value;
			double output_Sigmoid;
			
			System.out.println("********************************** -----> " + (i+1));
			
			hiddenNeuron1_Value = DataSet.get(i).get(0)* WeightsFromInput[j][0];
			System.out.println("hiddenNeuron1_Value: " + hiddenNeuron1_Value);
			hiddenNeuron2_Value = DataSet.get(i).get(0)* WeightsFromInput[j][1];
			System.out.println("hiddenNeuron2_Value: " + hiddenNeuron2_Value);
			hiddenNeuron3_Value = DataSet.get(i).get(0)* WeightsFromInput[j][2];
			System.out.println("hiddenNeuron3_Value: " + hiddenNeuron3_Value);
			hiddenNeuron4_Value = DataSet.get(i).get(0)* WeightsFromInput[j][3];
			System.out.println("hiddenNeuron4_Value: " + hiddenNeuron4_Value);
			hiddenNeuron5_Value = DataSet.get(i).get(0)* WeightsFromInput[j][4];
			System.out.println("hiddenNeuron5_Value: " + hiddenNeuron5_Value);
			
			hiddenNeuron1_Sigmoid = Sigmoid(hiddenNeuron1_Value);
			System.out.println("hiddenNeuron1_Sigmoid: " + hiddenNeuron1_Sigmoid);
			hiddenNeuron2_Sigmoid = Sigmoid(hiddenNeuron2_Value);
			System.out.println("hiddenNeuron2_Sigmoid: " + hiddenNeuron2_Sigmoid);
			hiddenNeuron3_Sigmoid = Sigmoid(hiddenNeuron3_Value);
			System.out.println("hiddenNeuron3_Sigmoid: " + hiddenNeuron3_Sigmoid);
			hiddenNeuron4_Sigmoid = Sigmoid(hiddenNeuron4_Value);
			System.out.println("hiddenNeuron4_Sigmoid: " + hiddenNeuron4_Sigmoid);
			hiddenNeuron5_Sigmoid = Sigmoid(hiddenNeuron5_Value);
			System.out.println("hiddenNeuron5_Sigmoid: " + hiddenNeuron5_Sigmoid);

			
			output_Value = (hiddenNeuron1_Sigmoid * WeightsToOutput[j][0]) + (hiddenNeuron2_Sigmoid * WeightsToOutput[j][1]) +(hiddenNeuron3_Sigmoid * WeightsToOutput[j][2]) +(hiddenNeuron4_Sigmoid * WeightsToOutput[j][3]) +(hiddenNeuron5_Sigmoid * WeightsToOutput[j][4]);
			System.out.println("output_Value: " + output_Value);
			output_Sigmoid = Sigmoid(output_Value);
			System.out.println("output_Sigmoid: " + output_Sigmoid);
			System.out.println("output_Actual: " + DataSet.get(i).get(1));
			
			//calculating error from each point
			ErrorRate[i] = DataSet.get(i).get(1) - output_Sigmoid ;
			System.out.println("ErrorRate["+i+"]: " + ErrorRate[i]);
			System.out.println("\n");
		}
			Mean_SE[j] = MeanSquaredError(ErrorRate);
			System.out.println("MSE for last weighs = " + Mean_SE[j]);
		}
		System.out.println("\n\n");
		
		// getting mean squared error for first 10 points
		System.out.println("Mean squared error for last 10 attempts with different weights= " + Arrays.toString(Mean_SE));
		
		//getting index of smallest error to use best weights
		int index=0;
		for(int i=0;i<10;i++)
		{
			// performing absolute because error could be negative, we want closest to zero
			if (Math.abs(Mean_SE[i])< Math.abs(Mean_SE[index]))
			{
				index = i;
			}
		}
		System.out.println("\n\n");
		System.out.println("best weights where for index: " + index);
		
				//repeating procedure (with same best weights for each time)
				// topology = 1 input, hidden layer= 1x5, output = 1
		System.out.println("\n");
		System.out.println("repeating procedure (with same best weights for each time)");
				for(int i =0;i<10;i++)
				{
					double hiddenNeuron1_Value;
					double hiddenNeuron2_Value;
					double hiddenNeuron3_Value;
					double hiddenNeuron4_Value;
					double hiddenNeuron5_Value;
					
					double hiddenNeuron1_Sigmoid;
					double hiddenNeuron2_Sigmoid;
					double hiddenNeuron3_Sigmoid;
					double hiddenNeuron4_Sigmoid;
					double hiddenNeuron5_Sigmoid;
					
					double output_Value;
					double output_Sigmoid;
					
					System.out.println("********************************** -----> " + (i+1));
					
					hiddenNeuron1_Value = DataSet.get(i).get(0)* WeightsFromInput[index][0];
					System.out.println("hiddenNeuron1_Value: " + hiddenNeuron1_Value);
					hiddenNeuron2_Value = DataSet.get(i).get(0)* WeightsFromInput[index][1];
					System.out.println("hiddenNeuron2_Value: " + hiddenNeuron2_Value);
					hiddenNeuron3_Value = DataSet.get(i).get(0)* WeightsFromInput[index][2];
					System.out.println("hiddenNeuron3_Value: " + hiddenNeuron3_Value);
					hiddenNeuron4_Value = DataSet.get(i).get(0)* WeightsFromInput[index][3];
					System.out.println("hiddenNeuron4_Value: " + hiddenNeuron4_Value);
					hiddenNeuron5_Value = DataSet.get(i).get(0)* WeightsFromInput[index][4];
					System.out.println("hiddenNeuron5_Value: " + hiddenNeuron5_Value);
					
					hiddenNeuron1_Sigmoid = Sigmoid(hiddenNeuron1_Value);
					System.out.println("hiddenNeuron1_Sigmoid: " + hiddenNeuron1_Sigmoid);
					hiddenNeuron2_Sigmoid = Sigmoid(hiddenNeuron2_Value);
					System.out.println("hiddenNeuron2_Sigmoid: " + hiddenNeuron2_Sigmoid);
					hiddenNeuron3_Sigmoid = Sigmoid(hiddenNeuron3_Value);
					System.out.println("hiddenNeuron3_Sigmoid: " + hiddenNeuron3_Sigmoid);
					hiddenNeuron4_Sigmoid = Sigmoid(hiddenNeuron4_Value);
					System.out.println("hiddenNeuron4_Sigmoid: " + hiddenNeuron4_Sigmoid);
					hiddenNeuron5_Sigmoid = Sigmoid(hiddenNeuron5_Value);
					System.out.println("hiddenNeuron5_Sigmoid: " + hiddenNeuron5_Sigmoid);

					
					output_Value = (hiddenNeuron1_Sigmoid * WeightsToOutput[index][0]) + (hiddenNeuron2_Sigmoid * WeightsToOutput[index][1]) +(hiddenNeuron3_Sigmoid * WeightsToOutput[index][2]) +(hiddenNeuron4_Sigmoid * WeightsToOutput[index][3]) +(hiddenNeuron5_Sigmoid * WeightsToOutput[index][4]);
					System.out.println("output_Value: " + output_Value);
					output_Sigmoid = Sigmoid(output_Value);
					System.out.println("output_Sigmoid: " + output_Sigmoid);
					System.out.println("output_Actual: " + DataSet.get(i).get(1));
					
					//calculating error from each point
					ErrorRate2[i] = DataSet.get(i).get(1) - output_Sigmoid ;
					System.out.println("ErrorRate2["+i+"]: " + ErrorRate2[i]);
					System.out.println("\n");
				}
				
				System.out.println("\n\n");
				
				// getting mean squared error for first 10 points
				System.out.println("Mean squared error for last 10 attempts with same weights= " + MeanSquaredError(ErrorRate2));
	}
	
	// returns value of Sigmoid function
	public static double Sigmoid(double V)
	{
		return (1/(1+Math.pow((Math.E), (-V))));
	}
	
	public static double MeanSquaredError(double [] err)
	{
		double MSE = 0;
		for (int i=0; i<10; i++)
		{
			MSE = MSE + err[i];
		}
		return MSE;
	}

}
