import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1939 {

    static int N,M,A,B;
    static List<Edge>[] graph;
    static class Edge implements Comparable<Edge>{
        int to, weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(o.weight, this.weight);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N+1];
        for (int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }


        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to =  Integer.parseInt(st.nextToken());
            int weight =  Integer.parseInt(st.nextToken());

            graph[to].add(new Edge(from, weight));
            graph[from].add(new Edge(to, weight));
        }
        st = new StringTokenizer(br.readLine());

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());


        PriorityQueue<Edge> pq = new PriorityQueue<>();
        int[] dist = new int[N+1];

        pq.add(new Edge(A, Integer.MAX_VALUE));
        dist[A] = Integer.MAX_VALUE;

        while(!pq.isEmpty()) {
            Edge cur = pq.poll();

            if(cur.to == B) {
                System.out.println(cur.weight);
                break;
            }

            if(dist[cur.to] > cur.weight) continue;

            for(Edge next: graph[cur.to]) {
                int nextWeight = Math.min(cur.weight, next.weight);

                if(dist[next.to] < nextWeight) {
                    dist[next.to] = nextWeight;
                    pq.add(new Edge(next.to, nextWeight));
                }
            }

        }

    }

}
