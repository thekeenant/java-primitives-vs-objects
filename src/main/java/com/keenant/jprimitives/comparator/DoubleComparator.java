package com.keenant.jprimitives.comparator;

import java.util.Comparator;

public interface DoubleComparator extends Comparator<Double> {
  DoubleComparator ASCENDING = Double::compare;
  DoubleComparator DESCENDING = (d1, d2) -> d1 < d2 ? 1 : (d1 == d2 ? 0 : -1);

  static DoubleComparator reverse(DoubleComparator comparator) {
    return (i1, i2) -> -comparator.compare(i1, i2);
  }

  default int compare(Double d1, Double d2) {
    return compare(d1.doubleValue(), d2.doubleValue());
  }

  int compare(double i1, double i2);
}
