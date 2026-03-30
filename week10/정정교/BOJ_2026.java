import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2026 {
    static int N, M;
    static int[][] map;
    static boolean[][][] visited;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    static class Node {
        int y, x, dist, broken;
        Node(int y, int x, int dist, int broken) {
            this.y = y;
            this.x = x;
            this.dist = dist;
            this.broken = broken;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[2][N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }

        System.out.println(bfs());
    }

    private static int bfs() {
        Queue<Node> q = new ArrayDeque<>();
        q.offer(new Node(0, 0, 1, 0));
        visited[0][0][0] = true;

        while (!q.isEmpty()) {
            Node cur = q.poll();

            if (cur.y == N - 1 && cur.x == M - 1) {
                return cur.dist;
            }

            for (int i = 0; i < 4; i++) {
                int ny = cur.y + dy[i];
                int nx = cur.x + dx[i];

                if (ny < 0 || nx < 0 || ny >= N || nx >= M) continue;

                if (map[ny][nx] == 0) { // 빈 칸인 경우
                    if (!visited[cur.broken][ny][nx]) {
                        visited[cur.broken][ny][nx] = true;
                        q.offer(new Node(ny, nx, cur.dist + 1, cur.broken));
                    }
                } else { // 벽인 경우
                    if (cur.broken == 0 && !visited[1][ny][nx]) {
                        visited[1][ny][nx] = true;
                        q.offer(new Node(ny, nx, cur.dist + 1, 1));
                    }
                }
            }
        }
        return -1;
    }
}