class Solution {

  List<Integer>[] adj = new ArrayList[26];
  int[] numbers = new int[26];

  public boolean equationsPossible(String[] equations) {
    for (int i = 0; i < 26; i++) {
      adj[i] = new ArrayList<>();
      numbers[i] = -1;
    }

    for (String equation : equations) {
      if (equation.charAt(1) == '!') {
        continue;
      }

      int v1 = equation.charAt(0) - 'a';
      int v2 = equation.charAt(3) - 'a';

      adj[v1].add(v2);
      adj[v2].add(v1);
    }

    int num = 0;
    for (int i = 0; i < 26; i++) {
      if (numbers[i] != -1) {
        continue;
      }

      dfs(i, num++);
    }

    for (String equation : equations) {
      if (equation.charAt(1) == '=') {
        continue;
      }

      int v1 = equation.charAt(0) - 'a';
      int v2 = equation.charAt(3) - 'a';

      if (numbers[v1] == numbers[v2]) {
        return false;
      }
    }

    return true;
  }

  public void dfs(int n, int num) {
    numbers[n] = num;

    for (int next : adj[n]) {
      if (numbers[next] != -1) {
        continue;
      }

      dfs(next, num);
    }
  }
}