package com.keenant.jprimitives;

import com.keenant.jprimitives.ordering.IntOrdering;
import java.util.Arrays;
import org.junit.Test;

public class IntOrderingTests {
  @Test
  public void ordering() {
    int[] array = new int[] {1, 2, 3, 4};

    PrimitiveArrays.sort(array, IntOrdering.from(Integer::compare).nFirst(4).reverse());

    System.out.println(Arrays.toString(array));
  }
}
