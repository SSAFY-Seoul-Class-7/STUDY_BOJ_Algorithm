package STUDY_BOJ_Algorithm.week08.박창희;

import java.io.*;
import java.util.*;

public class BOJ_10282 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int T;
    static int n, d, c;
    static int start;
    static int INF = Integer.MAX_VALUE;

    static class Node implements Comparable<Node> {
        int to, cost;

        Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    static List<List<Node>> graph;

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            init();
            dijkstra();
        }

        System.out.println(sb);
    }

    static void init() throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        start = c;

        graph = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < d; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph.get(b).add(new Node(a, c));
        }
    }

    static void dijkstra() {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, INF);
        dist[c] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(c, 0));

        while (!pq.isEmpty()) {
            Node curNode = pq.poll();

            if (dist[curNode.to] < curNode.cost) {
                continue;
            }

            for (Node next : graph.get(curNode.to)) {
                if (dist[next.to] > dist[curNode.to] + next.cost) {
                    dist[next.to] = dist[curNode.to] + next.cost;
                    pq.offer(new Node(next.to, dist[next.to]));
                }
            }
        }

        int max = 0;
        int target = 0;

        for (int i = 1; i <= n; i++) {
            if (dist[i] != INF) {
                target++;
            }
            if (dist[i] != INF && max < dist[i]) {
                max = dist[i];
            }
        }

        sb.append(target + " " + max).append("\n");

    }
}
