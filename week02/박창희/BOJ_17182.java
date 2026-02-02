package STUDY_BOJ_Algorithm.week02.박창희;


import java.io.*;
import java.util.*;

public class BOJ_17182 {

    static int N, K;
    static int[][] dist;
    static boolean[] visited;
    static int minTime = Integer.MAX_VALUE;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dist = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                dist[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        visited = new boolean[N];
        visited[K] = true; 
        dfs(K, 0, 1);

        System.out.println(minTime);
    }

    static void dfs(int curr, int sum, int count) {
        if (count == N) {
            minTime = Math.min(minTime, sum);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(i, sum + dist[curr][i], count + 1);
                visited[i] = false; 
            }
        }
    }
}
