package com.keenant.jprimitives;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class PrimitiveArraysTests {
  private static final int[] INTS = new int[] {
      Integer.MAX_VALUE, 8, 9, 5, 0, 6, 7, 3, 4, 2, 1, Integer.MIN_VALUE
  };

  @Test
  public void sortInts() {
    int[] asc = INTS.clone();
    int[] desc = INTS.clone();
    int[] evensFirst = INTS.clone();

    // should be sorted ascending
    PrimitiveArrays.sort(asc, Integer::compare);
    assertArrayEquals(new int[] {Integer.MIN_VALUE, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, Integer.MAX_VALUE}, asc);

    // descending
    PrimitiveArrays.sort(desc, (i1, i2) -> i1 < i2 ? 1 : (i1 == i2 ? 0 : -1));
    assertArrayEquals(new int[] {Integer.MAX_VALUE, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0, Integer.MIN_VALUE}, desc);

    // evens (plus 0), then odds, ascending
    PrimitiveArrays.sort(evensFirst, (i1, i2) -> {
      boolean b1 = i1 % 2 == 0;
      boolean b2 = i2 % 2 == 0;

      if (b1 && !b2)
        return -1;
      else if (b1 == b2)
        return Integer.compare(i1, i2);
      else
        return 1;
    });
    assertArrayEquals(new int[] {Integer.MIN_VALUE, 0, 2, 4, 6, 8, 1, 3, 5, 7, 9, Integer.MAX_VALUE}, evensFirst);
  }
}
