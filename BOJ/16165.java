import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

  static int N, M;
  static HashMap<String, ArrayList<String>> teamToMember = new HashMap<>();
  static HashMap<String, String> memberToTeam = new HashMap<>();

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    M = in.nextInt();

    for (int i = 0; i < N; i++) {
      String team = in.next();

      ArrayList<String> members = new ArrayList<>();

      int memberCount = in.nextInt();
      for (int j = 0; j < memberCount; j++) {
        String name = in.next();

        members.add(name);
        memberToTeam.put(name, team);
      }

      Collections.sort(members);

      teamToMember.put(team, members);
    }

    for (int i = 0; i < M; i++) {
      String quiz = in.next();
      int kind = in.nextInt();

      if (kind == 0) {
        for (String name : teamToMember.get(quiz)) {
          out.println(name);
        }
      } else {
        out.println(memberToTeam.get(quiz));
      }
    }

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