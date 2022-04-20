import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

class Flower {

  LocalDate start;
  LocalDate end;

  Flower(int startMonth, int startDay, int endMonth, int endDay) {
    start = LocalDate.of(2022, startMonth, startDay);
    end = LocalDate.of(2022, endMonth, endDay);
  }
}

public class Main {

  static int N;
  static ArrayList<Flower> flowers = new ArrayList<>();

  public static void main(String[] args) {
    InputRedaer in = new InputRedaer(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    for (int i = 0; i < N; i++) {
      flowers.add(new Flower(in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt()));
    }

    Collections.sort(flowers, Comparator.comparing(o -> o.start));

    int result = 0;

    int index = 0;
    LocalDate currentDate = LocalDate.of(2022, 3, 1);
    while (true) {
      int i = peekFlower(index, currentDate);

      if (i == -1) {
        out.println(0);

        out.flush();

        return;
      }

      Flower flower = flowers.get(i);

      index += 1;
      currentDate = flower.end;
      result += 1;

      if (flower.end.compareTo(LocalDate.of(2022, 12, 1)) >= 0) {
        break;
      }
    }

    out.println(result);

    out.flush();
  }

  static int peekFlower(int index, LocalDate currentDate) {
    int result = -1;
    LocalDate maxEndDate = null;
    for (int i = index; i < N; i++) {
      Flower flower = flowers.get(i);

      if (flower.start.compareTo(currentDate) > 0) {
        break;
      }

      if (maxEndDate == null || flower.end.compareTo(maxEndDate) > 0) {
        result = i;
        maxEndDate = flower.end;
      }
    }

    return result;
  }
}

class InputRedaer {

  BufferedReader reader;
  StringTokenizer tokenizer;

  public InputRedaer(InputStream stream) {
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