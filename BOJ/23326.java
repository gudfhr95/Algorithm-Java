import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {

  static int N, Q;
  static TreeSet<Integer> places = new TreeSet<>();
  static int current = 0;

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    Q = in.nextInt();

    for (int i = 0; i < N; i++) {
      int isFavorite = in.nextInt();

      if (isFavorite == 1) {
        places.add(i);
        places.add(i + N);
      }
    }

    for (int i = 0; i < Q; i++) {
      int query = in.nextInt();

      if (query == 1) {
        int index = in.nextInt() - 1;
        if (places.contains(index)) {
          places.remove(index);
          places.remove(index + N);
        } else {
          places.add(index);
          places.add(index + N);
        }
      } else if (query == 2) {
        current = (current + in.nextInt()) % N;
      } else if (query == 3) {
        if (places.size() == 0) {
          out.println(-1);
        } else {
          out.println(getDistance());
        }
      }
    }

    out.flush();
  }

  static int getDistance() {
    int index = places.ceiling(current);

    return index - current;
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