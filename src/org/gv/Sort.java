package org.gv;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

/**
 * @author GV
 *
 */
public class Sort {

	static void insertionSort(Integer[] arr) {

		for (int i = 1; i < arr.length; i++) {
			int tmp = arr[i];
			int j = i - 1;
			for (; j >= 0 && arr[j] < tmp; j--) {
				arr[j + 1] = arr[j];
			}
			arr[j + 1] = tmp;
		}

		System.out.println("Sorted Array ::: " + Arrays.toString(arr));
	}

	static void insertionSort(LinkedList<Integer> arr) {

		for (int i = 1; i < arr.size(); i++) {
			int tmp = arr.get(i);
			int j = i - 1, elt;

			for (; j >= 0 && (elt = arr.get(j)) > tmp; j--) {
				arr.set(j + 1, elt);
			}
			arr.set(j + 1, tmp);
		}

		System.out.println("Sorted List ::: " + arr);
	}

	static void merge(int[] arr, int lower, int mid, int higher) {
		// Find sizes of two subarrays to be merged
		int n1 = mid - lower + 1;
		int n2 = higher - mid;

		/* Create temp arrays */
		int arrLeft[] = new int[n1];
		int arrRight[] = new int[n2];

		/* Copy data to temp arrays */
		System.arraycopy(arr, lower, arrLeft, 0, n1);
		System.arraycopy(arr, mid+1, arrRight, 0, n2);

		/* Merge the temp arrays */

		// Initial indexes of first and second subarrays
		int i = 0, j = 0;

		// Initial index of merged subarry array
		int k = lower;
		while (i < n1 && j < n2) {
			if (arrLeft[i] <= arrRight[j]) {
				arr[k] = arrLeft[i];
				i++;
			} else {
				arr[k] = arrRight[j];
				j++;
			}
			k++;
		}

		/* Copy remaining elements of Left[] if any */
		while (i < n1) {
			arr[k] = arrLeft[i];
			i++;
			k++;
		}

		/* Copy remaining elements of Right[] if any */
		while (j < n2) {
			arr[k] = arrRight[j];
			j++;
			k++;
		}
	}

	static void mergeSort(int[] arr, int lower, int higher) {

		if (lower < higher) {
			// Find the middle point
			int mid = (lower + higher) / 2;

			// Sort first and second halves
			mergeSort(arr, lower, mid);
			mergeSort(arr, mid + 1, higher);

			// Merge the sorted halves
			merge(arr, lower, mid, higher);
		}

	}
	
	static int partition(int[] arr, int p, int r) {
		int last = arr[r];
		int i = p-1;
		for(int j = p; j < r; j++) {
			if(arr[j] <= last) {
				i++;
				int tmp = arr[i];
				arr[i] = arr[j];
				arr[j] = tmp;
			}
		}
		int tmp = arr[i+1];
		arr[i+1] = arr[r];
		arr[r] = tmp;
		return i+1;
	}
	
	static void quickSort(int[] arr, int p, int r) {
		if(p < r) {
			int q = partition(arr, p, r);
			quickSort(arr, p, q-1);
			quickSort(arr, q+1, r);
		}
	}

	public static void main(String[] args) {
		int[] arr = new int[] { 9, 6, 4, 3, -11, 7, 8, 5, 0, 1, 2, -2 };
		// insertionSort(arr);
		// insertionSort(new LinkedList<>(Arrays.asList(arr)));

		quickSort(arr, 0, arr.length - 1);
		System.out.println("Sorted Array ::: " + Arrays.toString(arr));
	}
}
