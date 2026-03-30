import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_2660 {
    static final int INF = 100; // N이 50이므로 100이면 충분합니다.

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] dist = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == j) dist[i][j] = 0;
                else dist[i][j] = INF;
            }
        }

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            if (u == -1 && v == -1) break;

            dist[u][v] = 1;
            dist[v][u] = 1;
        }

        // 플로이드-워셜
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        int[] scores = new int[N + 1];
        int minScore = INF;

        for (int i = 1; i <= N; i++) {
            int maxDist = 0;
            for (int j = 1; j <= N; j++) {
                maxDist = Math.max(maxDist, dist[i][j]);
            }
            scores[i] = maxDist;
            minScore = Math.min(minScore, maxDist);
        }

        ArrayList<Integer> candidates = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            if (scores[i] == minScore) {
                candidates.add(i);
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(minScore).append(" ").append(candidates.size()).append("\n");
        for (int c : candidates) {
            sb.append(c).append(" ");
        }
        System.out.println(sb);
    }
}