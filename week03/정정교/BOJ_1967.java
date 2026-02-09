import java.util.*;
import java.io.*;

class Node {
    int target, weight;
    Node(int target, int weight) {
        this.target = target;
        this.weight = weight;
    }
}

public class BOJ_1967 {
    static List<Node>[] adj;
    static boolean[] visited;
    static int maxDist = 0;
    static int farthestNode = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        if (n == 1) {
            System.out.println(0);
            return;
        }

        adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) adj[i] = new ArrayList<>();

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            adj[u].add(new Node(v, w));
            adj[v].add(new Node(u, w));
        }

        // 1. 임의의 노드(1)에서 가장 먼 노드 탐색
        visited = new boolean[n + 1];
        dfs(1, 0);

        // 2. 찾은 노드에서 가장 먼 노드 탐색
        visited = new boolean[n + 1];
        maxDist = 0;
        dfs(farthestNode, 0);

        System.out.println(maxDist);
    }

    static void dfs(int cur, int dist) {
        if (dist > maxDist) {
            maxDist = dist;
            farthestNode = cur;
        }
        visited[cur] = true;

        for (Node next : adj[cur]) {
            if (!visited[next.target]) {
                dfs(next.target, dist + next.weight);
            }
        }
    }
}