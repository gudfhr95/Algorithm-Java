import java.util.*;

class Solution {

  ArrayList<Integer>[] adj;
  int[] dist;

  public int solution(int n, int[][] edge) {
    adj = new ArrayList[n + 1];
    dist = new int[n + 1];

    for (int i = 1; i <= n; i++) {
      adj[i] = new ArrayList<>();
    }

    Arrays.fill(dist, -1);

    for (int[] e : edge) {
      adj[e[0]].add(e[1]);
      adj[e[1]].add(e[0]);
    }

    bfs();

    return getResult();
  }

  public void bfs() {
    Queue<Integer> q = new LinkedList<>();

    q.add(1);
    dist[1] = 0;

    while (!q.isEmpty()) {
      int cur = q.remove();

      for (int next : adj[cur]) {
        if (dist[next] != -1) {
          continue;
        }

        q.add(next);
        dist[next] = dist[cur] + 1;
      }
    }
  }

  public int getResult() {
    int maxValue = Integer.MIN_VALUE;

    for (int d : dist) {
      maxValue = Math.max(maxValue, d);
    }

    int result = 0;
    for (int d : dist) {
      if (d == maxValue) {
        result += 1;
      }
    }

    return result;
  }
}