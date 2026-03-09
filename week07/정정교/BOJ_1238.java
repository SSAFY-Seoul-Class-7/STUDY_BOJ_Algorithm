import java.io.*;
import java.util.*;

public class BOJ_1238 {
    static final int INF = Integer.MAX_VALUE;
    static List<Node>[] adj, revAdj;
    static int N, M, X;

    static class Node implements Comparable<Node> {
        int target, time;
        Node(int target, int time) { this.target = target; this.time = time; }
        @Override
        public int compareTo(Node o) { return this.time - o.time; }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        adj = new ArrayList[N + 1];
        revAdj = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
            revAdj[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            adj[u].add(new Node(v, t));
            revAdj[v].add(new Node(u, t));
        }

        int[] distToParty = dijkstra(revAdj, X); // 각 마을 -> X
        int[] distFromParty = dijkstra(adj, X);  // X -> 각 마을

        int maxTime = 0;
        for (int i = 1; i <= N; i++) {
            maxTime = Math.max(maxTime, distToParty[i] + distFromParty[i]);
        }
        System.out.println(maxTime);
    }

    static int[] dijkstra(List<Node>[] graph, int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            if (curr.time > dist[curr.target]) continue;

            for (Node next : graph[curr.target]) {
                if (dist[next.target] > dist[curr.target] + next.time) {
                    dist[next.target] = dist[curr.target] + next.time;
                    pq.add(new Node(next.target, dist[next.target]));
                }
            }
        }
        return dist;
    }
}