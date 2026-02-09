package 풀이중;

import java.io.*;
import java.util.*;

public class 사회망서비스 {
    static int n;
    static ArrayList<Integer>[] graph;
    static int[][] dp;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        graph = new ArrayList[n + 1];
        visited = new boolean[n + 1];
        dp = new int[n + 1][2];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            graph[from].add(to);
            graph[to].add(from);
        }


        dfs(1);
        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }
    private static void dfs(int cur) {
        visited[cur] = true;

        dp[cur][1] = 1;
        dp[cur][0] = 0;

        for (int child : graph[cur]) {
            if (!visited[child]) {
                dfs(child);
                dp[cur][1] += Math.min(dp[child][0], dp[child][1]);
                dp[cur][0] += dp[child][1];
            }
        }


    }
}
