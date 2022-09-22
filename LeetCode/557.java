class Solution {

  public String reverseWords(String s) {
    String result = "";

    String[] splited = s.split(" ");
    for (int i = 0; i < splited.length; i++) {
      result += reverse(splited[i]);

      if (i < splited.length - 1) {
        result += " ";
      }
    }

    return result;
  }

  public String reverse(String s) {
    Stack<Character> stack = new Stack<>();
    for (char c : s.toCharArray()) {
      stack.push(c);
    }

    String result = "";
    while (!stack.isEmpty()) {
      result += stack.pop();
    }

    return result;
  }
}