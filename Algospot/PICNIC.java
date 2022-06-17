import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

  static int C, n, m;
  static boolean[][] isFriend = new boolean[10][10];
  static boolean[] visited = new boolean[10];
  static int result = 0;

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    C = in.nextInt();

    while (C-- > 0) {
      n = in.nextInt();

      for (int y = 0; y < n; y++) {
        Arrays.fill(isFriend[y], false);
      }

      m = in.nextInt();

      for (int i = 0; i < m; i++) {
        int a = in.nextInt();
        int b = in.nextInt();

        isFriend[a][b] = isFriend[b][a] = true;
      }

      result = 0;
      findFriends();

      out.println(result);
    }

    out.flush();
  }

  static void findFriends() {
    int first = getFirstPerson();

    if (first == -1) {
      result += 1;
      return;
    }

    for (int i = first + 1; i < n; i++) {
      if (!visited[first] && !visited[i] && isFriend[first][i]) {
        visited[first] = visited[i] = true;
        findFriends();
        visited[first] = visited[i] = false;
      }
    }
  }

  static int getFirstPerson() {
    for (int i = 0; i < n; i++) {
      if (!visited[i]) {
        return i;
      }
    }

    return -1;
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
