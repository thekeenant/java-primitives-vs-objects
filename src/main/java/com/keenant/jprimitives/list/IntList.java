package com.keenant.jprimitives.list;

public class IntList implements PrimitiveList {
  private static final int DEFAULT_CAPACITY = 10;
  private static final int[] EMPTY = new int[0];

  private int[] elementData;
  private int size;

  public IntList() {
    this(DEFAULT_CAPACITY);
  }

  public IntList(int initialSize) {
    if (initialSize > 0) {
      this.elementData = new int[initialSize];
    } else if (initialSize == 0) {
      this.elementData = EMPTY;
    } else {
      throw new IllegalArgumentException("Illegal capcity: " + initialSize);
    }
  }

  @Override
  public int size() {
    return this.size;
  }

  @Override
  public boolean isEmpty() {
    return this.size == 0;
  }
}
