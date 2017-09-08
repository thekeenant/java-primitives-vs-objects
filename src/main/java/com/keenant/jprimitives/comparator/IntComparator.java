package com.keenant.jprimitives.comparator;

import java.util.Comparator;

public interface IntComparator extends Comparator<Integer> {
  IntComparator ASCENDING = Integer::compare;
  IntComparator DESCENDING = (i1, i2) -> i1 < i2 ? 1 : (i1 == i2 ? 0 : -1);

  static IntComparator reverse(IntComparator comparator) {
    return (i1, i2) -> -comparator.compare(i1, i2);
  }

  int compare(int i1, int i2);

  default int compare(Integer i1, Integer i2) {
    return compare(i1.intValue(), i2.intValue());
  }
}
