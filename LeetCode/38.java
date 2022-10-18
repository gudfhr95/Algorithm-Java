class Solution {

  public String countAndSay(int n) {
    if (n == 1) {
      return "1";
    }

    String s = countAndSay(n - 1);

    String result = "";
    char prev = s.charAt(0);
    int count = 1;
    for (int i = 1; i < s.length(); i++) {
      if (s.charAt(i) == prev) {
        count += 1;
      } else {
        result += Integer.toString(count);
        result += prev;

        prev = s.charAt(i);
        count = 1;
      }
    }

    result += Integer.toString(count);
    result += prev;

    return result;
  }
}