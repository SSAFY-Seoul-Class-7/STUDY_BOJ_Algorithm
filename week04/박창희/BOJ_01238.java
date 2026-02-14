package week04.박창희;

import java.util.*;
import java.io.*;

public class BOJ_01238 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, M, X;
    static final int INF = 1_000_000_000;

    static List<List<Node>> graph; // 정방향 그래프
    static List<List<Node>> reverseGraph; // 역방향 그래프

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        reverseGraph = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
            reverseGraph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.get(u).add(new Node(v, w));
            reverseGraph.get(v).add(new Node(u, w));
        }

        int[] distToX = dijkstra(graph, X);
        int[] distFromX = dijkstra(reverseGraph, X);

        int maxDist = 0;
        for (int i = 1; i <= N; i++) {
            maxDist = Math.max(maxDist, distToX[i] + distFromX[i]);
        }

        sb.append(maxDist);
        System.out.println(sb.toString());

    }

    static int[] dijkstra(List<List<Node>> targetGraph, int start) {

        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node currentNode = pq.poll();
            int currentIdx = currentNode.index;
            int currentCost = currentNode.cost;

            if (currentCost > dist[currentIdx])
                continue;

            for (Node neighbor : targetGraph.get(currentIdx)) {
                if (dist[neighbor.index] > currentCost + neighbor.cost) {
                    dist[neighbor.index] = currentCost + neighbor.cost;
                    pq.offer(new Node(neighbor.index, dist[neighbor.index]));
                }
            }
        }

        return dist;
    }
}

class Node implements Comparable<Node> {
    int index;
    int cost;

    public Node(int index, int cost) {
        this.index = index;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node o) {
        return this.cost - o.cost;
    }
}