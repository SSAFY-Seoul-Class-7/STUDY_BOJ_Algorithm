package 풀이중;
import java.io.*;
import java.util.*;

class Point{
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class BOJ_7576 {
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        int[][] box = new int[n][m];
        Queue<Point> q = new ArrayDeque();
        int unripe = 0;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < m; j++) {
                box[i][j] = Integer.parseInt(st.nextToken());
                if (box[i][j] == 1) {
                    q.offer(new Point(i, j));
                } else if (box[i][j] == 0) {
                    unripe++;
                }
            }
        }
        if (unripe == 0) {
            System.out.println(0);
            return;
        }

        System.out.println(multiBfs(n, m, box, q ,unripe));
    }

    static int multiBfs(int n, int m, int[][] box, Queue<Point> q, int unripe) {
        int day = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Point cur = q.poll();
                int x = cur.x;
                int y = cur.y;
                for (int d = 0; d < 4; d++) {
                    int nx = x + dr[d];
                    int ny = y + dc[d];

                    if (nx >= 0 && nx < n && ny >= 0 && ny < m && box[nx][ny] == 0) {
                        box[nx][ny] = 1;
                        unripe--;
                        q.offer(new Point(nx, ny));
                    }
                }
            }

            day++;
        }
        if (unripe > 0) {
            return  -1;
        }

        day -= 1; // 마지막 day 빼주기
        return day;
    }
}
