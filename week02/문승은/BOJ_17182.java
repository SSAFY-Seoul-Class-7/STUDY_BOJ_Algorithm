import java.io.*;
import java.util.*;

public class BOJ_17182 {
    static int N, K;
    static int[][] dist;
    static boolean[] visited;
    static int minTime = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        dist = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                dist[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    dist[i][j] = Integer.min(dist[i][j], dist[i][k]+dist[k][j]);
                }
            }
        }

        visited = new boolean[N];
        visited[K] = true;
        dfs(K, 0, 1);

        System.out.println(minTime);
    }

    static void dfs(int current, int sum, int depth) {
        if(depth == N) {
            minTime = Integer.min(sum, minTime);
        }

        for(int i=0; i< N; i++) {
            if(!visited[i]) {
                visited[i] = true;
                dfs(i, sum + dist[current][i], depth + 1);
                visited[i] = false;
            }

        }

    }
}