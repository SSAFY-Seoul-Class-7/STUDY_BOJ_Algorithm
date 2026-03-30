import java.io.*;
import java.util.*;

public class BOJ_2206 {
    static int n;
    static int m;
    static int[][] map;

    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };

    static class State {
        int x;
        int y;
        int dist;
        boolean canBreak;

        public State(int x, int y, int dist, boolean canBreak) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.canBreak = canBreak;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }

        System.out.println(bfs());
    }

    private static int bfs() {
        Queue<State> q = new ArrayDeque();
        int[][][] dist = new int[n][m][2];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int j2 = 0; j2 < 2; j2++) {
                    dist[i][j][j2] = Integer.MAX_VALUE;
                }
            }
        }

        dist[0][0][0] = 1;

        q.offer(new State(0, 0, 1, true));

        while (!q.isEmpty()) {
            State cur = q.poll();

            for (int d = 0; d < 4; d++) {
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];


                if(cur.canBreak) { //0
                    if(nx>=0 && ny >=0 && nx < n && ny < m
                            && dist[nx][ny][0] > cur.dist + 1) {
                        if(map[nx][ny] == 0) {
                            dist[nx][ny][0] = cur.dist + 1;
                            q.offer(new State(nx, ny, cur.dist + 1, cur.canBreak));
                        } else {
                            dist[nx][ny][0] = cur.dist + 1;
                            q.offer(new State(nx, ny, cur.dist + 1, false));
                        }

                    }
                }else { // 1
                    if(nx>=0 && ny >=0 && nx < n && ny < m
                            && dist[nx][ny][1] > cur.dist + 1) {
                        if(map[nx][ny] == 0) {
                            dist[nx][ny][1] = cur.dist + 1;
                            q.offer(new State(nx, ny, cur.dist + 1, cur.canBreak));
                        }
                    }
                }
            }
        }

        int result = Math.min(dist[n-1][m-1][0], dist[n-1][m-1][1]);
        if (result == Integer.MAX_VALUE) {
            return -1;
        } else {
            return result;
        }
    }
}
