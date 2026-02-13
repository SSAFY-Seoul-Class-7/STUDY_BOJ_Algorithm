import java.io.*;
import java.util.*;

public class BOJ_1162 {

    static int N,M,K;
    static long[][] dist;
    static List<Node>[] edges;

    static class Node {
        int to;
        long weight;

        public Node(int to, long weight,int pave) {
            this.to = to;
            this.weight = weight;
        }
    }

    static class State implements Comparable<State> {
        int city, pave;
        long dist;

        public State(int city, long dist, int pave) {
            this.city = city;
            this.dist = dist;
            this.pave = pave;
        }

        @Override
        public int compareTo(State o) {
            return Long.compare(this.dist, o.dist);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        edges = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) {
            edges[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            edges[from].add(new Node(to, weight,0));
            edges[to].add(new Node(from, weight,0));
        }

        dist = new long[K+1][N+1];
        for (int i = 0; i <= K; i++) {
            Arrays.fill(dist[i], Long.MAX_VALUE);
        }

        PriorityQueue<State> pq = new PriorityQueue<>();

        dist[0][1] = 0;
        pq.add(new State(1, 0,0));

        while (!pq.isEmpty()) {
            State cur = pq.poll();

            if(cur.dist > dist[cur.pave][cur.city]) continue;

            for (Node next: edges[cur.city]) {

                long newDist = dist[cur.pave][cur.city] + next.weight;

                if(dist[cur.pave][next.to] > newDist) {
                    dist[cur.pave][next.to] = newDist;
                    pq.add(new State(next.to, newDist, cur.pave));
                }

                if(cur.pave < K && dist[cur.pave+1][next.to] > dist[cur.pave][cur.city]) {
                    dist[cur.pave+1][next.to] =  dist[cur.pave][cur.city];
                    pq.add(new State(next.to, dist[cur.pave][cur.city], cur.pave + 1));
                }
            }
        }

        long minAns = Long.MAX_VALUE;
        for (int i = 0; i <= K; i++) {
            minAns = Long.min(minAns,dist[i][N]);
        }

        System.out.println(minAns);

    }
}
