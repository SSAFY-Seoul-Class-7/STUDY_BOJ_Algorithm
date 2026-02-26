import java.io.*;
import java.util.*;

public class BOJ_16236 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    // 첫째 줄에 공간의 크기 N(2 ≤ N ≤ 20)이 주어진다.
    static int N;
    static int[][] map;

    static int[] dy = { -1, 0, 1, 0 };
    static int[] dx = { 0, -1, 0, 1 };

    static int startY;
    static int startX;

    static int sharkSize;
    static int eatFish;

    static int time;

    // 아기 상어는 자신의 크기와 같은 수의 물고기를 먹을 때 마다 크기가 1 증가

    // result 공간의 상태가 주어졌을 때, 아기 상어가 몇 초 동안
    // 엄마 상어에게 도움을 요청하지 않고 물고기를 잡아먹을 수 있는지 구하는 프로그램

    // 거리가 가까운 물고기가 많다면,
    // 가장 위에 있는 물고기, 그러한 물고기가
    // 여러마리라면, 가장 왼쪽에 있는 물고기를 먹는다.

    public static void main(String[] args) throws IOException {
        init();
        solve();
        System.out.println(time);
    }

    static void init() throws IOException {
        sharkSize = 2; // 처음 상어 사이즈
        eatFish = 0; // 먹은 물고기 갯수

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                int value = Integer.parseInt(st.nextToken());
                map[i][j] = value;
                if (value == 9) {
                    startY = i;
                    startX = j;
                    map[i][j] = 0;
                }
            }
        }
    }

    static void solve() {
        while (true) {
            PriorityQueue<Fish> pq = new PriorityQueue<>();
            boolean[][] visited = new boolean[N][N];

            pq.add(new Fish(startY, startX, 0));
            visited[startY][startX] = true;

            Fish target = null; // 이번 턴에 먹을 물고기

            while (!pq.isEmpty()) {
                Fish cur = pq.poll();

                if (map[cur.y][cur.x] != 0 && map[cur.y][cur.x] < sharkSize) {
                    target = cur;
                    break;
                }

                for (int i = 0; i < 4; i++) {
                    int ny = cur.y + dy[i];
                    int nx = cur.x + dx[i];
                    if (ny >= 0 && nx >= 0 && ny < N && nx < N && !visited[ny][nx]) {
                        if (map[ny][nx] <= sharkSize) {
                            visited[ny][nx] = true;
                            pq.add(new Fish(ny, nx, cur.dist + 1));
                        }
                    }
                }
            }

            if (target == null)
                break;

            time += target.dist;
            map[target.y][target.x] = 0;
            startY = target.y;
            startX = target.x;
            eatFish++;

            if (eatFish == sharkSize) {
                sharkSize++;
                eatFish = 0;
            }
        }

    }

    static void upgrade() {
        sharkSize++;
        eatFish = 0; // 먹어야될거 증가
    }

    static class Fish implements Comparable<Fish> {
        int y;
        int x;
        int dist;

        public Fish(int y, int x, int dist) {
            this.y = y;
            this.x = x;
            this.dist = dist;
        }

        @Override
        public int compareTo(Fish o) {
            if (this.dist != o.dist)
                return this.dist - o.dist; // 1. 거리순
            if (this.y != o.y)
                return this.y - o.y; // 2. 위쪽(y 적은 순)
            return this.x - o.x; // 3. 왼쪽(x 적은 순)
        }

    }
}
