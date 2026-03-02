import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {


    static int N,M,K;
    static List<Edge>[] graph;

    static class Edge implements Comparable<Edge>{
        int to;
        long dist;
        public Edge(int to, long dist) {
            this.to = to;
            this.dist=dist;
        }

        @Override
        public int compareTo(Edge o) {
            return Long.compare(this.dist, o.dist);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N+1];
        for (int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());
            graph[to].add(new Edge(from, dist));
        }

        //dijkstra
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        long[] dists = new long[N+1];
        Arrays.fill(dists, Long.MAX_VALUE);

        //multi
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            int k = Integer.parseInt(st.nextToken());
            pq.add(new Edge(k, 0));
            dists[k] = 0;
        }

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            if(dists[cur.to] < cur.dist) continue;

            for (Edge next: graph[cur.to]) {
                long newdist = dists[cur.to] + next.dist;

                if(dists[next.to] > newdist) {
                    dists[next.to] = newdist;
                    pq.add(new Edge(next.to, newdist));
                }
            }
        }

        int idx = -1;
        long maxDist = 0;
        for (int i = 0; i < N+1; i++) {
            if((dists[i] != Long.MAX_VALUE) && dists[i] > maxDist) {
                maxDist = dists[i];
                idx = i;
            }
        }

        System.out.println(idx);
        System.out.println(maxDist);





    }
}