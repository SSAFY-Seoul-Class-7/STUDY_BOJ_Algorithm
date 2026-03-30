import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2583 {
    static int N, M, K;
    static int[][] grid;
    static boolean[][] visited;
    static List<Integer> spaces;

    static class Point {
        int r,c;

        public Point(int r,int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        grid = new int[M][N];
        visited = new boolean[M][N];
        spaces = new ArrayList<>();

        int lx, ly, rx, ry;
        //좌표 칠하고
        for(int i=0; i<K;i++) {
            st = new StringTokenizer(br.readLine());
            lx =  Integer.parseInt(st.nextToken());
            ly =  M - Integer.parseInt(st.nextToken());
            rx =  Integer.parseInt(st.nextToken());
            ry =  M - Integer.parseInt(st.nextToken());

            for(int row=ry; row <ly; row++) {
                for(int col=lx; col< rx; col++) {
                    grid[row][col] = 1;
                }
            }
        }


        //dfs나 bfs 로 영역 넓이랑 개수 구하기
        for(int i=0; i<M; i++) {
            for(int j=0; j<N;j++) {
                if(grid[i][j] == 0 && !visited[i][j]) {
                    int size = bfs(i,j);
                    spaces.add(size);
                }
            }
        }

        Collections.sort(spaces);
        System.out.println(spaces.size());
        for(int i=0; i< spaces.size(); i++) {
            System.out.print(spaces.get(i) + " ");
        }
    }

    static int bfs(int sr, int sc) {
        int size = 1;
        int[] dr = {1,-1,0,0}, dc = {0,0,1,-1};
        Queue<Point> q = new ArrayDeque<>();
        q.add(new Point(sr, sc));
        visited[sr][sc] = true;

        while( !q.isEmpty()) {
            Point curr = q.poll();

            for(int k=0; k<4;k++) {
                int nr = curr.r + dr[k];
                int nc = curr.c + dc[k];

                if(nr < 0 | nr >= M | nc < 0 | nc >=N) continue;

                if(!visited[nr][nc] && grid[nr][nc] ==0) {
                    visited[nr][nc] = true;
                    q.add(new Point(nr,nc));
                    size++;
                }
            }
        }

        return size;
    }


}
