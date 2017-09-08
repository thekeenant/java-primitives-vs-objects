package com.keenant.jprimitives;

import com.keenant.jprimitives.comparator.IntComparator;

/**
 * Utilities for accessing and manipulating arrays of primitive types.
 */
public class PrimitiveArrays {
  public static void sort(int[] array) {
    sort(array, null);
  }

  /**
   * Sort a primitive int array using a provided comparator.
   * @param array the array to sort
   * @param comparator the comparator to determine the order of the array. A null value indicates
   * the elements' natural ordering should be used
   */
  public static void sort(int[] array, IntComparator comparator) {
    if (comparator == null)
      comparator = Integer::compare;

    sort(array, comparator, 0, array.length - 1);
  }

  /**
   * Recursive implementation of quick sort on an integer array.
   */
  private static void sort(int[] array, IntComparator comparator, int from, int to) {
    if (from < to) {
      int i = from;
      int j = to;
      int x = array[(i + j) / 2];

      while (i <= j) {
        while (comparator.compare(array[i], x) < 0) i++;
        while (comparator.compare(x, array[j]) < 0) j--;

        if (i <= j) {
          int tmp = array[i];
          array[i] = array[j];
          array[j] = tmp;
          i++;
          j--;
        }

      }

      sort(array, comparator, from, j);
      sort(array, comparator, i, to);
    }
  }

  public static void mergeSort(int[] array) {
    mergeSort(array, null);
  }

  public static void mergeSort(int[] array, IntComparator comparator) {
    mergeSort(array, comparator, 0, array.length - 1);
  }

  public static void mergeSort(int[] array, IntComparator comparator, int from, int to) {
    if (from == to)
      return;

    if (comparator == null)
      comparator = Integer::compare;

    int mid = (from + to) / 2;

    mergeSort(array, comparator, from, mid);
    mergeSort(array, comparator, mid + 1, to);
    merge(array, comparator, from, mid, to);
  }

  private static void merge(int[] array, IntComparator comparator, int from, int mid, int to) {
    int n = to - from + 1;
    int[] values = new int[n];

    int fromValue = from;
    int middleValue = mid + 1;
    int index = 0;

    while (fromValue <= mid && middleValue <= to) {
      if (comparator.compare(array[fromValue], array[middleValue]) < 0) {
        values[index] = array[fromValue];
        fromValue++;
      } else {
        values[index] = array[middleValue];
        middleValue++;
      }
      index++;
    }


    while (fromValue <= mid) {
      values[index] = array[fromValue];
      fromValue++;
      index++;
    }
    while (middleValue <= to) {
      values[index] = array[middleValue];
      middleValue++;
      index++;
    }

    for (index = 0; index < n; index++)
      array[from + index] = values[index];
  }
}
