import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Pos{
  int x, y;

  public Pos(int x, int y){
    this.x = x;
    this.y = y;
  }
}

public class Main {

  static int[] dx = {0, 1, 0, -1};
  static int[] dy = {1, 0, -1, 0};

  static int W, H;
  static char[][] map = new char[100][100];
  static Pos start, end;
  static int[][] dist = new int[100][100];

  static void bfs(){
    Queue<Pos> q = new LinkedList<>();

    q.add(start);
    dist[start.y][start.x] = 0;

    while(!q.isEmpty()){
      Pos cur = q.peek();
      q.remove();

      for(int k=0; k<4; k++){
        for(int d=1;; d++){
          int xn = cur.x + (d * dx[k]);
          int yn = cur.y + (d * dy[k]);

          if(xn < 0 || yn < 0 || xn > W -1 || yn > H-1){
            break;
          }

          if(map[yn][xn] == '*'){
            break;
          }

          if(dist[yn][xn] == -1 || dist[cur.y][cur.x]+1 < dist[yn][xn]){
            q.add(new Pos(xn, yn));
            dist[yn][xn] = dist[cur.y][cur.x] + 1;
          }
        }
      }
    }
  }

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    W = in.nextInt();
    H = in.nextInt();

    for(int y=0; y<H; y++){
      map[y] = in.next().toCharArray();

      for(int x=0; x<W; x++){
        if(map[y][x] == 'C'){
          if(start == null){
            start = new Pos(x, y);
          } else {
            end = new Pos(x, y);
          }
        }
      }
    }

    for(int[] x: dist){
      Arrays.fill(x, -1);
    }

    bfs();

    out.println(dist[end.y][end.x] - 1);

    out.flush();
  }
}

class InputReader {
  BufferedReader reader;
  StringTokenizer tokenizer;

  public InputReader(InputStream stream){
    reader = new BufferedReader(new InputStreamReader(stream));
    tokenizer = null;
  }

  public String next(){
    while(tokenizer == null || !tokenizer.hasMoreTokens()){
      try {
        tokenizer = new StringTokenizer(reader.readLine());
      } catch (IOException e){
        throw new RuntimeException(e);
      }
    }
    return tokenizer.nextToken();
  }

  public int nextInt(){
    return Integer.parseInt(next());
  }
}