package 풀이중;

import java.io.*;
import java.util.*;

public class BOJ_4485 {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] map;
    static int[][] dArr;
    static int n = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int test_case = 1;
        while (true) {
            n = Integer.parseInt(br.readLine());
            if(n == 0) break;

            map = new int[n][n];
            dArr = new int[n][n];
            for (int i = 0; i < n; i++) {
                Arrays.fill(dArr[i], Integer.MAX_VALUE);
            }

            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            sb.append("Problem ").append(test_case++).append(": ").append(dijkstra()).append('\n');
        }
        System.out.println(sb);
    }

    private static int dijkstra() {
        PriorityQueue<Edge> pq = new PriorityQueue();
        pq.offer(new Edge(0, 0, map[0][0]));
        dArr[0][0] = map[0][0];

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            int curX = cur.x;
            int curY = cur.y;
            int curW = cur.w;

            if (curW > dArr[curX][curY]) {
                continue;
            }

            for (int d = 0; d < 4; d++) {
                int nx = curX + dx[d];
                int ny = curY + dy[d];

                if (nx >= 0 && nx < n && ny >= 0 && ny < n
                        && dArr[nx][ny] > curW + map[nx][ny]) {
                    dArr[nx][ny] = curW + map[nx][ny];
                    pq.offer(new Edge(nx, ny, curW + map[nx][ny]));
                }
            }
        }
        return dArr[n - 1][n - 1];
    }


    static class Edge implements Comparable<Edge> {

        int x;
        int y;
        int w;

        public Edge(int x, int y, int w) {
            this.x = x;
            this.y = y;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return this.w - o.w;
        }
    }
}
