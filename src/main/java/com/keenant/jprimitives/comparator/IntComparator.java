package com.keenant.jprimitives.comparator;

import java.util.Comparator;

/**
 * Primitive int comparator.
 */
public interface IntComparator extends Comparator<Integer> {
  default int compare(Integer i1, Integer i2) {
    return compare(i1.intValue(), i2.intValue());
  }

  /**
   * Compares its two arguments for order. Returns a negative integer, zero, or a positive
   * integer as the first argument is less than, equal to, or greater than the second.
   * @param i1 the first element to be compared
   * @param i2 the second element to be compared
   * @return a negative integer, zero, or a positive integer as the first argument is less
   * than, equal to, or greater than the second
   */
  int compare(int i1, int i2);
}
