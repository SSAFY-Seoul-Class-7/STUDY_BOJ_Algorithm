import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_3197 {

    static int R,C,ans;
    static String[][] grid;
    static int[] dx = {-1,1,0,0}, dy = {0,0,-1,1};
    static boolean[][] visited;
    static boolean[][] checkVisited;
    static List<Point> swans;
    static Queue<Point> here = new ArrayDeque<>();


    static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        grid = new String[R][C];
        visited = new boolean[R][C];
        checkVisited = new boolean[R][C];
        swans = new ArrayList<>();

        Queue<Point> q = new ArrayDeque<>();

        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                grid[i][j] = Character.toString(line.charAt(j));
                if(!grid[i][j].equals("X")) {
                    q.add(new Point(i,j));
                    visited[i][j] = true;
                }
                if(grid[i][j].equals("L")) {
                    swans.add(new Point(i,j));

                }

            }
        }

        Point s = swans.get(0);
        checkVisited[s.r][s.c] = true;
        here.add(s);

        while(true) {

            if(BFS()) {
                System.out.println(ans);
                break;
            }

            Queue<Point> nq = new ArrayDeque<>();

            while(!q.isEmpty()) {
                Point cur = q.poll();

                for (int i = 0; i < 4; i++) {
                    int nr = cur.r + dx[i];
                    int nc = cur.c + dy[i];

                    if(nr < 0 || nc < 0 || nr >= R || nc >= C) continue;
                    if(visited[nr][nc]) continue;
                    if(grid[nr][nc].equals("L")) continue;

                    nq.add(new Point(nr,nc));
                    visited[nr][nc] = true;
                    grid[nr][nc] = ".";

                }
            }
            q = nq;

            ans++;
        }

    }

    static boolean BFS() {
        Queue<Point> checkq = here;
        here = new ArrayDeque<>();

        while (!checkq.isEmpty()) {
            Point p = checkq.poll();

            if(p.r == swans.get(1).r && p.c == swans.get(1).c) {
                return true;
            }

            for (int i = 0; i < 4; i++) {
                int nr = p.r + dx[i];
                int nc = p.c + dy[i];

                if(nr < 0 || nc < 0 || nr >= R || nc >= C) continue;
                if(checkVisited[nr][nc]) continue;
                if(grid[nr][nc].equals("X")) {
                    here.add(new Point(nr,nc));
                } else {
                    checkq.add(new Point(nr,nc));
                }
                checkVisited[nr][nc] = true;
            }
        }

        return false;
    }
}
