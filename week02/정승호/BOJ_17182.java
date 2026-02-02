package 플로이드;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17182 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 행성의 개수
        int start = Integer.parseInt(st.nextToken()); // 시작 행성

        int[][] dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                dist[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 플로이드 워셜 알고리즘으로 모든 정점 간의 최단 거리 구하기
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }
        boolean[] visited = new boolean[n];
        visited[start] = true;
        int ans = dfs(start, visited, dist, n, 1);
        System.out.println(ans);
    }

    static int dfs(int cur, boolean[] visited, int[][] dist, int n, int cnt) {
        if (cnt == n) {
            return 0; // 모든 행성을 방문했으면 비용 0 반환
        }

        int result = Integer.MAX_VALUE;
        for (int next = 0; next < n; next++) {
            if (!visited[next]) {
                visited[next] = true;
                int cost = dist[cur][next] + dfs(next, visited, dist, n, cnt + 1);
                result = Math.min(result, cost);
                visited[next] = false;
            }
        }
        return result;
    }
}
