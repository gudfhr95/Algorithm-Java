import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

class Node {

  int left, right, parent;

  public Node() {
    this.left = -1;
    this.right = -1;
    this.parent = -1;
  }
}

public class Main {

  static int N;
  static Node[] nodes = new Node[100001];
  static int edges = 0;
  static int lastNode = -1;

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    for (int i = 0; i < N; i++) {
      int a = in.nextInt();
      int b = in.nextInt();
      int c = in.nextInt();

      if (a != -1 && nodes[a] == null) {
        nodes[a] = new Node();
      }
      if (b != -1 && nodes[b] == null) {
        nodes[b] = new Node();
      }
      if (c != -1 && nodes[c] == null) {
        nodes[c] = new Node();
      }

      nodes[a].left = b;
      nodes[a].right = c;

      if (b != -1) {
        nodes[b].parent = a;
        edges += 1;
      }
      if (c != -1) {
        nodes[c].parent = a;
        edges += 1;
      }
    }

    dfs(1);

    int dist = 0;
    int cur = lastNode;
    while (nodes[cur].parent != -1) {
      dist += 1;
      cur = nodes[cur].parent;
    }

    out.println(2 * edges - dist);

    out.flush();
  }

  static void dfs(int n) {
    if (n == -1) {
      return;
    }

    dfs(nodes[n].left);
    lastNode = n;
    dfs(nodes[n].right);
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