package week03.박창희;

import java.io.*;
import java.util.*;

public class BOJ_1967 {
    static int N;
    static ArrayList<ArrayList<Node>> adj = new ArrayList<>();
    static boolean[] visited;
    static int maxDist = 0;
    static int farthestNode = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        if (N == 1) {
            System.out.println(0);
            return;
        }

        for (int i = 0; i <= N; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            
            adj.get(parent).add(new Node(child, weight));
            adj.get(child).add(new Node(parent, weight));
        }

        visited = new boolean[N + 1];
        dfs(1, 0);

        visited = new boolean[N + 1];
        maxDist = 0; // 거리 초기화
        dfs(farthestNode, 0);

        System.out.println(maxDist);
    }

    static void dfs(int now, int dist) {
        visited[now] = true;
        if (dist > maxDist) {
            maxDist = dist;
            farthestNode = now;
        }

        for (Node next : adj.get(now)) {
            if (!visited[next.to]) {
                dfs(next.to, dist + next.weight);
            }
        }
    }

    static class Node {
        int to, weight;
        Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
}
