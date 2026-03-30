import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2206 {

    static class Point {
        int r, c, cnt,broken;

        public Point(int r, int c, int cnt, int broken){
            this.r = r;
            this.c = c;
            this.cnt = cnt;
            this.broken = broken;
        }
    }

    static int N,M;
    static int[][] grid;
    static boolean[][][] visited;
    static int[] dr = {-1,1,0,0}, dc={0,0,-1,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        grid = new int[N][M];
        visited = new boolean[N][M][2];
        for(int i=0;i<N;i++) {
            String line = br.readLine();
            for(int j=0; j<M;j++) {
                grid[i][j] = line.charAt(j) - '0';
            }
        }

        System.out.println(bfs());



    }

    static int bfs() {
        Queue<Point> q = new ArrayDeque<>();
        q.add(new Point(0,0,1, 0));

        while(!q.isEmpty()) {
            Point curr =  q.poll();

            if(curr.r == N-1 && curr.c == M-1) {
                return curr.cnt;
            }

            for(int k=0; k<4;k++) {
                int nr = curr.r + dr[k];
                int nc = curr.c + dc[k];

                if(nr < 0 || nc < 0 || nr >= N || nc >= M) {
                    continue;
                }

                if(grid[nr][nc] == 0 && !visited[nr][nc][curr.broken]) {
                    visited[nr][nc][curr.broken] = true;
                    q.add(new Point(nr,nc,curr.cnt+1,curr.broken));
                }

                else {
                    if (curr.broken == 0 && !visited[nr][nc][1]){
                        visited[nr][nc][1] = true;
                        q.add(new Point(nr,nc,curr.cnt+1,1));
                    }

                }
            }
        }
        return -1;
    }




}
