import java.util.*;

class Solution {

  ArrayList<Integer>[] adj = new ArrayList[200];
  boolean[] visited = new boolean[200];

  public int solution(int n, int[][] computers) {
    for (int i = 0; i < n; i++) {
      adj[i] = new ArrayList<>();
    }

    for (int y = 0; y < n; y++) {
      for (int x = 0; x < n; x++) {
        if (computers[y][x] == 1) {
          adj[y].add(x);
          adj[x].add(y);
        }
      }
    }

    int result = 0;
    for (int i = 0; i < n; i++) {
      if (!visited[i]) {
        bfs(i);
        result += 1;
      }
    }

    return result;
  }

  public void bfs(int start) {
    Queue<Integer> q = new LinkedList<>();

    q.add(start);
    visited[start] = true;

    while (!q.isEmpty()) {
      int cur = q.remove();

      for (int next : adj[cur]) {
        if (visited[next]) {
          continue;
        }

        q.add(next);
        visited[next] = true;
      }
    }
  }
}