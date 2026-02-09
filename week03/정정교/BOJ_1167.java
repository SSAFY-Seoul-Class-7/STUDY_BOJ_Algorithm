import java.util.*;
import java.io.*;

class Node {
    int target, dist;
    Node(int target, int dist) {
        this.target = target;
        this.dist = dist;
    }
}

public class BOJ_1167 {
    static List<Node>[] adj;
    static boolean[] visited;
    static int maxDist = 0;
    static int farthestNode = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int V = Integer.parseInt(br.readLine());

        adj = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++) adj[i] = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            while (true) {
                int to = Integer.parseInt(st.nextToken());
                if (to == -1) break;
                int dist = Integer.parseInt(st.nextToken());
                adj[from].add(new Node(to, dist));
            }
        }

        // 1차 탐색: 1번 노드에서 가장 먼 노드 찾기
        visited = new boolean[V + 1];
        dfs(1, 0);

        // 2차 탐색: 찾은 끝점에서 다시 가장 먼 노드 찾기
        visited = new boolean[V + 1];
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
                dfs(next.target, dist + next.dist);
            }
        }
    }
}