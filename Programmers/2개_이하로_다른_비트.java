class Solution {

  public long[] solution(long[] numbers) {
    long[] result = new long[numbers.length];

    int index = 0;
    for (int i = 0; i < numbers.length; i++) {
      result[index++] = f(numbers[i]);
    }

    return result;
  }

  public long f(long x) {
    if (x % 2 == 0) {
      return x + 1;
    }

    long temp = x;
    int index = 0;
    while (temp > 0) {
      if ((temp & 1) == 0) {
        break;
      }

      temp >>= 1;
      index += 1;
    }

    x |= (1L << index);
    x &= ~(1L << index - 1);

    return x;
  }
}