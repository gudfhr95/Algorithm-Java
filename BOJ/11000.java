import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Lecture {

  int start, end;

  Lecture(int start, int end) {
    this.start = start;
    this.end = end;
  }
}

public class Main {

  static int N;
  static ArrayList<Lecture> lectures = new ArrayList<>();

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    for (int i = 0; i < N; i++) {
      lectures.add(new Lecture(in.nextInt(), in.nextInt()));
    }

    Collections.sort(lectures, Comparator.comparingInt(l -> l.start));

    int result = 0;
    PriorityQueue<Integer> pq = new PriorityQueue<>();

    for (Lecture lecture : lectures) {
      if (!pq.isEmpty() && pq.peek() <= lecture.start) {
        pq.remove();
      }

      pq.add(lecture.end);
      result = Math.max(result, pq.size());
    }

    out.println(result);

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