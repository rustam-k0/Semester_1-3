public class IntComparable implements Comparable<IntComparable> {
  private final Integer value;

  public IntComparable(Integer value) {
    this.value = value;
  }

  @Override
  public int compareTo(IntComparable other) {
    return this.value.compareTo(other.value);
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }
}