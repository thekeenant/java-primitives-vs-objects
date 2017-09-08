package com.keenant.jprimitives;

import com.keenant.jprimitives.comparator.DoubleComparator;
import com.keenant.jprimitives.comparator.IntComparator;

public class PrimitiveArrays {
  public static void sort(int[] arr, IntComparator comparator) {
    sort(arr, comparator, 0, arr.length - 1);
  }

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

  public static void sort(double[] arr, DoubleComparator comparator) {
    sort(arr, comparator, 0, arr.length - 1);
  }

  private static void sort(double[] array, DoubleComparator comparator, int start, int end) {
    if (start < end) {
      int i = start;
      int j = end;
      double x = array[(i + j) / 2];

      while (i <= j) {
        while (comparator.compare(array[i], x) < 0) i++;
        while (comparator.compare(x, array[j]) < 0) j--;

        if (i <= j) {
          double tmp = array[i];
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
