import java.util.*;

class Solution {

  static Map<String, String> map = Map.of(
      "zero", "0",
      "one", "1",
      "two", "2",
      "three", "3",
      "four", "4",
      "five", "5",
      "six", "6",
      "seven", "7",
      "eight", "8",
      "nine", "9"
  );

  public int solution(String s) {
    String result = "";

    for (int i = 0; i < s.length(); ) {
      if (Character.isDigit(s.charAt(i))) {
        result += s.charAt(i);
        i += 1;
      } else {
        for (int k = 3; k <= 5; k++) {
          if (check(s, i, k) && map.containsKey(s.substring(i, i + k))) {
            result += map.get(s.substring(i, i + k));
            i += k;
            break;
          }
        }
      }
    }

    return Integer.parseInt(result);
  }

  public boolean check(String s, int n, int k) {
    if (n + k > s.length()) {
      return false;
    }

    for (int i = n; i < n + k; i++) {
      if (Character.isDigit(s.charAt(i))) {
        return false;
      }
    }

    return true;
  }
}