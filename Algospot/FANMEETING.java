import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

  static final int MSB_MASK = 1 << 31;
  static final long INT_MASK = 0xFFFFFFFFL;

  static int C;
  static String member, fan;
  static long[] memberInt = new long[10000];
  static long[] fanInt = new long[10000];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    C = in.nextInt();

    while (C-- > 0) {
      member = in.next();
      fan = in.next();

      convert();

      int result = 0;
      for (int i = 0; i <= fan.length() - member.length(); i++) {
        if (hug()) {
          result += 1;
        }

        shift();
      }

      out.println(result);
    }

    out.flush();
  }

  static void convert() {
    Arrays.fill(memberInt, 0L);
    Arrays.fill(fanInt, 0L);

    int count = 0;
    int index = 0;
    for (int i = 0; i < member.length(); i++) {
      if (member.charAt(i) == 'M') {
        memberInt[index] += 1;
      }

      if (count == 31) {
        count = 0;
        index += 1;
      } else {
        count += 1;
        memberInt[index] <<= 1;
      }
    }

    if (count != 0) {
      memberInt[index] <<= 31 - count;
    }

    count = 0;
    index = 0;
    for (int i = 0; i < fan.length(); i++) {
      if (fan.charAt(i) == 'M') {
        fanInt[index] += 1;
      }

      if (count == 31) {
        count = 0;
        index += 1;
      } else {
        count += 1;
        fanInt[index] <<= 1;
      }
    }

    if (count != 0) {
      fanInt[index] <<= 31 - count;
    }
  }

  static boolean hug() {
    for (int i = 0; i < memberInt.length; i++) {
      if ((memberInt[i] & fanInt[i]) != 0) {
        return false;
      }
    }

    return true;
  }

  static void shift() {
    for (int i = 0; i < fanInt.length - 1; i++) {
      fanInt[i] <<= 1;
      fanInt[i] &= INT_MASK;

      if ((fanInt[i + 1] & MSB_MASK) != 0) {
        fanInt[i] += 1;
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