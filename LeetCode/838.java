class Solution {

  public String pushDominoes(String dominoes) {
    char[] result = new char[dominoes.length()];

    for (int i = 0; i < dominoes.length(); i++) {
      result[i] = dominoes.charAt(i);

      System.out.println(i);

      if (result[i] == 'R') {
        int nextIndex = i + 1;

        while (nextIndex < dominoes.length() && dominoes.charAt(nextIndex) == '.') {
          nextIndex += 1;
        }

        if (nextIndex == i + 1) {
          continue;
        }

        if (nextIndex == dominoes.length()) {
          for (int j = i; j < nextIndex; j++) {
            result[j] = 'R';
          }

          i = nextIndex;
          continue;
        }

        if (dominoes.charAt(nextIndex) != 'L') {
          for (int j = i; j <= nextIndex; j++) {
            result[j] = 'R';
          }

          i = nextIndex - 1;
        } else {
          int length = nextIndex - i + 1;
          int mid = (nextIndex + i) / 2;

          if (length % 2 == 1) {
            for (int j = i; j < mid; j++) {
              result[j] = 'R';
            }

            result[mid] = '.';

            for (int j = mid + 1; j <= nextIndex; j++) {
              result[j] = 'L';
            }
          } else {
            for (int j = i; j <= mid; j++) {
              result[j] = 'R';
            }
            for (int j = mid + 1; j <= nextIndex; j++) {
              result[j] = 'L';
            }
          }

          i = nextIndex;
        }
      } else if (result[i] == 'L') {
        int nextIndex = i - 1;

        while (nextIndex >= 0 && dominoes.charAt(nextIndex) == '.') {
          nextIndex -= 1;
        }

        for (int j = i; j > nextIndex; j--) {
          result[j] = 'L';
        }
      }
    }

    return new String(result);
  }
}