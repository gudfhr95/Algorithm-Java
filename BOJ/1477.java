import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

  static int N, M, L;
  static ArrayList<Integer> pos = new ArrayList<>();
  static ArrayList<Integer> dist = new ArrayList<>();

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    M = in.nextInt();
    L = in.nextInt();
    for (int i = 0; i < N; i++) {
      pos.add(in.nextInt());
    }

    pos.add(0);
    pos.add(L);
    Collections.sort(pos);

    for (int i = 1; i < pos.size(); i++) {
      dist.add(pos.get(i) - pos.get(i - 1));
    }

    int result = 0;

    int l = 1;
    int r = L;
    while (l <= r) {
      int mid = (l + r) / 2;

      if (canBuild(mid)) {
        r = mid - 1;
        result = mid;
      } else {
        l = mid + 1;
      }
    }

    out.println(result);

    out.flush();
  }

  static boolean canBuild(int m) {
    int count = 0;
    for (int d : dist) {
      if (d % m == 0) {
        count += (d / m) - 1;
      } else {
        count += (d / m);
      }
    }
    return count <= M;
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