import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

class Node {

  int n;
  Node parent, left, right;

  public Node(int n) {
    this.n = n;
  }
}

public class Main {

  static InputReader in = new InputReader(System.in);
  static PrintWriter out = new PrintWriter(System.out);

  static Node root = null;

  public static void main(String[] args) {
    int n = in.nextInt();
    root = new Node(n);

    while (true) {
      n = in.nextInt();

      if (n == 0) {
        break;
      }

      put(root, n);
    }

    postOrder(root);

    out.flush();
  }

  static void put(Node node, int n) {
    if (n < node.n) {
      if (node.left == null) {
        node.left = new Node(n);
        return;
      }

      put(node.left, n);
    } else {
      if (node.right == null) {
        node.right = new Node(n);
        return;
      }

      put(node.right, n);
    }
  }

  static void postOrder(Node node) {
    if (node.left != null) {
      postOrder(node.left);
    }

    if (node.right != null) {
      postOrder(node.right);
    }

    out.println(node.n);
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
        String s = reader.readLine();

        if (s == null) {
          return null;
        }

        tokenizer = new StringTokenizer(s);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }

    return tokenizer.nextToken();
  }

  public int nextInt() {
    String s = next();
    if (s == null) {
      return 0;
    }

    return Integer.parseInt(s);
  }
}