import java.util.*;

class Solution {

  ArrayList<Integer>[] adj = new ArrayList[101];
  boolean[] visited = new boolean[101];
  int[] selected;

  public int solution(int n, int[][] wires) {
    for (int i = 1; i <= 100; i++) {
      adj[i] = new ArrayList<>();
    }

    for (int i = 0; i < wires.length; i++) {
      adj[wires[i][0]].add(wires[i][1]);
      adj[wires[i][1]].add(wires[i][0]);
    }

    int result = Integer.MAX_VALUE;
    for (int i = 0; i < wires.length; i++) {
      Arrays.fill(visited, false);

      selected = wires[i];

      int t1 = bfs(wires[i][0]);
      int t2 = bfs(wires[i][1]);

      result = Math.min(result, Math.abs(t1 - t2));
    }

    return result;
  }

  public int bfs(int start) {
    Queue<Integer> q = new LinkedList<>();

    q.add(start);
    visited[start] = true;

    int result = 1;
    while (!q.isEmpty()) {
      int cur = q.remove();

      for (int next : adj[cur]) {
        if (visited[next]) {
          continue;
        }

        if (cur == selected[0] && next == selected[1]) {
          continue;
        }

        if (cur == selected[1] && next == selected[0]) {
          continue;
        }

        q.add(next);
        visited[next] = true;
        result += 1;
      }
    }

    return result;
  }
}