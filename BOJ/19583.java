import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

  static int S, E, Q;
  static HashSet<String> check = new HashSet<>();

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    S = toMinutes(in.next());
    E = toMinutes(in.next());
    Q = toMinutes(in.next());

    int result = 0;
    while (true) {
      String timeString = in.next();
      if (timeString == null) {
        break;
      }

      int time = toMinutes(timeString);
      String name = in.next();

      if (time <= S) {
        check.add(name);
      }

      if (E <= time && time <= Q && check.contains(name)) {
        check.remove(name);
        result += 1;
      }
    }

    out.println(result);

    out.flush();
  }

  static int toMinutes(String time) {
    String[] times = time.split(":");

    int hour = Integer.parseInt(times[0]);
    int minute = Integer.parseInt(times[1]);

    return hour * 60 + minute;
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
        String input = reader.readLine();
        if (input == null) {
          return null;
        }

        tokenizer = new StringTokenizer(input);
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