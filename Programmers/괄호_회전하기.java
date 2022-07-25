import java.util.*;

class Solution {

  public int solution(String s) {
    int result = 0;
    for (int i = 0; i < s.length(); i++) {
      if (isCorrect(s)) {
        result += 1;
      }

      s = s.substring(1) + s.charAt(0);
    }

    return result;
  }

  public boolean isCorrect(String s) {
    Stack<Character> st = new Stack<>();

    for (char c : s.toCharArray()) {
      if (st.isEmpty()) {
        st.add(c);
      } else if (st.peek() == '(' && c == ')') {
        st.pop();
      } else if (st.peek() == '{' && c == '}') {
        st.pop();
      } else if (st.peek() == '[' && c == ']') {
        st.pop();
      } else {
        st.add(c);
      }
    }

    return st.isEmpty();
  }
}