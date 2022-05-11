import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

class Student implements Comparable<Student> {

  int ability, clazz, index;

  Student(int ability, int clazz, int index) {
    this.ability = ability;
    this.clazz = clazz;
    this.index = index;
  }

  @Override
  public int compareTo(Student other) {
    return ability - other.ability;
  }
}

public class Main {

  static int N, M;
  static int[][] abilities = new int[1000][1000];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    M = in.nextInt();
    for (int y = 0; y < N; y++) {
      for (int x = 0; x < M; x++) {
        abilities[y][x] = in.nextInt();
      }
    }

    for (int y = 0; y < N; y++) {
      Arrays.sort(abilities[y], 0, M);
    }

    ArrayList<Student> students = new ArrayList<>();
    for (int y = 0; y < N; y++) {
      students.add(new Student(abilities[y][0], y, 0));
    }

    int result = Integer.MAX_VALUE;
    while (true) {
      Collections.sort(students, Collections.reverseOrder());

      int lastIndex = students.size() - 1;
      Student lastStudent = students.get(lastIndex);

      int diff = students.get(0).ability - lastStudent.ability;
      result = Math.min(result, diff);

      if (lastStudent.index == M - 1) {
        break;
      }

      Student newStudent = new Student(
          abilities[lastStudent.clazz][lastStudent.index + 1],
          lastStudent.clazz,
          lastStudent.index + 1
      );

      students.remove(lastIndex);
      students.add(newStudent);
    }

    out.println(result);

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