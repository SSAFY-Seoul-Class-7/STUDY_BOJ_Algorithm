package week09.박창희;

import java.io.*;
import java.util.*;

public class BOJ_20618 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, M, A, B, C;
    static List<List<Edge>> map;
    static int INF = Integer.MAX_VALUE;

    static class Edge {
        int to, cost;

        public Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    static class Node implements Comparable<Node> {
        int to, mCost, totalSum;

        public Node(int to, int mCost, int totalSum) {
            this.to = to;
            this.mCost = mCost;
            this.totalSum = totalSum;
        }

        @Override
        public int compareTo(Node o) {
            if (this.mCost != o.mCost)
                return Integer.compare(this.mCost, o.mCost);
            return Integer.compare(this.totalSum, o.totalSum);
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        dijkstra();
    }

    static int dijkstra() {
        int dist[] = new int[N + 1];
        Arrays.fill(dist, INF);
        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist[A] = 0;

        // 시작점 A 수치심 0, 누적 0
        pq.add(new Node(A, 0, 0));

        while (!pq.isEmpty()) {
            Node curNode = pq.poll();

            if (curNode.to == B) {
                // 종료 조건
                return curNode.mCost;
            }

            // 가지치기
            if (dist[curNode.to] < curNode.totalSum) {
                continue;

            }

            for (Edge edge : map.get(curNode.to)) {
                int nextSum = curNode.totalSum + edge.cost;

                if (nextSum <= C) {
                    int nextMax = Math.max(curNode.mCost, edge.cost);

                    if (dist[edge.to] > nextSum) {
                        dist[edge.to] = nextSum;
                        pq.add(new Node(edge.to, nextMax, nextSum));
                    }
                }

            }

        }

        return -1;
    }

    static void init() throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            map.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            map.get(a).add(new Edge(b, c));
            map.get(b).add(new Edge(a, c));
        }
    }

}
