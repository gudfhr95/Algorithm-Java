import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

  static int N, M;
  static ArrayList<Integer>[] adj = new ArrayList[51];
  static boolean[] truth = new boolean[51];
  static ArrayList<ArrayList<Integer>> parties = new ArrayList<>();

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    M = in.nextInt();

    for (int i = 1; i <= N; i++) {
      adj[i] = new ArrayList<>();
    }

    int n = in.nextInt();
    for (int i = 0; i < n; i++) {
      truth[in.nextInt()] = true;
    }

    for (int i = 0; i < M; i++) {
      n = in.nextInt();

      ArrayList<Integer> participants = new ArrayList<>();
      for (int j = 0; j < n; j++) {
        participants.add(in.nextInt());
      }
      parties.add(participants);

      for (int j = 0; j < n; j++) {
        for (int k = j + 1; k < n; k++) {
          int a = participants.get(j);
          int b = participants.get(k);

          adj[a].add(b);
          adj[b].add(a);
        }
      }
    }

    bfs();

    int result = 0;
    for (ArrayList<Integer> party : parties) {
      if (canTellLie(party)) {
        result += 1;
      }
    }

    out.println(result);

    out.flush();
  }

  static void bfs() {
    Queue<Integer> q = new LinkedList<>();
    boolean[] visited = new boolean[N + 1];

    for (int i = 1; i <= N; i++) {
      if (truth[i]) {
        q.add(i);
        visited[i] = true;
      }
    }

    while (!q.isEmpty()) {
      int cur = q.remove();
      truth[cur] = true;

      for (int next : adj[cur]) {
        if (visited[next]) {
          continue;
        }

        q.add(next);
        visited[next] = true;
      }
    }
  }

  static boolean canTellLie(ArrayList<Integer> party) {
    for (int participant : party) {
      if (truth[participant]) {
        return false;
      }
    }

    return true;
  }
}

class InputReader {

  BufferedReader reader;
  StringTokenizer tokenizer;

  public InputReader(InputStream stream) {
    reader = new BufferedReader(new InputStreamReader(stream));
    tokenizer = null;
  }

  public String next() {
    while (tokenizer == null || !tokenizer.hasMoreTokens()) {
      try {
        tokenizer = new StringTokenizer(reader.readLine());
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }

    return tokenizer.nextToken();
  }

  public int nextInt() {
    return Integer.parseInt(next());
  }
}