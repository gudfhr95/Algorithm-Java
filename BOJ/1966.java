import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

  static int T, N, M;
  static Queue<Integer> printerQueue = new LinkedList<>();
  static Queue<Integer> indexQueue = new LinkedList<>();
  static PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    T = in.nextInt();

    while (T-- > 0) {
      N = in.nextInt();
      M = in.nextInt();

      printerQueue.clear();
      indexQueue.clear();
      pq.clear();

      for (int i = 0; i < N; i++) {
        int p = in.nextInt();

        printerQueue.add(p);
        pq.add(p);

        if (i == M) {
          indexQueue.add(1);
        } else {
          indexQueue.add(0);
        }
      }

      out.println(count());
    }

    out.flush();
  }

  static int count() {
    int result = 0;

    while (!pq.isEmpty()) {
      if (printerQueue.peek() == pq.peek()) {
        result += 1;

        if (indexQueue.peek() == 1) {
          return result;
        }

        printerQueue.remove();
        pq.remove();
        indexQueue.remove();
      } else {
        printerQueue.add(printerQueue.remove());
        pq.add(pq.remove());
        indexQueue.add(indexQueue.remove());
      }
    }

    return result;
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