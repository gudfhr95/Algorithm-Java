import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

  static int N, R, M, K;
  static HashMap<String, Integer> cities = new HashMap<>();
  static int[] travelOrder = new int[200];
  static int[][] cost = new int[100][100];
  static int[][] discountCost = new int[100][100];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    R = in.nextInt();

    for (int i = 0; i < N; i++) {
      cities.put(in.next(), i);
    }

    M = in.nextInt();

    for (int i = 0; i < M; i++) {
      travelOrder[i] = cities.get(in.next());
    }

    for (int y = 0; y < N; y++) {
      for (int x = 0; x < N; x++) {
        if (y == x) {
          continue;
        }

        cost[y][x] = Integer.MAX_VALUE / 2;
        discountCost[y][x] = Integer.MAX_VALUE / 2;
      }
    }

    K = in.nextInt();

    for (int i = 0; i < K; i++) {
      String type = in.next();
      int s = cities.get(in.next());
      int e = cities.get(in.next());
      int c = in.nextInt();

      cost[s][e] = Math.min(cost[s][e], c * 2);
      cost[e][s] = Math.min(cost[e][s], c * 2);

      if (type.equals("Mugunghwa") || type.equals("ITX-Saemaeul") || type.equals(
          "ITX-Cheongchun")) {
        discountCost[s][e] = Math.min(discountCost[s][e], 0);
        discountCost[e][s] = Math.min(discountCost[e][s], 0);
      } else if (type.equals("S-Train") || type.equals("V-Train")) {
        discountCost[s][e] = Math.min(discountCost[s][e], c);
        discountCost[e][s] = Math.min(discountCost[e][s], c);
      } else {
        discountCost[s][e] = Math.min(discountCost[s][e], c * 2);
        discountCost[e][s] = Math.min(discountCost[e][s], c * 2);
      }
    }

    floyd();

    int costWithoutTicket = 0;
    int costWithTicket = 2 * R;
    for (int i = 0; i < M - 1; i++) {
      costWithoutTicket += cost[travelOrder[i]][travelOrder[i + 1]];
      costWithTicket += discountCost[travelOrder[i]][travelOrder[i + 1]];
    }

    if (costWithoutTicket <= costWithTicket) {
      out.println("No");
    } else {
      out.println("Yes");
    }

    out.flush();
  }

  static void floyd() {
    for (int k = 0; k < N; k++) {
      for (int y = 0; y < N; y++) {
        for (int x = 0; x < N; x++) {
          cost[y][x] = Math.min(cost[y][x], cost[y][k] + cost[k][x]);
          discountCost[y][x] = Math.min(discountCost[y][x],
              discountCost[y][k] + discountCost[k][x]);
        }
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