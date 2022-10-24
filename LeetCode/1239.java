class Solution {

  int result = 0;

  public int maxLength(List<String> arr) {
    maxLengthRec(0, new HashSet<Character>(), arr);

    return result;
  }

  public void maxLengthRec(int n, Set<Character> set, List<String> arr) {
    if (n == arr.size()) {
      result = Math.max(result, set.size());
      return;
    }

    String cur = arr.get(n);
    boolean contains = false;
    for (char c : cur.toCharArray()) {
      if (set.contains(c)) {
        contains = true;
        break;
      }
    }

    maxLengthRec(n + 1, set, arr);

    if (!contains) {
      Set<Character> newSet = new HashSet<>(set);
      for (char c : cur.toCharArray()) {
        if (newSet.contains(c)) {
          return;
        }

        newSet.add(c);
      }

      maxLengthRec(n + 1, newSet, arr);
    }
  }
}