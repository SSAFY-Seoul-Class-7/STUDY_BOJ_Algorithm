
import java.io.*;
import java.util.*;

public class BOJ_16928 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, M;
    static int[] board;
    static boolean[] visited;
    static int[] costs;

    public static void main(String[] args) throws IOException {
        init();
        solve();

    }

    static void init() throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        // 사다리의 수 N(1 ≤ N ≤ 15)과 뱀의 수 M(1 ≤ M ≤ 15)이 주어진다.

        board = new int[101];

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            board[a] = b;

        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            board[a] = b;
        }

    }

    static void solve() {

        visited = new boolean[101];
        costs = new int[101];

        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(1);
        visited[1] = true;

        while (!queue.isEmpty()) {

            int current = queue.poll();

            if (current == 100) { // 기저 조건
                System.out.println(costs[100]);
                break;
            }

            for (int i = 1; i <= 6; i++) {

                int next = current + i;

                if (next > 100) {
                    continue;
                }

                if (board[next] != 0) {
                    next = board[next];
                }

                if (!visited[next]) {
                    visited[next] = true;
                    costs[next] = costs[current] + 1;
                    queue.offer(next);
                }
            }
        }
    }
}
