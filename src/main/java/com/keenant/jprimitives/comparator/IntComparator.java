package com.keenant.jprimitives.comparator;

import java.util.Comparator;
import java.util.Objects;
import java.util.function.IntFunction;

/**
 * Primitive int comparator.
 */
public interface IntComparator extends Comparator<Integer> {
  @Deprecated
  default int compare(Integer i1, Integer i2) {
    return compare(i1.intValue(), i2.intValue());
  }

  default IntComparator reversed() {
    return (i1, i2) -> this.compare(i2, i1);
  }

  default IntComparator thenComparing(IntComparator comparator) {
    return (i1, i2) -> {
      int result = this.compare(i1, i2);
      return result == 0 ? comparator.compare(i1, i2) : result;
    };
  }

  default <R> IntComparator thenComparing(IntFunction<? extends R> transform, Comparator<? super R> comparator) {
    return thenComparing(comparing(transform, comparator));
  }

  static <R> IntComparator comparing(IntFunction<? extends R> transform, Comparator<? super R> comparator) {
    Objects.requireNonNull(transform);
    Objects.requireNonNull(comparator);

    return (i1, i2) -> {
      R r1 = transform.apply(i1);
      R r2 = transform.apply(i2);

      return comparator.compare(r1, r2);
    };
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
