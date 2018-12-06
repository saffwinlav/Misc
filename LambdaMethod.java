/**
 * LambdaMethod.java
 * apportion seats in the House of Rep by the method of major
fractions using the lambda method
 * @author saffywinton
 * @version03152018
 */

import java.util.Scanner;
//Collects data on house seats and population and creates arrays of data
public class LambdaMethod {

	public static void main(String[] args) {
		Scanner kbd = new Scanner(System.in);
		System.out.println("Enter number of house seats:");
		double housesize = kbd.nextDouble();
		System.out.println("Enter number of states:");
		double numberofstates = kbd.nextDouble();
		double [] popstate = new double [(int)numberofstates];
		for (int i = 0; i<numberofstates; i++) {
			System.out.println("Enter the population of the " + (i+1) + " state:");
			popstate[i] = kbd.nextDouble();
		}
		double poptotal = getPopTotal(numberofstates, popstate);
		double [] statequota = getStateQuota(numberofstates, popstate, poptotal, housesize);
		double [] lambdavalues = {(1/(double)numberofstates),(1/(double)numberofstates)};
		lambdavalues = setLowLambda(numberofstates,statequota,lambdavalues,housesize);
		lambdavalues = setHighLambda(numberofstates,statequota,lambdavalues,housesize);
		System.out.printf("The range of lambda values is (%.3f, %.3f)", lambdavalues[0],lambdavalues[1] );
		System.out.println("\nThe apportionment for each state is: ");
				printArray(getLowApport(numberofstates, statequota, lambdavalues));
		kbd.close();
	}
	//gets total population based on population of each state
	public static double getPopTotal (double numberofstates, double [] popstate) {
		double poptotal = 0;
		for (int i = 0; i<numberofstates; i++) {
			poptotal += popstate[i];
		}
		return poptotal;
	}
	//gets individual state quota
	public static double [] getStateQuota (double numberofstates, double [] popstate, double poptotal, double housesize) {
		double [] statequota = new double [(int) numberofstates];
		for (int i = 0; i<numberofstates; i++) { 
			statequota[i]= (((double)popstate[i]/(double)poptotal)*housesize);
			}
		return statequota;
	}
	//gets low end apportion values
	public static double [] getLowApport (double numberofstates, double [] statequota, double [] lambdavalues) {
		double [] apport = new double [(int)numberofstates];
		for (int i = 0; i<numberofstates; i++) { 
			apport[i] = Math.floor((statequota[i]/lambdavalues[0])+.5);
		}
		return apport;
	}
	//gets high end apportion values
	public static double [] getHighApport (double numberofstates, double [] statequota, double [] lambdavalues) {
		double [] apport = new double [(int)numberofstates];
		for (int i = 0; i<numberofstates; i++) { 
			apport[i] = Math.floor((statequota[i]/lambdavalues[1])+.5);
		}
		return apport;
	}
	//increases low end lambda value
	public static double [] increaseLowLambda(double [] lambdavalues) {
		lambdavalues[0]=lambdavalues[0]+.1;
		return lambdavalues;
	}
	//increases high end lambda value
	public static double [] increaseHighLambda(double [] lambdavalues) {
		lambdavalues[1]=lambdavalues[1]+.1;
		return lambdavalues;
	}
	//sums apportionment for all states
	public static double sumApport(double [] apport) {
		double sum=0.0;
		for (int i = 0; i<apport.length; i++){
			sum += apport[i];
		}
		return sum;
	}
	//increments lambda values to find the range of possible values to apportion seats
	public static double [] setLowLambda (double numberofstates,double [] statequota, double [] lambdavalues, double housesize) {
	double [] LowApport = getLowApport(numberofstates, statequota, lambdavalues);
	if (sumApport(LowApport) > housesize) {
		lambdavalues = increaseLowLambda(lambdavalues);
	setLowLambda(numberofstates,statequota,lambdavalues,housesize);
	} else {
		lambdavalues[1] = lambdavalues [0];
	}
	return lambdavalues;
	
}
	public static double [] setHighLambda (double numberofstates,double [] statequota, double [] lambdavalues, double housesize) {
		double [] getHighApport = getHighApport(numberofstates, statequota, lambdavalues);
		if (sumApport(getHighApport) == housesize) {
			lambdavalues = increaseHighLambda(lambdavalues);
			setHighLambda(numberofstates,statequota,lambdavalues,housesize);
			} 
		return lambdavalues;
	}
	//prints arrays, used for debugging
	public static void printArray(double [] array) {
		for (int i =0; i<array.length; i++) {
			System.out.println(array[i]);
		}
	}
}
