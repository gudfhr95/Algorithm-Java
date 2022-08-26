import java.util.*;

class Solution {

  class Edge {

    int to, cost;

    Edge(int to, int cost) {
      this.to = to;
      this.cost = cost;
    }
  }

  ArrayList<Edge>[] adj = new ArrayList[100];
  boolean[] visited = new boolean[100];
  PriorityQueue<Edge> pq = new PriorityQueue<>(
      (e1, e2) -> e1.cost - e2.cost
  );

  public int solution(int n, int[][] costs) {
    for (int i = 0; i < n; i++) {
      adj[i] = new ArrayList<>();
    }

    for (int[] cost : costs) {
      adj[cost[0]].add(new Edge(cost[1], cost[2]));
      adj[cost[1]].add(new Edge(cost[0], cost[2]));
    }

    visited[0] = true;
    for (Edge e : adj[0]) {
      pq.add(e);
    }

    int result = 0;
    while (!pq.isEmpty()) {
      Edge cur = pq.poll();

      if (visited[cur.to]) {
        continue;
      }

      result += cur.cost;
      visited[cur.to] = true;
      for (Edge next : adj[cur.to]) {
        pq.add(next);
      }
    }

    return result;
  }
}