package week10.박창희;

import java.io.*;
import java.util.*;

public class BOJ_2206 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, M;
    static int[][] graph;

    static int[] dy = { 0, 0, -1, 1 };
    static int[] dx = { -1, 1, 0, 0 };

    static boolean[][][] visited;

    // 목표 0,0 -> N - 1 , M - 1;
    // N(1 ≤ N ≤ 1,000), M(1 ≤ M ≤ 1,000)

    public static void main(String[] args) throws IOException {
        init();
        int result = solved();
        System.out.println(result);
    }

    static void init() throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new int[N][M];
        visited = new boolean[N][M][2];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                graph[i][j] = str.charAt(j) - '0';
            }
        }
    }

    static int solved() {

        PriorityQueue<Node> queue = new PriorityQueue<>();
        visited[0][0][0] = true;
        queue.offer(new Node(0, 0, 1, false)); // 0 , 0 에서 시작 1의 값을 가지고 있음

        while (!queue.isEmpty()) {
            Node curNode = queue.poll();

            if (curNode.y == N - 1 && curNode.x == M - 1) {
                return curNode.cost;
            }

            for (int i = 0; i < 4; i++) {

                int nextY = curNode.y + dy[i];
                int nextX = curNode.x + dx[i];

                if (nextY >= 0 && nextX >= 0 && nextY < N && nextX < M) {
                    int breakState = curNode.crash ? 1 : 0; // 현재 나의 상태 (0: 안부숨, 1: 부숨)

                    if (graph[nextY][nextX] == 0) {
                        if (!visited[nextY][nextX][breakState]) { // 내 현재 상태와 같은 차원 체크
                            visited[nextY][nextX][breakState] = true;
                            queue.offer(new Node(nextY, nextX, curNode.cost + 1, curNode.crash));
                        }
                    } else if (graph[nextY][nextX] == 1 && !curNode.crash) {
                        if (!visited[nextY][nextX][1]) { // 부순 상태의 차원 체크
                            visited[nextY][nextX][1] = true;
                            queue.offer(new Node(nextY, nextX, curNode.cost + 1, true));
                        }
                    }
                }
            }
        }

        return -1;
    }

    static class Node implements Comparable<Node> {
        int y;
        int x;
        int cost;
        boolean crash;

        public Node(int y, int x, int cost, boolean crash) {
            this.y = y;
            this.x = x;
            this.cost = cost;
            this.crash = crash;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
}
