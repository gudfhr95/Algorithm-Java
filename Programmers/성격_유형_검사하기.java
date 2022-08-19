import java.util.*;

class Solution {

  Map<Character, Integer> map = new HashMap<>();

  public String solution(String[] survey, int[] choices) {
    map.put('R', 0);
    map.put('T', 0);
    map.put('C', 0);
    map.put('F', 0);
    map.put('J', 0);
    map.put('M', 0);
    map.put('A', 0);
    map.put('N', 0);

    for (int i = 0; i < survey.length; i++) {
      char first = survey[i].charAt(0);
      char second = survey[i].charAt(1);

      int choice = choices[i] - 4;
      if (choice < 0) {
        int score = map.get(first);
        score += Math.abs(choice);

        map.put(first, score);
      } else if (choice > 0) {
        int score = map.get(second);
        score += Math.abs(choice);

        map.put(second, score);
      }
    }

    String answer = "";
    if (map.get('R') >= map.get('T')) {
      answer += 'R';
    } else {
      answer += 'T';
    }

    if (map.get('C') >= map.get('F')) {
      answer += 'C';
    } else {
      answer += 'F';
    }

    if (map.get('J') >= map.get('M')) {
      answer += 'J';
    } else {
      answer += 'M';
    }

    if (map.get('A') >= map.get('N')) {
      answer += 'A';
    } else {
      answer += 'N';
    }

    return answer;
  }
}