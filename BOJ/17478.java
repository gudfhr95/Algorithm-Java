import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

  static InputReader in = new InputReader(System.in);
  static PrintWriter out = new PrintWriter(System.out);

  static int N;

  public static void main(String[] args) {
    N = in.nextInt();

    out.println("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.");
    chatBot(0);

    out.flush();
  }

  static void chatBot(int n) {
    printIndent(n);
    out.println("\"재귀함수가 뭔가요?\"");

    if (n == N) {
      printIndent(n);
      out.println("\"재귀함수는 자기 자신을 호출하는 함수라네\"");
      printIndent(n);
      out.println("라고 답변하였지.");
      return;
    }
    
    printIndent(n);
    out.println("\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.");
    printIndent(n);
    out.println("마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.");
    printIndent(n);
    out.println("그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"");
    chatBot(n + 1);
    printIndent(n);
    out.println("라고 답변하였지.");
  }

  static void printIndent(int n) {
    for (int i = 0; i < n * 4; i++) {
      out.print('_');
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