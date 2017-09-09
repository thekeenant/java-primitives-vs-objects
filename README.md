# jprimitives

A simple library, but more for me to experiment with `int` vs `Integer` and other primitives 
in Java. Unfortunately, at this time Java does not support primitives in generics, such as
`List<int>` like you might use in C# (a resizable `int[]`).

When performance is critical, it's important to use primitive arrays in order to minimize
cache misses due to internal fragmentation of your data. Below are comparisions of `int[]`
and `ArrayList<Integer>` sorting on the same collection of `1..n` ints/integers.

## Tests

`int[]` quicksort vs `ArrayList<Integer>` mergesort:

```text
Preparing 10 elements...
	0ms (int[])
	0ms (ArrayList<Integer>)
Preparing 100 elements...
	0ms (int[])
	0ms (ArrayList<Integer>)
Preparing 1000 elements...
	0ms (int[])
	1ms (ArrayList<Integer>)
Preparing 10000 elements...
	1ms (int[])
	6ms (ArrayList<Integer>)
Preparing 50000 elements...
	3ms (int[])
	22ms (ArrayList<Integer>)
Preparing 100000 elements...
	9ms (int[])
	31ms (ArrayList<Integer>)
Preparing 1000000 elements...
	91ms (int[])
	343ms (ArrayList<Integer>)
Preparing 10000000 elements...
	925ms (int[])
	2854ms (ArrayList<Integer>)
```

`int[]` mergesort vs `ArrayList<Integer>` mergesort:

```text
Preparing 10 elements...
	0ms (int[])
	0ms (ArrayList<Integer>)
Preparing 100 elements...
	0ms (int[])
	0ms (ArrayList<Integer>)
Preparing 1000 elements...
	0ms (int[])
	1ms (ArrayList<Integer>)
Preparing 10000 elements...
	1ms (int[])
	7ms (ArrayList<Integer>)
Preparing 50000 elements...
	6ms (int[])
	20ms (ArrayList<Integer>)
Preparing 100000 elements...
	15ms (int[])
	28ms (ArrayList<Integer>)
Preparing 1000000 elements...
	156ms (int[])
	239ms (ArrayList<Integer>)
Preparing 10000000 elements...
	1432ms (int[])
	2960ms (ArrayList<Integer>)
```

A fluent API for `Comparator` implementations was added in Java 8 that allows convenient
methods for creating a relatively complex or multi-part sorting algorithm:

```java
Comparator.nullsFirst(String.CASE_INSENSITIVE_ORDER).thenComparing(str -> {
  // ...
}, (s1, s2) -> ...).reversed();
```

Again, thanks to generics, any attempt to use this API for sorting primitives requires the usage
of the wrapper class. This could be solved by primitive comparators, lists, and more:

Examples:

* `IntComparator`: maintains a similar fluent API as `Comparator` but accepts and outputs only
`IntComparator` instances. This comparator would then be accepts as a parameter in `Arrays.sort(int[], comparator)`.
* `IntArrayList`: similar API as `ArrayList` but maintains an `int[]` as opposed to the wrapper `Integer[]`
class.