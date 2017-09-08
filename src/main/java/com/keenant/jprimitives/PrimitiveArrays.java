package com.keenant.jprimitives;

import com.keenant.jprimitives.comparator.IntComparator;

/**
 * Utilities for accessing and manipulating arrays of primitive types.
 */
public class PrimitiveArrays {
  /**
   * Sort a primitive int array using a provided comparator.
   * @param array the array to sort
   * @param comparator the comparator to determine the order of the array. A null value indicates
   * the elements' natural ordering should be used
   */
  public static void sort(int[] array, IntComparator comparator) {
    sort(array, comparator == null ? Integer::compare : comparator, 0, array.length - 1);
  }

  /**
   * Recursive implementation of quick sort on an integer array.
   */
  private static void sort(int[] array, IntComparator comparator, int start, int end) {
    if (start < end) {
      int i = start;
      int j = end;
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

      sort(array, comparator, start, j);
      sort(array, comparator, i, end);
    }
  }
}
