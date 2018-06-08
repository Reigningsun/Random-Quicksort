package hw5;

import java.util.Date;
import java.util.Random;


public class Main {

	
	public static int[] swap(int[] array, int i, int j){										// Exchanges the values of i with the value of j
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
		return array;
	}
	
	
	
	public static int partition(int[] toPartition, int lo, int hi){
		int val = toPartition[lo];
		int h = lo;
		
		for(int k = lo; k < hi + 1; k++){ 
			if (toPartition[k] < val){
				h = h + 1;
				toPartition = swap(toPartition, h, k);
			}
		}
		toPartition = swap(toPartition, lo, h);
		return h;
	}
	
	
	
	public static int random_partition(int[] toPartition, int lo, int hi){
		Random rand = new Random();																// Create random number
		int k = rand.nextInt((hi - lo) + 1) + lo;
		
		toPartition = swap (toPartition, lo, k);
		return partition(toPartition, lo, hi);
	}
	
	
	
	public static int[] random_quicksort(int[] toPartition, int lo, int hi){
		if (lo < hi){
			int p = random_partition(toPartition, lo, hi);										// Selects a random pivot 
			random_quicksort(toPartition, lo, p - 1);											// Sorts the lower half of the partition
			random_quicksort(toPartition, p + 1, hi); 											// Sorts the higher half of the partition
		}
		return toPartition;
	}
	
	
	
	public static int random_select (int[] array, int i, int j, int median){					// Returns the index of the median value of an array in linear time
		if (i < j){
			int p = random_partition(array, i, j);
			if (median == p){
				return p;
			}
			int p2 = -1;
			if (median < p){
				p2 = random_select(array, i, p - 1, median);
			} else {
				p2 = random_select(array, p + 1, j, median);
			}
			if (median == p2){
				return p2;
			}
		}
		return 0;
	}
	
	
	//** Testing helpers ** 
	public static void isSorted (int[] array){													// Checks that the array is actually sorted
		boolean sorted = true;

		for (int i = 0; i < array.length - 1; i++) {
		    if (array[i] > array[i+1]) {
		        sorted = false;
		        break;
		    }
		}
		System.out.println("Array is sorted: " + sorted);
	}
	
	
	
	public static void contentsOfArray (int[] array){											// Prints out the contents of the array for inspection
		for (int i = 0; i < array.length; i++){
			System.out.print(array[i] + " ");
			if (i % 10 == 0){
				System.out.println(" ");
			}
		}
		System.out.println(" ");
	}
	
	
	public static boolean medianCorrect(int[] array, int median){
		array = random_quicksort(array, 0, array.length - 1);
		int arraysTrueMedian = array[(array.length - 1) / 2];
		if (arraysTrueMedian == median){
			return true;
		}
		return false;
	}
	
	
	
	public static void main(String[] args) {
		
	//** Quick Sort an array
		int array[] = new int [100000];
		Random rand = new Random();

		for (int i = 0; i < array.length; i++){													// Fill the array with random int's between 10^6 and -10^6
			array[i] = rand.nextInt((1000000 - -1000000) + 1) + -1000000;
		}

		
		//contentsOfArray(array); 																// Outputs unsorted contents
		isSorted(array);
		
		System.out.println(" ");
		
		
		long startTime = System.currentTimeMillis();											// Starting timer
		array = random_quicksort(array, 0, array.length - 1);
		long elapsedTime = (new Date()).getTime() - startTime;									// Calculate time of search
		System.out.println("Time to sort 1st time: " + elapsedTime + " milliseconds");			// Print out the time taken
		isSorted(array);

		
		System.out.println(" ");
		
		
		//contentsOfArray(array);																// Outputs sorted contents
		
		
		startTime = System.currentTimeMillis();													// Starting timer
		array = random_quicksort(array, 0, array.length - 1);
		elapsedTime = (new Date()).getTime() - startTime;										// Calculate time of search
		System.out.println("Time to sort 2nd time: " + elapsedTime + " milliseconds");			// Print out the time taken
		isSorted(array);
		
		
		System.out.println(" ");
		
		
		
	//** Select the Median 
		int array2[] = new int [100000];
		Random rand2 = new Random();

		for (int i = 0; i < array.length; i++){													// Fill the array with random int's between 10^6 and -10^6
			array2[i] = rand2.nextInt((1000000 - -1000000) + 1) + -1000000;
		}
		
		int k = (array2.length - 1) / 2;
		int medianInd = random_select(array2, 0, array2.length - 1, k);
		int median = array2[medianInd];
		System.out.println(median);
		System.out.println("This is the correct median: " + medianCorrect(array2, median));
		
		
	}

}
