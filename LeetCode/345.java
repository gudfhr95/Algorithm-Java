class Solution {

  public String reverseVowels(String s) {
    char[] c = s.toCharArray();

    int left = 0, right = c.length - 1;
    while (left < right) {
      if (!isVowel(c[left])) {
        left += 1;
      } else if (!isVowel(c[right])) {
        right -= 1;
      } else {
        char temp = c[left];
        c[left] = c[right];
        c[right] = temp;

        left += 1;
        right -= 1;
      }
    }

    return new String(c);
  }

  public boolean isVowel(char c) {
    return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'A' || c == 'E'
        || c == 'I' || c == 'O' || c == 'U';
  }
}