import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Stack;
import java.util.StringTokenizer;

class Person {

  int height;
  int count;

  public Person(int height) {
    this.height = height;
    count = 1;
  }
}

public class Main {

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    Stack<Person> people = new Stack<>();

    int N = in.nextInt();

    long result = 0;
    for (int i = 0; i < N; i++) {
      Person p = new Person(in.nextInt());

      int count = 1;
      while (!people.empty() && (people.peek().height <= p.height)) {
        result += people.peek().count;

        if (people.peek().height == p.height) {
          count += people.peek().count;
        }

        people.pop();
      }

      if (!people.empty()) {
        result += 1;
      }

      p.count = count;
      people.push(p);
    }

    out.print(result);

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