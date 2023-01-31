package INSERTIONSORTALGOTITHM;

import java.util.Arrays;
import java.util.Random;


public final class App {
   //method takes an integer array as input and returns the sorted array.
    public static int[] sortArrayUsingInsertionSort(int[] nums) {
        //loop used to select keys
        for (int i = 1; i < nums.length; i++) {
            int key = nums[i];
            int j = i - 1;
            //loop to shift elements to the right
            while (j >= 0 && nums[j] > key) {
                nums[j + 1] = nums[j];
                j--;
            }
            nums[j + 1] = key;
        }
        return nums;
    }

    public static int[] sortArrayUsingSelectionSort(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            int smallestElementIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (nums[j] < nums[smallestElementIndex]) {
                    smallestElementIndex = j;
                }
            }
            // exchange/swap
            int minValueInUnsortedArray = nums[smallestElementIndex];
            nums[smallestElementIndex] = nums[i];
            nums[i] = minValueInUnsortedArray;
        }
        return nums;
    }

    public static int[] sortArrayUsingBubbleSort(int[] nums) {
        int temp;
        //for loop that runs through length of array
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = 0; j < nums.length - i - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                }
            }
        }
        return nums;
    }

    public static int[] sortArrayUsingMergeSort(int[] nums) {
        //main method that sorts the input array
        if (nums.length < 2) {
        return nums;
    }
        int mid = nums.length / 2;//calculates the midpoint of the input array.
        int[] left = Arrays.copyOfRange(nums, 0, mid);
        int[] right = Arrays.copyOfRange(nums, mid, nums.length);// create two subarrays

        left = sortArrayUsingMergeSort(left);
        right = sortArrayUsingMergeSort(right);

        return merge(left, right);//merges the sorted subarrays
    }

    private static int[] merge(int[] left, int[] right) {
        //helper method that takes two sorted arrays,and merges them into a single sorted array.
        int[] result = new int[left.length + right.length];
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                result[k++] = left[i++];
            } else {
                result[k++] = right[j++];
            }
        }

        while (i < left.length) {
            result[k++] = left[i++];
        }

        while (j < right.length) {
            result[k++] = right[j++];
        }

        return result;
    }

    //method that takes an integer array as input and returns the sorted array
    public static int[] sortArrayUsingQuickSort(int[] nums) {
        quickSort(nums, 0, nums.length-1);
        return nums;
    }
    //method that sorts the given array by dividing it into two parts and sorting each part recursively
    private static void quickSort(int[] nums, int start, int end) {
        while (start < end) {
            int pivotIndex = partition(nums, start, end);
            if (pivotIndex - start < end - pivotIndex){
            quickSort(nums, start, pivotIndex - 1);
                start = pivotIndex + 1;
            } else {
                quickSort(nums, pivotIndex + 1, end);
                end = pivotIndex - 1;
            }
        }
    }

    //method that selects a pivot element from the array and rearranges the elements
    private static int partition(int[] nums, int start, int end) {
        int pivot = nums[end];
        int i = start - 1;
        for (int j = start; j < end; j++) {
            if (nums[j] <= pivot) {
                i++;
                swap(nums, i, j);
            }
        }
        swap(nums, i + 1, end);
        return i + 1;
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }



    public static void main(String[] args) {
        //Create an array of non-negative integers with data volumes of 4000, 8000, 16000 and 32000 elements
        int[] array4000 = new int[4000];
        int[] array8000 = new int[8000];
        int[] array16000 = new int[16000];
        int[] array32000 = new int[32000];
        int[][] arraysDataVolumes = {array4000};// array8000, array16000, array32000};
        String[] sortingMethods = {"Insertion Sort", "Selection Sort", "Bubble Sort", "Merge Sort", "Quick Sort"};
        long[][] sortingTimes = new long[sortingMethods.length][array4000.length];
        long[][] sortingTimes2 = new long[sortingMethods.length][array8000.length];
        long[][] sortingTimes3 = new long[sortingMethods.length][array16000.length];
        long[][] sortingTimes4 = new long[sortingMethods.length][array32000.length];
        long[] avgTimeList = new long[sortingMethods.length];


        Random random = new Random();
        /*for (int i = 0; i < 4000; i++) {
            array4000[i] = random.nextInt(100);
        }

        for (int i = 0; i < 8000; i++) {
            array8000[i] = random.nextInt(100);
        }

        for (int i = 0; i < 6000; i++) {
            array16000[i] = random.nextInt(100);
        }

        for (int i = 0; i < 32000; i++) {
            array32000[i] = random.nextInt(100);
        }*/

        for (int i = 0; i < arraysDataVolumes.length; i++) {
            int[] nums = arraysDataVolumes[i];
            for (int j = 0; j < nums.length; j++) {
                nums[j] = random.nextInt();
            }

            for (int j = 0; j < sortingMethods.length; j++) {
                long startTime = System.currentTimeMillis();
                //int[] sortedNums = null;
                switch (j) {
                    case 0:
                        sortArrayUsingInsertionSort(nums);
                        break;
                    case 1:
                        sortArrayUsingSelectionSort(nums);
                        break;
                    case 2:
                        sortArrayUsingBubbleSort(nums);
                        break;
                    case 3:
                        sortArrayUsingMergeSort(nums);
                        break;
                    case 4:
                        sortArrayUsingQuickSort(nums);
                        break;
                }
                long endTime = System.currentTimeMillis();
                long elapsedTime = endTime - startTime;
                sortingTimes[j][i] = elapsedTime;
                sortingTimes2[j][i] = elapsedTime;
                sortingTimes3[j][i] = elapsedTime;
                sortingTimes4[j][i] = elapsedTime;
            }
        }
        // calculate time for each sorting method
        for (int i = 0; i < arraysDataVolumes.length; i++) {
            long time = 0;
            for (int j = 0; j < sortingMethods.length; j++) {
                time += sortingTimes[j][i];
                time += sortingTimes2[j][i];
                time += sortingTimes3[j][i];
                time += sortingTimes4[j][i];

            }
            avgTimeList[i] = time;
        }

        // print results
        System.out.println("Sorting Method\tAvg. Time (ms)\tData Size");
        for (int i = 0; i < sortingMethods.length; i++) {
            System.out.println(sortingMethods[i] + "\t\t" + avgTimeList[i] + "\t\t" + array4000.length);
            System.out.println(sortingMethods[i] + "\t\t" + avgTimeList[i] + "\t\t" + array8000.length);
            System.out.println(sortingMethods[i] + "\t\t" + avgTimeList[i] + "\t\t" + array16000.length);
            System.out.println(sortingMethods[i] + "\t\t" + avgTimeList[i] + "\t\t" + array32000.length);


        }
    }
}
