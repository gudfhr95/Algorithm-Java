import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeSet;

class Water {

  int a, b, c;

  public Water(int a, int b, int c) {
    this.a = a;
    this.b = b;
    this.c = c;
  }
}

public class Main {

  static int A, B, C;
  static boolean[][][] check = new boolean[201][201][201];

  static void bfs() {
    Queue<Water> q = new LinkedList<>();

    q.add(new Water(0, 0, C));
    check[0][0][C] = true;

    while (!q.isEmpty()) {
      Water cur = q.peek();
      q.remove();

      int an, bn, cn;
      // a -> b
      an = 0;
      bn = cur.b + cur.a;
      if (bn >= B) {
        an = bn - B;
        bn = B;
      }

      if (!check[an][bn][cur.c]) {
        q.add(new Water(an, bn, cur.c));
        check[an][bn][cur.c] = true;
      }

      // a -> c
      an = 0;
      cn = cur.c + cur.a;
      if (cn >= C) {
        an = cn - C;
        cn = C;
      }

      if (!check[an][cur.b][cn]) {
        q.add(new Water(an, cur.b, cn));
        check[an][cur.b][cn] = true;
      }

      // b -> a
      bn = 0;
      an = cur.a + cur.b;
      if (an >= A) {
        bn = an - A;
        an = A;
      }

      if (!check[an][bn][cur.c]) {
        q.add(new Water(an, bn, cur.c));
        check[an][bn][cur.c] = true;
      }

      // b -> c
      bn = 0;
      cn = cur.c + cur.b;
      if (cn >= C) {
        bn = cn - C;
        cn = C;
      }

      if (!check[cur.a][bn][cn]) {
        q.add(new Water(cur.a, bn, cn));
        check[cur.a][bn][cn] = true;
      }

      // c -> a
      cn = 0;
      an = cur.a + cur.c;
      if (an >= A) {
        cn = an - A;
        an = A;
      }

      if (!check[an][cur.b][cn]) {
        q.add(new Water(an, cur.b, cn));
        check[an][cur.b][cn] = true;
      }

      // c -> b
      cn = 0;
      bn = cur.b + cur.c;
      if (bn >= B) {
        cn = bn - B;
        bn = B;
      }

      if (!check[cur.a][bn][cn]) {
        q.add(new Water(cur.a, bn, cn));
        check[cur.a][bn][cn] = true;
      }
    }
  }

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    A = in.nextInt();
    B = in.nextInt();
    C = in.nextInt();

    bfs();

    TreeSet<Integer> results = new TreeSet<>();
    for (int b = 0; b <= B; b++) {
      for (int c = 0; c <= C; c++) {
        if (check[0][b][c]) {
          results.add(c);
        }
      }
    }

    for (Integer result : results) {
      out.printf("%d ", result);
    }

    out.flush();
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