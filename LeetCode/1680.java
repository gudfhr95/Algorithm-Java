class Solution {

  final int MOD = 1_000_000_000 + 7;

  public int concatenatedBinary(int n) {
    int result = 0;

    for (int i = 1; i <= n; i++) {
      String binary = Integer.toBinaryString(i);

      for (int j = 0; j < binary.length(); j++) {
        result *= 2;
        result += (binary.charAt(j) - '0');

        result %= MOD;
      }
    }

    return result;
  }
}