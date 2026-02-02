import java.io.*;
import java.util.*;

public class BOJ_1389 {
    static final int INF = 9999999; // 연결되지 않음을 나타내는 충분히 큰 값

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 유저의 수
        int M = Integer.parseInt(st.nextToken()); // 친구 관계의 수

        int[][] dist = new int[N + 1][N + 1];

        // 1. 최단 거리 행렬 초기화
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == j) dist[i][j] = 0;
                else dist[i][j] = INF;
            }
        }

        // 2. 친구 관계 입력 (무방향 그래프이므로 양방향 설정)
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            dist[a][b] = dist[b][a] = 1;
        }

        // 3. 플로이드-워셜 알고리즘 수행
        // k = 거쳐가는 노드, i = 출발 노드, j = 도착 노드
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    // k를 거쳐가는 것이 기존 경로보다 짧으면 갱신
                    if (dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        // 4. 케빈 베이컨 수가 가장 작은 사람 찾기
        int minKevinBacon = INF;
        int answerPerson = -1;

        for (int i = 1; i <= N; i++) {
            int currentSum = 0;
            for (int j = 1; j <= N; j++) {
                currentSum += dist[i][j];
            }

            // 가장 작은 합을 가진 사람 갱신
            // 합이 같다면 번호가 작은 사람을 유지해야 하므로 '>' 만 사용
            if (currentSum < minKevinBacon) {
                minKevinBacon = currentSum;
                answerPerson = i;
            }
        }

        System.out.println(answerPerson);
    }
}