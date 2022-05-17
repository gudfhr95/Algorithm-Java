import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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

  static int N;
  static TreeSet<Problem>[] problemSet = new TreeSet[101];
  static HashMap<Integer, Problem>[] problemMap = new HashMap[101];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    for (int i = 0; i < N; i++) {
      int P = in.nextInt();
      int L = in.nextInt();
      int G = in.nextInt();

      add(P, L, G);
    }

    int M = in.nextInt();
    for (int i = 0; i < M; i++) {
      String command = in.next();

      if (command.equals("recommend")) {
        int G = in.nextInt();
        int x = in.nextInt();

        out.println(recommend1(G, x));
      } else if (command.equals("recommend2")) {
        int x = in.nextInt();

        out.println(recommend2(x));
      } else if (command.equals("recommend3")) {
        int x = in.nextInt();
        int L = in.nextInt();

        out.println(recommend3(x, L));
      } else if (command.equals("add")) {
        int P = in.nextInt();
        int L = in.nextInt();
        int G = in.nextInt();

        add(P, L, G);
      } else {
        int P = in.nextInt();

        solved(P);
      }
    }

    out.flush();
  }

  static int recommend1(int g, int x) {
    Problem problem;
    if (problemSet[g] == null || problemSet[g].isEmpty()) {
      return -1;
    }

    if (x == 1) {
      problem = problemSet[g].last();
    } else {
      problem = problemSet[g].first();
    }

    return problem.p;
  }

  static int recommend2(int x) {
    TreeSet<Problem> resultSet = new TreeSet<>();

    for (int g = 1; g <= 100; g++) {
      if (problemSet[g] == null || problemSet[g].isEmpty()) {
        continue;
      }

      Problem problem;
      if (x == 1) {
        problem = problemSet[g].last();
      } else {
        problem = problemSet[g].first();
      }

      resultSet.add(problem);
    }

    if (resultSet.isEmpty()) {
      return -1;
    }

    Problem result;
    if (x == 1) {
      result = resultSet.last();
    } else {
      result = resultSet.first();
    }

    return result.p;
  }

  static int recommend3(int x, int l) {
    TreeSet<Problem> resultSet = new TreeSet<>();

    Problem p = new Problem(0, l);
    for (int g = 1; g <= 100; g++) {
      if (problemSet[g] == null) {
        continue;
      }

      Problem problem = null;
      if (x == 1) {
        problem = problemSet[g].ceiling(p);
      } else {
        problem = problemSet[g].floor(p);
      }

      if (problem != null) {
        resultSet.add(problem);
      }
    }

    if (resultSet.isEmpty()) {
      return -1;
    }

    if (x == 1) {
      return resultSet.first().p;
    } else {
      return resultSet.last().p;
    }
  }

  static void add(int p, int l, int g) {
    Problem problem = new Problem(p, l);

    if (problemSet[g] == null) {
      problemSet[g] = new TreeSet<>();
    }
    problemSet[g].add(problem);

    if (problemMap[g] == null) {
      problemMap[g] = new HashMap<>();
    }
    problemMap[g].put(p, problem);
  }

  static void solved(int p) {
    Problem problem = null;
    for (int g = 1; g <= 100; g++) {
      if (problemMap[g] == null) {
        continue;
      }

      if (problemMap[g].containsKey(p)) {
        problem = problemMap[g].get(p);
        problemMap[g].remove(p);

        break;
      }
    }

    if (problem == null) {
      return;
    }

    for (int g = 1; g <= 100; g++) {
      if (problemSet[g] == null) {
        continue;
      }

      if (problemSet[g].contains(problem)) {
        problemSet[g].remove(problem);
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