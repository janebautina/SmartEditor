package application;

import java.util.Random;
import static org.junit.Assert.assertEquals;

public class QuickSort {

	public static void main(String[] args) {
		int a []  = {6, 8, 1, 4, 60, 5};
		quickSort(a, 0, a.length - 1);
		for (int i: a){
			System.out.print(i + " ");
		}
		System.out.println();
        assertEquals(binarySearch(a, 5, 0, a.length - 1), 2);
	}

	private static int binarySearch(int[] a, int elem, int start, int end) throws NegativeArraySizeException {
		if (start > end)return -1;
		int mid = (end - start)/2; 
		if (a[mid] == elem) {
		     return mid;
		}
		else if(a[mid] < elem){
			return binarySearch(a, elem, mid + 1, end);
		}else {
			return binarySearch(a, elem, start, mid - 1);
		}
	}

	private static void quickSort(int[] a, int start, int end) {
		Random random = new Random();
		int pivotNum = random.nextInt(end - start + 1 ) + start;
		int pivot = a[pivotNum];
		System.out.println("Pivot " + pivot);
		int i = start; 
		int j = end;
		while ( i <= j ) {
			while ((a[i] < pivot) && (i < end)) i++;
			while((a[j] > pivot)&&(j > start)) j--;
			if(i <= j) {
				int temp = a[i];
				a[i] = a[j];
				a[j] = temp;
				i++;
				j--;
			}
		}
		if (j > start) quickSort(a, start, j); 
		if (i < end) quickSort(a, i, end);
		
	}
}
