class Solution {

  public boolean canConstruct(String ransomNote, String magazine) {
    char[] magazineChar = new char[26];

    for (char c : magazine.toCharArray()) {
      magazineChar[c - 'a'] += 1;
    }

    for (char c : ransomNote.toCharArray()) {
      if (magazineChar[c - 'a'] == 0) {
        return false;
      }

      magazineChar[c - 'a'] -= 1;
    }

    return true;
  }
}