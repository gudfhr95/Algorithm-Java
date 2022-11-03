class Solution {

  public int longestPalindrome(String[] words) {
    int result = 0;

    Map<String, Integer> map = new HashMap<>();
    for (String word : words) {
      String reversed = "";
      reversed += word.charAt(1);
      reversed += word.charAt(0);

      if (map.getOrDefault(reversed, 0) > 0) {
        map.put(reversed, map.get(reversed) - 1);

        result += 4;
      } else {
        map.put(word, map.getOrDefault(word, 0) + 1);
      }
    }

    for (String key : map.keySet()) {
      if (map.get(key) == 0) {
        continue;
      }

      if (key.charAt(0) == key.charAt(1)) {
        result += 2;
        break;
      }
    }

    return result;
  }
}