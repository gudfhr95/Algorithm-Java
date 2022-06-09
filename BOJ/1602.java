import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Dog implements Comparable<Dog> {

  int index, cost;

  public Dog(int index, int cost) {
    this.index = index;
    this.cost = cost;
  }

  @Override
  public int compareTo(Dog other) {
    return cost - other.cost;
  }
}

public class Main {

  static int N, M, Q;
  static ArrayList<Dog> dogs = new ArrayList<>();
  static int[][] dist = new int[501][501];
  static int[][] total = new int[501][501];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    M = in.nextInt();
    Q = in.nextInt();

    for (int i = 1; i <= N; i++) {
      dogs.add(new Dog(i, in.nextInt()));
    }

    for (int y = 1; y <= N; y++) {
      for (int x = 1; x <= N; x++) {
        if (y == x) {
          continue;
        }

        dist[y][x] = Integer.MAX_VALUE / 2;
        total[y][x] = Integer.MAX_VALUE / 2;
      }
    }

    for (int i = 0; i < M; i++) {
      int a = in.nextInt();
      int b = in.nextInt();
      int d = in.nextInt();

      dist[a][b] = dist[b][a] = d;
    }

    floyd();

    for (int i = 0; i < Q; i++) {
      int S = in.nextInt();
      int T = in.nextInt();

      int result = total[S][T];
      if (result == Integer.MAX_VALUE / 2) {
        out.println(-1);
      } else {
        out.println(result);
      }
    }

    out.flush();
  }

  static void floyd() {
    Collections.sort(dogs);

    for (Dog k : dogs) {
      for (Dog y : dogs) {
        for (Dog x : dogs) {
          dist[y.index][x.index] = Math.min(dist[y.index][x.index],
              dist[y.index][k.index] + dist[k.index][x.index]);

          int sum = dist[y.index][x.index] + Math.max(k.cost, Math.max(y.cost, x.cost));
          total[y.index][x.index] = Math.min(total[y.index][x.index], sum);
        }
      }
    }
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