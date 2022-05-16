import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeSet;

class Problem implements Comparable<Problem> {

  int p, l;

  public Problem(int p, int l) {
    this.p = p;
    this.l = l;
  }

  @Override
  public int compareTo(Problem other) {
    if (l == other.l) {
      return p - other.p;
    }
    return l - other.l;
  }
}

public class Main {

  static int N, M;
  static TreeSet<Problem> problemSet = new TreeSet<>(Collections.reverseOrder());
  static HashMap<Integer, Problem> problemMap = new HashMap<>();

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    for (int i = 0; i < N; i++) {
      add(in.nextInt(), in.nextInt());
    }

    M = in.nextInt();
    for (int i = 0; i < M; i++) {
      String command = in.next();

      if (command.equals("recommend")) {
        out.println(recommend(in.nextInt()));
      } else if (command.equals("add")) {
        add(in.nextInt(), in.nextInt());
      } else {
        solved(in.nextInt());
      }
    }

    out.flush();
  }

  static int recommend(int x) {
    if (x == 1) {
      return problemSet.first().p;
    } else {
      return problemSet.last().p;
    }
  }

  static void add(int p, int l) {
    Problem problem = new Problem(p, l);

    problemSet.add(problem);
    problemMap.put(p, problem);
  }

  static void solved(int p) {
    Problem problem = problemMap.get(p);

    problemSet.remove(problem);
    problemMap.remove(p);
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