import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

  static int N, M;
  static HashMap<String, Integer> pokemonToNumber = new HashMap<>();
  static HashMap<Integer, String> numberToPokemon = new HashMap<>();

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    M = in.nextInt();

    for (int i = 1; i <= N; i++) {
      String name = in.next();

      pokemonToNumber.put(name, i);
      numberToPokemon.put(i, name);
    }

    while (M-- > 0) {
      String query = in.next();

      if (isNumber(query)) {
        int index = Integer.parseInt(query);

        out.println(numberToPokemon.get(index));
      } else {
        out.println(pokemonToNumber.get(query));
      }
    }

    out.flush();
  }

  static boolean isNumber(String s) {
    try {
      Integer.parseInt(s);
      return true;
    } catch (NumberFormatException e) {
      return false;
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