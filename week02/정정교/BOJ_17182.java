import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17182 {
    static int N, K, answer = Integer.MAX_VALUE;
    static int[][] cost;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        //1. 입력부
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        cost = new int[N][N];
        visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //2. 플로이드-워셜 : 모든 정점쌍에 대한 최단 거리
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (cost[i][j] > cost[i][k] + cost[k][j]) {
                        cost[i][j] = cost[i][k] + cost[k][j];
                    }
                }
            }
        }

        //3. 순회
        visited[K] = true; //시작지점 방문처리
        dfs(K, 1, 0); // 방문 지점, 방문 행성, 비용합

        //4. 출력
        System.out.println(answer);
    }

    private static void dfs(int start, int cnt, int sum) {
        //3-1. 종료조건: 모든 행성을 다 한번씩 방문
        if (cnt == N) {
            answer = Math.min(answer, sum);
            return;
        }
        //3-2. 백트래킹
        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(i, cnt + 1, sum + cost[start][i]);
                visited[i] = false;
            }
        }
    }
}
