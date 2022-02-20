import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

  static int N, K;
  static int[][] A = new int[10][10];
  static int[][] rps = new int[3][20];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    K = in.nextInt();

    for (int y = 1; y <= N; y++) {
      for (int x = 1; x <= N; x++) {
        A[y][x] = in.nextInt();
      }
    }

    for (int i = 0; i < 20; i++) {
      rps[1][i] = in.nextInt();
    }

    for (int i = 0; i < 20; i++) {
      rps[2][i] = in.nextInt();
    }

    int[] j = new int[N];
    for (int i = 1; i <= N; i++) {
      j[i - 1] = i;
    }

    do {
      if (simulate(j)) {
        out.println(1);

        out.flush();

        return;
      }
    } while (nextPermutation(j));

    out.println(0);

    out.flush();
  }

  static boolean simulate(int[] j) {
    for (int i = 0; i < j.length; i++) {
      rps[0][i] = j[i];
    }

    int[] score = new int[3];
    int[] index = new int[3];
    int p1 = 0, p2 = 1;

    while (true) {
      if (p1 > p2) {
        int temp = p1;
        p1 = p2;
        p2 = temp;
      }

      if (p1 == 0 && index[0] >= N) {
        return false;
      }

      int h1 = rps[p1][index[p1]++];
      int h2 = rps[p2][index[p2]++];

      if (A[h1][h2] == 2) {
        score[p1] += 1;

        p2 = 3 - p1 - p2;
      } else if (A[h1][h2] <= 1) {
        score[p2] += 1;

        int next = 3 - p1 - p2;
        p1 = p2;
        p2 = next;
      }

      if (score[1] == K || score[2] == K) {
        return false;
      }

      if (score[0] == K) {
        return true;
      }
    }
  }

  static boolean nextPermutation(int[] a) {
    int i = a.length - 1;
    while (i > 0 && a[i - 1] >= a[i]) {
      i -= 1;
    }

    if (i <= 0) {
      return false;
    }

    int j = a.length - 1;
    while (a[j] <= a[i - 1]) {
      j -= 1;
    }

    swap(a, i - 1, j);

    j = a.length - 1;
    while (i < j) {
      swap(a, i++, j--);
    }

    return true;
  }

  static void swap(int[] a, int i, int j) {
    int temp = a[i];
    a[i] = a[j];
    a[j] = temp;
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