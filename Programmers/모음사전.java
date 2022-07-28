import java.util.*;

class Solution {

  final char[] alphabet = {'A', 'E', 'I', 'O', 'U'};

  TreeSet<String> ts = new TreeSet<>();

  public int solution(String word) {
    for (char c : alphabet) {
      generate(1, "" + c);
    }

    List<String> list = new ArrayList<>(ts);
    return list.indexOf(word) + 1;
  }

  public void generate(int n, String word) {
    if (n == 6) {
      return;
    }

    ts.add(word);

    for (char c : alphabet) {
      generate(n + 1, word + c);
    }
  }
}