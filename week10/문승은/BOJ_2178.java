import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2178 {

    static class Point {
        int r,c,depth;

        public Point(int r, int c, int depth) {
            this.r = r;
            this.c = c;
            this.depth = depth;
        }
    }

    static int N, M;
    static int[][] grid;
    static boolean[][] visited;
    static int[] dr = {-1, 1, 0,0 }, dc = {0,0,-1,1};

    public static void main(String[] args) throws IOException {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        grid = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                grid[i][j] = line.charAt(j) - '0';
            }
        }

        System.out.println(bfs());
    }

    static int bfs() {

        Queue<Point> q = new ArrayDeque<>();
        q.add(new Point(0,0,1));

        while(!q.isEmpty()) {
            Point curr = q.poll();

            if(curr.r == N-1 && curr.c == M-1) {
                return curr.depth;
            }

            for(int k=0; k<4; k++) {
                int nr = curr.r + dr[k];
                int nc = curr.c + dc[k];

                if(nr >= N || nc >= M || nr < 0 || nc < 0) {
                    continue;
                }

                if(!visited[nr][nc] && grid[nr][nc] == 1) {
                    visited[nr][nc] = true;
                    q.add(new Point(nr, nc, curr.depth+1));
                }
            }
        }

        return -1;
    }
}