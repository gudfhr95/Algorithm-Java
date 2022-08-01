import java.util.*;

class Solution {

  public int solution(int n, int k) {
    String number = getNumber(n, k);

    String[] splited = number.split("0");

    int result = 0;
    for (String s : splited) {
      if (s.equals("")) {
        continue;
      }

      long p = Long.parseLong(s);
      if (isPrime(p)) {
        result += 1;
      }
    }

    return result;
  }

  public String getNumber(int n, int k) {
    StringBuilder sb = new StringBuilder();

    while (n > 0) {
      sb.append(n % k);
      n /= k;
    }

    return sb.reverse().toString();
  }

  public boolean isPrime(long n) {
    if (n == 1) {
      return false;
    }

    for (int i = 2; i <= Math.sqrt(n); i++) {
      if (n % i == 0) {
        return false;
      }
    }

    return true;
  }
}