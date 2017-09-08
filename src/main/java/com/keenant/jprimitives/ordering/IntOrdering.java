package com.keenant.jprimitives.ordering;

import com.keenant.jprimitives.comparator.IntComparator;
import java.util.LinkedList;
import java.util.List;

public class IntOrdering implements IntComparator {
  private static final IntComparator EVENS_FIRST = new MultiplesFirstComparator(2);

  private final IntComparator comparator;
  private boolean reversed;

  private IntOrdering() {
    this(new LinkedList<>());
  }

  private IntOrdering(List<IntComparator> comparators) {
    this.comparators = comparators;
  }

  public static IntOrdering from(IntComparator comparator) {
    IntOrdering ordering = new IntOrdering();
    ordering.comparators.add(comparator);
    return ordering;
  }

  public IntOrdering compound(IntComparator comparator) {
    IntOrdering ordering = new IntOrdering();
    comparators.add(comparator);
    return this;
  }

  public IntOrdering nFirst(int n) {
    comparators.add(0, new NFirstComparator(n));
    return this;
  }

  public IntOrdering evensFirst() {
    return multiplesFirst(2);
  }

  public IntOrdering evensLast() {
    return multiplesLast(2);
  }

  public IntOrdering multiplesFirst(int n) {
    IntComparator comparator;
    if (n == 2)
      comparator = EVENS_FIRST;
    else
      comparator = new MultiplesFirstComparator(n);

    comparators.add(0, comparator);
    return this;
  }

  public IntOrdering multiplesLast(int n) {
    IntComparator comparator;
    if (n == 2)
      comparator = EVENS_FIRST;
    else
      comparator = new MultiplesFirstComparator(n);

    comparators.add(0, (i1, i2) -> comparator.compare(i2, i1));
    return this;
  }

  public IntOrdering reverse() {
    reversed = true;
    return this;
  }

  @Override
  public int compare(int i1, int i2) {
    for (IntComparator comparator : comparators) {
      int result;
      if (reversed) {
        result = comparator.compare(i2, i1);
      } else {
        result = comparator.compare(i1, i2);
      }

      if (result != 0)
        return result;
    }
    return 0;
  }

  private static class NFirstComparator implements IntComparator {
    private int n;

    public NFirstComparator(int n) {
      this.n = n;
    }

    @Override
    public int compare(int i1, int i2) {
      if (i1 == n && i2 != n)
        return -1;
      else if (i1 == n == (i2 == n))
        return 0;
      else
        return 1;
    }
  }

  private static class MultiplesFirstComparator implements IntComparator {
    private int multiple;

    public MultiplesFirstComparator(int multiple) {
      this.multiple = multiple;
    }

    @Override
    public int compare(int i1, int i2) {
      boolean b1 = i1 % multiple == 0;
      boolean b2 = i2 % multiple == 0;

      if (b1 && !b2)
        return -1;
      else if (b1 == b2)
        return 0;
      else
        return 1;
    }
  }
}
