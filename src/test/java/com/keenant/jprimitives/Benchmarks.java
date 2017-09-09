package com.keenant.jprimitives;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.junit.Test;

public class Benchmarks {
  @Test
  public void sortInts() {
    // Load the class used to prevent static instantiation as part of timings
    {
      List<Integer> x = new ArrayList<>();
      Collections.sort(x);
      PrimitiveArrays.sort(new int[] {1});
    }

    int[] sizes = {10, 100, 1_000, 10_000, 50_000, 100_000, 1_000_000, 10_000_000};

    for (int size : sizes) {
      System.out.println("Preparing " + size + " elements...");
      Random rand = new Random();
      List<Integer> list = new ArrayList<>(size);
      for (int i = 0; i < size; i++)
        list.add(i + 1);
      Collections.shuffle(list, rand);

      int[] array = new int[size];
      for (int i = 0; i < array.length; i++)
        array[i] = list.get(i);

      {
        int[] clone = array.clone();

        long start = System.nanoTime();
        PrimitiveArrays.mergeSort(clone);
        long end = System.nanoTime();

        long millis = TimeUnit.MILLISECONDS.convert(end - start, TimeUnit.NANOSECONDS);
        System.out.println("\t" + millis + "ms (int[])");
      }

      {
        List<Integer> clone = new ArrayList<>(list);

        long start = System.nanoTime();
        Collections.sort(clone);
        long end = System.nanoTime();

        long millis = TimeUnit.MILLISECONDS.convert(end - start, TimeUnit.NANOSECONDS);
        System.out.println("\t" + millis + "ms (ArrayList<Integer>)");
      }
    }
  }
}
