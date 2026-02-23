
import java.io.*;
import java.util.*;

public class BOJ_14940 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, M;
    static int[][] map;
    static int[][] result;
    static boolean[][] visited;
    static Node start;

    static int[] dy = { 0, 0, -1, 1 };
    static int[] dx = { 1, -1, 0, 0 };

    public static void main(String[] args) throws IOException {
        init();
        solve();

    }

    static void solve() {
        ArrayDeque<Node> ad = new ArrayDeque<>();
        ad.offer(start);
        visited[start.y][start.x] = true;
        result[start.y][start.x] = 0;

        while (!ad.isEmpty()) {
            Node cur = ad.poll();

            for (int i = 0; i < 4; i++) {
                int nextY = cur.y + dy[i];
                int nextX = cur.x + dx[i];

                if (nextY >= 0 && nextY < N && nextX >= 0 && nextX < M) {
                    if (!visited[nextY][nextX] && map[nextY][nextX] == 1) { // 방문한적 없으면
                        visited[nextY][nextX] = true;
                        result[nextY][nextX] = result[cur.y][cur.x] + 1;
                        ad.offer(new Node(nextY, nextX));
                    }
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(result[i][j] + " ");
            }
            sb.append("\n");
        }

        System.out.println(sb);

    }

    static void init() throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        result = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                int value = Integer.parseInt(st.nextToken());

                if (value == 2) {
                    start = new Node(i, j);
                    result[i][j] = -1;
                } else if (value == 0) {
                    result[i][j] = 0;
                } else {
                    result[i][j] = -1;
                }

                map[i][j] = value;

            }
        }
    }

    static class Node {
        int y;
        int x;

        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

}
