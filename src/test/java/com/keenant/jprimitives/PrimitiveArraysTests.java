package com.keenant.jprimitives;

import static org.junit.Assert.assertArrayEquals;

import com.keenant.jprimitives.comparator.DoubleComparator;
import com.keenant.jprimitives.comparator.IntComparator;
import java.util.Arrays;
import org.junit.Test;

public class PrimitiveArraysTests {
  private static final int[] INTS = new int[] {
      Integer.MAX_VALUE, 8, 9, 5, 0, 6, 7, 3, 4, 2, 1, Integer.MIN_VALUE
  };

  private static final double[] DOUBLES = new double[] {
      Double.MAX_VALUE, 1.0, 1.0001, 1.2, 1.09, 0.5, 2.0, 1.2, Double.MIN_VALUE,
  };

  @Test
  public void sortInts() {
    int[] asc = INTS.clone();
    int[] desc = INTS.clone();
    int[] evensFirst = INTS.clone();

    PrimitiveArrays.sort(asc, IntComparator.ASCENDING);
    PrimitiveArrays.sort(desc, IntComparator.DESCENDING);
    PrimitiveArrays.sort(evensFirst, (i1, i2) -> {
      boolean b1 = i1 % 2 == 0;
      boolean b2 = i2 % 2 == 0;

      if (b1 && !b2)
        return -1;
      else if (b1 == b2)
        return IntComparator.ASCENDING.compare(i1, i2);
      else
        return 1;
    });

    // should be sorted ascending
    assertArrayEquals(new int[] {Integer.MIN_VALUE, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, Integer.MAX_VALUE}, asc);
    assertArrayEquals(new int[] {Integer.MAX_VALUE, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0, Integer.MIN_VALUE}, desc);
    assertArrayEquals(new int[] {Integer.MIN_VALUE, 0, 2, 4, 6, 8, 1, 3, 5, 7, 9, Integer.MAX_VALUE}, evensFirst);
  }

  @Test
  public void sortDoubles() {
    double[] asc = DOUBLES.clone();
    double[] desc = DOUBLES.clone();

    PrimitiveArrays.sort(asc, DoubleComparator.ASCENDING);
    PrimitiveArrays.sort(desc, DoubleComparator.DESCENDING);

    System.out.println(Arrays.toString(asc));
    System.out.println(Arrays.toString(desc));
  }
}
