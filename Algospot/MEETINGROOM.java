import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;
import java.util.StringTokenizer;

class Meeting {

  int start, end;

  public Meeting(int start, int end) {
    this.start = start;
    this.end = end;
  }
}

class Pair implements Comparable<Pair> {

  int first, second;

  public Pair(int first, int second) {
    this.first = first;
    this.second = second;
  }

  @Override
  public int compareTo(Pair o) {
    if (first == o.first) {
      return second - o.second;
    }
    return first - o.first;
  }
}

public class Main {

  static int N, sccCounter, vertexCounter;
  static ArrayList<Integer>[] adj = new ArrayList[400];
  static int[] sccId = new int[400];
  static int[] discovered = new int[400];
  static Stack<Integer> stack = new Stack<>();

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    int C = in.nextInt();

    while (C-- > 0) {
      N = in.nextInt();

      ArrayList<Meeting> meetings = new ArrayList<>();
      for (int i = 0; i < N; i++) {
        meetings.add(new Meeting(in.nextInt(), in.nextInt()));
        meetings.add(new Meeting(in.nextInt(), in.nextInt()));
      }

      makeGraph(meetings);

      ArrayList<Integer> result = solve2SAT();

      if (result == null) {
        out.println("IMPOSSIBLE");
      } else {
        out.println("POSSIBLE");
        for (int i = 0; i < 2 * N; i++) {
          if (result.get(i) == 1) {
            out.printf("%d %d\n", meetings.get(i).start, meetings.get(i).end);
          }
        }
      }
    }

    out.flush();
  }

  static void makeGraph(ArrayList<Meeting> meetings) {
    for (int i = 0; i < N * 4; i++) {
      adj[i] = new ArrayList<>();
    }

    for (int i = 0; i < N * 2; i += 2) {
      int j = i + 1;

      adj[i * 2 + 1].add(j * 2);
      adj[j * 2 + 1].add(i * 2);
      adj[i * 2].add(j * 2 + 1); // i -> ~j
      adj[j * 2].add(i * 2 + 1); // j -> ~i
    }

    for (int i = 0; i < N * 2; i++) {
      for (int j = 0; j < i; j++) {
        if (!disjoint(meetings.get(i), meetings.get(j))) {
          adj[i * 2].add(j * 2 + 1);
          adj[j * 2].add(i * 2 + 1);
        }
      }
    }
  }

  static boolean disjoint(Meeting a, Meeting b) {
    return (a.end <= b.start) || (b.end <= a.start);
  }

  static ArrayList<Integer> solve2SAT() {
    tarjanSCC();

    for (int i = 0; i < 4 * N; i += 2) {
      if (sccId[i] == sccId[i + 1]) {
        return null;
      }
    }

    ArrayList<Integer> result = new ArrayList<>();
    ArrayList<Pair> order = new ArrayList<>();
    for (int i = 0; i < 4 * N; i++) {
      result.add(-1);
      order.add(new Pair(-sccId[i], i));
    }

    Collections.sort(order);

    for (int i = 0; i < 4 * N; i++) {
      int vertex = order.get(i).second;
      int variable = vertex / 2;
      int isTrue = (vertex % 2) == 0 ? 1 : 0;

      if (result.get(variable) != -1) {
        continue;
      }

      result.set(variable, 1 - isTrue);
    }

    return result;
  }

  static void tarjanSCC() {
    Arrays.fill(sccId, -1);
    Arrays.fill(discovered, -1);
    stack.clear();

    sccCounter = vertexCounter = 0;

    for (int i = 0; i < N * 4; i++) {
      if (discovered[i] == -1) {
        scc(i);
      }
    }
  }

  static int scc(int here) {
    int ret = discovered[here] = vertexCounter++;

    stack.push(here);
    for (int there : adj[here]) {
      if (discovered[there] == -1) {
        ret = Math.min(ret, scc(there));
      } else if (sccId[there] == -1) {
        ret = Math.min(ret, discovered[there]);
      }
    }

    if (ret == discovered[here]) {
      while (true) {
        int t = stack.peek();
        stack.pop();

        sccId[t] = sccCounter;
        if (t == here) {
          break;
        }
      }
      sccCounter += 1;
    }

    return ret;
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