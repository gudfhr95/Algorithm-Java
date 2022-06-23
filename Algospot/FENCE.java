import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

  static int C, N;
  static int[] board = new int[20000];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    C = in.nextInt();

    while (C-- > 0) {
      N = in.nextInt();

      for (int i = 0; i < N; i++) {
        board[i] = in.nextInt();
      }

      out.println(cut(0, N - 1));
    }

    out.flush();
  }

  static int cut(int start, int end) {
    if (start == end) {
      return board[start];
    }

    int mid = (start + end) / 2;
    int left = cut(start, mid);
    int right = cut(mid + 1, end);

    int result = Math.max(left, right);

    int highest = Math.max(board[mid], board[mid + 1]);
    int leftIndex = board[mid] > board[mid + 1] ? mid : mid + 1;
    int rightIndex = board[mid] > board[mid + 1] ? mid : mid + 1;

    while (start < leftIndex || rightIndex < end) {
      if (rightIndex < end && (leftIndex == start || board[leftIndex - 1] < board[rightIndex
          + 1])) {
        rightIndex += 1;
        highest = Math.min(highest, board[rightIndex]);
      } else {
        leftIndex -= 1;
        highest = Math.min(highest, board[leftIndex]);
      }

      result = Math.max(result, highest * ((rightIndex - leftIndex) + 1));
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