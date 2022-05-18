import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

class Student implements Comparable<Student> {

  int h, k;

  public Student(int h, int k) {
    this.h = h;
    this.k = k;
  }

  @Override
  public int compareTo(Student other) {
    return h - other.h;
  }
}

public class Main {

  static int N;
  static TreeSet<Student> students = new TreeSet<>(Collections.reverseOrder());
  static TreeMap<Integer, Integer> teams = new TreeMap<>();

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    for (int i = 0; i < N; i++) {
      Student student = new Student(in.nextInt(), in.nextInt());
      students.add(student);
    }

    for (Student student : students) {
      Integer floor = teams.lowerKey(student.k);

      if (floor == null) {
        if (!teams.containsKey(1)) {
          teams.put(1, 0);
        }

        int count = teams.get(1);
        teams.replace(1, count + 1);
      } else {
        int newKey = floor + 1;
        if (!teams.containsKey(newKey)) {
          teams.put(newKey, 0);
        }

        int count = teams.get(newKey);
        teams.replace(newKey, count + 1);

        count = teams.get(floor);
        if (count == 1) {
          teams.remove(floor);
        } else {
          teams.replace(floor, count - 1);
        }
      }
    }

    int result = 0;
    for (Entry<Integer, Integer> entry : teams.entrySet()) {
      result += entry.getValue();
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