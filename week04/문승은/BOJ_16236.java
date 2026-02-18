import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16236 {

    static int N, size, cnt, stack;
    static int r,c;
    static int[][] grid;
    static boolean[][] visited;
    static int[] dx = {-1,0,1,0}, dy = {0,-1,0,1};

    static class Point implements Comparable<Point>{
        int x, y, dist;

        public Point(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }

        @Override
        public int compareTo(Point o) {
            if(this.dist == o.dist) {
                if(this.x == o.x) {
                    return Integer.compare(this.y, o.y);
                }else {
                    return Integer.compare(this.x, o.x);
                }
            } else {
                return Integer.compare(this.dist, o.dist);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        grid = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
                if (grid[i][j] == 9) {
                    r = i;
                    c= j;
                    grid[i][j] = 0;
                }
            }
        }

        size = 2;

        while(true) {
            visited = new boolean[N][N];
            Point feeds = bfs(r,c);
            if(feeds.x==-1 & feeds.y==-1) break;

            r = feeds.x;
            c = feeds.y;
            cnt+= feeds.dist;
            stack++;
            grid[r][c] = 0;
            if(stack == size) {
                stack = 0;
                size++;
            }
        }

        System.out.println(cnt);

    }

    static boolean inRange(int x, int y) {
        return x>= 0 && y >=0 && x < N && y < N;
    }

    static Point bfs(int r, int c) {
        Queue<Point> q = new ArrayDeque<>();
        PriorityQueue<Point> feeds = new PriorityQueue<>();

        q.add(new Point(r,c,0));
        visited[r][c] = true;

        while(!q.isEmpty()) {
            Point cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nr = cur.x + dx[i];
                int nc = cur.y + dy[i];

                if(!inRange(nr,nc)) continue;
                if(visited[nr][nc]) continue;
                if(grid[nr][nc] > size) continue;
                if (grid[nr][nc] != 0 && grid[nr][nc] < size) {
                    feeds.add(new Point(nr,nc,cur.dist+1));
                }
                visited[nr][nc] = true;
                q.add(new Point(nr,nc,cur.dist+1));
            }
        }

        if(feeds.isEmpty()) {
            return new Point(-1,-1,-1);
        }
        return feeds.poll();
    }
}

